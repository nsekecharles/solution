package fnag.core.repository.file;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SalesFileDataSourceTest {

    @Test
    void should_throw_exception_given_input_file_with_more_than_100_products() {
        assertThrows(SalesFileDataSourceException.class,
                () -> new SalesFileDataSource("TestFileWithTooMuchProductsToRead"));
    }

    @Test
    void should_throw_exception_given_input_file_with_more_than_100_sales() {
        assertThrows(SalesFileDataSourceException.class,
                () -> new SalesFileDataSource("TestFileWithTooMuchSalesToRead"));
    }

    @Test
    void should_create_data_given_a_valid_input_file() throws IOException {

        final var testFileData = new SalesFileDataSource("TestFile");

        assertThat(testFileData.getProducts()).hasSize(1);
        assertThat(testFileData.getSales()).hasSize(1);
    }

    @Test
    void should_create_data_given_a_valid_input_file_whithout_data() throws IOException {

        final var testFileData = new SalesFileDataSource("TestFileWithoutData");

        assertThat(testFileData.getProducts()).hasSize(0);
        assertThat(testFileData.getSales()).hasSize(0);
    }
}