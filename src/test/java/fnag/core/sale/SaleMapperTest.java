package fnag.core.sale;


import fnag.core.UnParsableStringException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SaleMapperTest {

    private SaleMapper sut = new SaleMapper();

    @Test
    void should_throw_UnParsableStringException_given_null_string_to_parse_and_null_delimiter() {
        assertThrows(UnParsableStringException.class,
                () -> sut.fromString(null, null));
    }

    @Test
    void should_throw_UnParsableStringException_given_null_string_to_parse() {
        assertThrows(UnParsableStringException.class,
                () -> sut.fromString(null, "-"));
    }

    @Test
    void should_throw_UnParsableStringException_given_null_delimiter() {
        assertThrows(UnParsableStringException.class,
                () -> sut.fromString("test-test-12", null));
    }

    @Test
    void should_throw_UnParsableStringException_given_string_to_parse_without_4_parameters() {
        assertThrows(UnParsableStringException.class,
                () -> sut.fromString("test-test-12", "-"));
    }

    @Test
    void should_make_a_sale_given_a_valid_string_to_map() {

        final var sale = sut.fromString("AAA-AAA-test-12", "-");

        assertThat(sale)
                .hasFieldOrPropertyWithValue("storeName", "AAA")
                .hasFieldOrPropertyWithValue("productReference", "test")
                .hasFieldOrPropertyWithValue("quantity", 12)
                .hasFieldOrPropertyWithValue("seller", "AAA");
    }
}