package fnag.core.product;


import fnag.core.UnParsableStringException;

import static org.apache.commons.lang3.StringUtils.isBlank;

public class ProductMapper {

    public Product fromString(String productLine, String parameterDelimiter) {

        if(isBlank(productLine) || isBlank(parameterDelimiter)) {
            throw new UnParsableStringException("impossible to transform string to product. " +
                    "There is no String or no parameterDelimiter");
        }

        String[] values = productLine.split(parameterDelimiter);

        if(values.length != 3) {
            throw new UnParsableStringException("impossible to transform string to product. " +
                    "There should be 3 parameters");
        }

        return new Product(values[0], Double.valueOf(values[1]), values[2]);
    }
}
