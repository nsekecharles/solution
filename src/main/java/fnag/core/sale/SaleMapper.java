package fnag.core.sale;

import fnag.core.UnParsableStringException;

import static org.apache.commons.lang3.StringUtils.isBlank;

public class SaleMapper {

    public Sale fromString(String sellLine, String parameterDelimiter) {

        if(isBlank(sellLine) || isBlank(parameterDelimiter)) {
            throw new UnParsableStringException("impossible to transform string to sellResult. " +
                    "There is no String or no parameterDelimiter");
        }

        String[] values = sellLine.split(parameterDelimiter);

        if(values.length != 4) {
            throw new UnParsableStringException("impossible to transform string to product. " +
                    "There should be 4 parameters");
        }

        return new Sale(values[0], values[1], values[2], Integer.valueOf(values[3]));
    }
}
