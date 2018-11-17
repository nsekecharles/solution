package fnag.core.product;

import fnag.core.UnParsableStringException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProductMapperTest {

    private ProductMapper sut = new ProductMapper();

    @Test
    void should_throw_UnParsableStringException_given_null_product_string_to_parse_and_null_delimiter() {
        assertThrows(UnParsableStringException.class,
                () -> sut.fromString(null, null));
    }

    @Test
    void should_throw_UnParsableStringException_given_null_product_string_to_parse() {
        assertThrows(UnParsableStringException.class,
                () -> sut.fromString(null, "-"));
    }

    @Test
    void should_throw_UnParsableStringException_given_null_delimiter() {
        assertThrows(UnParsableStringException.class,
                () -> sut.fromString("T233-12-ras", null));
    }

    @Test
    void should_throw_UnParsableStringException_given_string_to_parse_without_3_parameters() {
        assertThrows(UnParsableStringException.class,
                () -> sut.fromString("T233-12-ras-erer", "-"));
    }

    @Test
    void should_make_a_sale_given_a_valid_string_to_map() {

        final var sale = sut.fromString("T233-12.12-ras", "-");

        assertThat(sale)
                .hasFieldOrPropertyWithValue("reference", "T233")
                .hasFieldOrPropertyWithValue("description", "ras")
                .hasFieldOrPropertyWithValue("price", 12.12);
    }

}