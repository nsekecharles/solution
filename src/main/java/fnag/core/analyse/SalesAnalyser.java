package fnag.core.analyse;

import fnag.core.analyse.result.AnalyseResult;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class SalesAnalyser {

    public List<AnalyseResult> analyseWith(SalesAnalyseAlgorithm... algorithms) {

        List<SalesAnalyseAlgorithm> analyseAlgorithms = new ArrayList<>(algorithms.length);

        for(SalesAnalyseAlgorithm algorithm : algorithms) {
            analyseAlgorithms.add(algorithm);
        }

        return analyseAlgorithms
                .stream()
                .map(SalesAnalyseAlgorithm::run)
                .map(o -> (AnalyseResult) o)
                .collect(toList());
    }
}
