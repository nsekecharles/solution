package fnag.core.analyse.topsales;

import fnag.core.analyse.result.AnalyseResult;
import fnag.core.analyse.AnalyseType;

import java.util.List;

public class TopSalesAnalyseResult implements AnalyseResult {

    private final int quantitySold;
    private final List<String> productsRefence;

    public TopSalesAnalyseResult(int quantitySold, List<String> productsRefence) {
        this.quantitySold = quantitySold;
        this.productsRefence = productsRefence;
    }

    public int getQuantitySold() {
        return quantitySold;
    }

    public List<String> getProductsRefence() {
        return productsRefence;
    }

    @Override
    public AnalyseType getAnalyseType() {
        return AnalyseType.TOPSALE;
    }
}
