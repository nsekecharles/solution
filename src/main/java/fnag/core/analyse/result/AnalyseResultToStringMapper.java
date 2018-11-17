package fnag.core.analyse.result;

import fnag.core.analyse.topsales.TopSalesAnalyseResult;
import fnag.core.analyse.topseller.TopSellerAnalyseResult;
import fnag.core.product.Product;

import java.util.List;
import java.util.Map;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toMap;

public class AnalyseResultToStringMapper {

    public static final String DELIMITER = "|";
    private final Map<String, Product> products;

    public AnalyseResultToStringMapper(List<Product> products) {
        this.products = products
                .stream()
                .collect(toMap(Product::getReference, identity()));
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
                throw new IllegalArgumentException("unhandle analyse type : " + result.getAnalyseType());
        }

        return mapValue;
    }

    private String map(TopSalesAnalyseResult resultToMap) {

        return resultToMap.getProductsRefence()
                .stream()
                .map(r -> new StringBuilder(resultToMap.getAnalyseType().name())
                        .append(DELIMITER)
                        .append(r)
                        .append(DELIMITER)
                        .append(products.get(r).getDescription())
                        .append(DELIMITER)
                        .append(resultToMap.getQuantitySold())
                        .toString())
                .collect(joining("\n"));
    }

    private String map(TopSellerAnalyseResult resultToMap) {

        return resultToMap.getTopSellers()
                .stream()
                .map(topSeller -> new StringBuilder(resultToMap.getAnalyseType().name())
                        .append(DELIMITER)
                        .append(topSeller.getStore())
                        .append(DELIMITER)
                        .append(topSeller.getSeller())
                        .append(DELIMITER)
                        .append(topSeller.getTotalSales())
                        .toString())
                .collect(joining("\n"));
        }


}
