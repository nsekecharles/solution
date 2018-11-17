package fnag.core.analyse.topseller;

import fnag.core.analyse.AnalyseType;
import fnag.core.analyse.result.AnalyseResult;

import java.util.List;

public class TopSellerAnalyseResult implements AnalyseResult {

    private final List<TopSeller> topSellers;

    public TopSellerAnalyseResult(List<TopSeller> topSellers) {
        this.topSellers = topSellers;
    }

    public List<TopSeller> getTopSellers() {
        return topSellers;
    }

    @Override
    public AnalyseType getAnalyseType() {
        return AnalyseType.TOPSELLER;
    }

    @Override
    public String toString() {
        return "TopSellerAnalyseResult{" +
                "topSellers=" + topSellers +
                '}';
    }
}
