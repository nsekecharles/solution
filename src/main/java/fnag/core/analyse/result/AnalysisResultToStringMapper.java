package fnag.core.analyse.result;

import fnag.core.analyse.topsales.TopSalesAnalyseResult;
import fnag.core.analyse.topseller.TopSellerAnalyseResult;
import fnag.core.product.Product;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.joining;

public class AnalysisResultToStringMapper {

    public static final String DELIMITER = "|";
    private final Map<String, Product> products;

    public AnalysisResultToStringMapper(List<Product> products) {
        this.products = products
                .stream()
                .collect(Collectors.toMap(Product::getReference, identity()));
    }

    public String map(AnalyseResult result) {

        String mapValue;

        switch (result.getAnalyseType()) {
            case TOPSALE:
                mapValue = map((TopSalesAnalyseResult) result);
                break;
            case TOPSELLER:
                mapValue = map((TopSellerAnalyseResult) result);
                break;
            default:
                throw new RuntimeException("");
        }
        return mapValue;
    }


    private String map(TopSalesAnalyseResult resultToMap) {
        StringBuilder resultBuilder = new StringBuilder(resultToMap.getAnalyseType().name());

        return resultBuilder
                .append(DELIMITER)
                .append(resultToMap.getProductsRefence().stream()
                        .map(r-> r + DELIMITER + products.get(r).getDescription())
                        .collect(joining(DELIMITER)))
                .append(DELIMITER)
                .append(resultToMap.getQuantitySold())
                .toString();
    }

    private String map(TopSellerAnalyseResult resultToMap) {

        StringBuilder resultBuilder = new StringBuilder(resultToMap.getAnalyseType().name());

        return resultBuilder
                .append(DELIMITER)
                .append(resultToMap.getTopSellers()
                        .stream()
                        .map(t -> t.getStore() + DELIMITER +t.getSeller() + DELIMITER + t.getTotalSales())
                .collect(joining(DELIMITER)))
                .toString();
        }


}
