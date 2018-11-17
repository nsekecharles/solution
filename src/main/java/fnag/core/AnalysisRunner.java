package fnag.core;

import fnag.core.analyse.SalesAnalyser;
import fnag.core.analyse.result.AnalyseResult;
import fnag.core.analyse.result.AnalysisResultToStringMapper;
import fnag.core.analyse.topsales.TopSalesAlgorithm;
import fnag.core.analyse.topseller.TopSellerAlgorithm;

import java.io.IOException;
import java.util.List;

public class AnalysisRunner {

    public static void main(String[] args) throws IOException {

        AnalysisDataSource dataSource = new SalesFileDataSource("Inputs");

        TopSalesAlgorithm topSalesAlgorithm = new TopSalesAlgorithm(dataSource.getSales());
        TopSellerAlgorithm topSellerAlgorithm = new TopSellerAlgorithm(dataSource.getSales(), dataSource.getProducts());

        SalesAnalyser salesAnalyser = new SalesAnalyser();

        final List<AnalyseResult> analyseResults = salesAnalyser.analyseWith(topSalesAlgorithm, topSellerAlgorithm);

        AnalysisResultToStringMapper mapper = new AnalysisResultToStringMapper(dataSource.getProducts());

        analyseResults.stream()
                .map(r -> mapper.map(r))
                .forEach(System.out::println);
    }
}
