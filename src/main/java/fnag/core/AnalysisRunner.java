package fnag.core;

import fnag.core.analyse.SalesAnalyser;
import fnag.core.analyse.result.AnalyseResult;
import fnag.core.analyse.result.AnalyseResultToStringMapper;
import fnag.core.analyse.topsales.TopSalesAlgorithm;
import fnag.core.analyse.topseller.TopSellerAlgorithm;
import fnag.core.repository.AnalysisDataSource;
import fnag.core.repository.file.SalesFileDataSource;

import java.io.IOException;
import java.util.List;

public class AnalysisRunner {

    public static void main(String[] args) throws IOException {

        AnalysisDataSource dataSource = new SalesFileDataSource("Inputs");

        TopSalesAlgorithm topSalesAlgorithm = new TopSalesAlgorithm(dataSource.getSales());
        TopSellerAlgorithm topSellerAlgorithm = new TopSellerAlgorithm(dataSource.getSales(), dataSource.getProducts());

        SalesAnalyser salesAnalyser = new SalesAnalyser();

        final List<AnalyseResult> analyseResults = salesAnalyser.analyseWith(topSalesAlgorithm, topSellerAlgorithm);

        AnalyseResultToStringMapper resultToStringMapper = new AnalyseResultToStringMapper(dataSource.getProducts());

        analyseResults.stream()
                .map(resultToStringMapper::map)
                .forEach(System.out::println);
    }
}
