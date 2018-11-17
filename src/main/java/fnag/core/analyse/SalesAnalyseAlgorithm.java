package fnag.core.analyse;

import fnag.core.analyse.result.AnalyseResult;

public interface SalesAnalyseAlgorithm<T extends AnalyseResult> {
    T run();
}
