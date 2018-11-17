package fnag.core.analyse.topseller;

import fnag.core.analyse.SalesAnalyseAlgorithm;
import fnag.core.product.Product;
import fnag.core.sale.Sale;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.*;

public class TopSellerAlgorithm implements SalesAnalyseAlgorithm<TopSellerAnalyseResult> {

    private final List<Sale> sales;
    private final Map<String, Double> productsPrice;

    public TopSellerAlgorithm(List<Sale> sales, List<Product> products) {

        if(products == null || products.isEmpty()) {
            throw new AnalysisException("Products list cannot be empty");
        }
        this.sales = sales;
        this.productsPrice = products.stream()
                .collect(toMap(Product::getReference, Product::getPrice));
    }

    @Override
    public TopSellerAnalyseResult run() {

        final Map<String, List<Sale>> sellerSales = sales.stream()
                .collect(groupingBy(Sale::getSeller));

        final List<TopSeller> topSellers = sellerSales.entrySet().stream()
                .map(this::toTopSeller)
                .collect(toList());

        final double maxsalesAmount = topSellers.stream()
                .mapToDouble(TopSeller::getTotalSales)
                .max()
                .orElse(0);

        final List<TopSeller> filteredTopSellers = topSellers.stream()
                .filter(topSeller -> topSeller.getTotalSales() == maxsalesAmount)
                .collect(toList());

        return new TopSellerAnalyseResult(filteredTopSellers);
    }

    private TopSeller toTopSeller(Map.Entry<String, List<Sale>> e) {
        return new TopSeller(e.getValue().get(0).getStoreName(), e.getKey(), totalSales(e.getValue()));
    }

    private double totalSales(List<Sale> salesToSum) {
        return salesToSum.stream().mapToDouble(t-> t.getQuantity() * productsPrice.get(t.getProductReference())).sum();
    }
}
