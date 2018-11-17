package fnag.core.analyse.topsales;

import fnag.core.analyse.SalesAnalyseAlgorithm;
import fnag.core.sale.Sale;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.*;

public class TopSalesAlgorithm implements SalesAnalyseAlgorithm<TopSalesAnalyseResult> {
    private final List<Sale> sales;

    public TopSalesAlgorithm(List<Sale> sales) {
        this.sales = sales;
    }

    @Override
    public TopSalesAnalyseResult run() {

        final Map<String, Integer> productSales = sales.stream()
                .collect(groupingBy(Sale::getProductReference, summingInt(Sale::getQuantity)));

        final int max = productSales.values()
                .stream()
                .mapToInt(v -> v)
                .max()
                .orElse(0);

        final List<String> bestSales = productSales.entrySet()
                .stream()
                .filter(entry -> entry.getValue() == max)
                .map(entry -> entry.getKey())
                .collect(toList());

        return new TopSalesAnalyseResult(max, bestSales);
    }
}
