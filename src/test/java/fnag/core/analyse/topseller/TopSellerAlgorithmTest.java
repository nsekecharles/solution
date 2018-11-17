package fnag.core.analyse.topseller;

import fnag.core.analyse.AnalyseType;
import fnag.core.product.Product;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static fnag.core.sale.SalesTestUtils.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TopSellerAlgorithmTest {

    @Test
    void should_throw_invalid_exception_given_an_empty_products_list() {

        assertThrows(AnalysisException.class,
                () -> new TopSellerAlgorithm(Collections.emptyList(), Collections.emptyList()),
                "Products list cannot be empty");
    }

    @Test
    void should_throw_invalid_exception_given_a_null_products_list() {

        assertThrows(AnalysisException.class,
                () -> new TopSellerAlgorithm(Collections.emptyList(), null),
                "Products list cannot be empty");
    }

    @Test
    void should_return_empty_analyse_result_given_an_empty_sales_list() {

        var sut = new TopSellerAlgorithm(Collections.emptyList(), List.of(new Product("TTT",22,"rien à dire")));

        // When
        var result = sut.run();

        // Then
        isTopSellerResultType(result);
        assertThat(result.getTopSellers()).isEqualTo(Collections.emptyList());
    }

    private static void isTopSellerResultType(TopSellerAnalyseResult result) {
        assertThat(result.getAnalyseType()).isEqualTo(AnalyseType.TOPSELLER);
    }

    @Test
    void should_return_the_correct_top_seller_given_one_sale() {

        // Given
        var sales = List.of(CHARLES_FROM_PARIS_SOLD_4_S_888);
        var products = List.of(new Product("S888", 10, "nothing"));
        var sut = new TopSellerAlgorithm(sales, products);

        // When
        var result = sut.run();

        // Then
        isTopSellerResultType(result);
        assertThat(result.getTopSellers().get(0))
                .hasFieldOrPropertyWithValue("seller", "Charles")
                .hasFieldOrPropertyWithValue("store", "PARIS")
                .hasFieldOrPropertyWithValue("totalSales", 40.00);

    }

    @Test
    void should_return_the_correct_top_seller_given_different_sales() {

        // Given
        var sales = List.of(
                CHARLES_FROM_PARIS_SOLD_4_S_888,
                KARINE_FROM_ROUEN_SOLD_5_S_888,
                CHARLES_FROM_PARIS_SOLD_10_T_233);

        var products = List.of(new Product("S888", 10, "nothing"),
                new Product("T233", 1,"ras"));
        var sut = new TopSellerAlgorithm(sales, products);

        // When
        var result = sut.run();

        // Then
        isTopSellerResultType(result);
        assertThat(result.getTopSellers().get(0))
                .hasFieldOrPropertyWithValue("seller", "Charles")
                .hasFieldOrPropertyWithValue("store", "PARIS")
                .hasFieldOrPropertyWithValue("totalSales", 50.00);
    }

    @Test
    void should_return_the_correct_top_sellers_given_different_sales() {

        // Given
        var sales = List.of(
                KARINE_FROM_ROUEN_SOLD_5_T_233,
                CHARLES_FROM_PARIS_SOLD_10_T_233,
                KARINE_FROM_ROUEN_SOLD_5_S_888);

        var products = List.of(new Product("S888", 10, "nothing"),
                new Product("T233", 10,"ras"));
        var sut = new TopSellerAlgorithm(sales, products);

        // When
        var result = sut.run();

        // Then
        isTopSellerResultType(result);
        assertThat(result.getTopSellers().get(0))
                .hasFieldOrPropertyWithValue("seller", "Charles")
                .hasFieldOrPropertyWithValue("store", "PARIS")
                .hasFieldOrPropertyWithValue("totalSales", 100.00);

        assertThat(result.getTopSellers().get(1))
                .hasFieldOrPropertyWithValue("seller", "Karine")
                .hasFieldOrPropertyWithValue("store", "ROUEN")
                .hasFieldOrPropertyWithValue("totalSales", 100.00);
    }
}