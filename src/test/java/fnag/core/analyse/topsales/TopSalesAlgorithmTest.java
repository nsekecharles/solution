package fnag.core.analyse.topsales;

import fnag.core.analyse.AnalyseType;
import fnag.core.sale.Sale;
import fnag.core.sale.SalesTestUtils;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class TopSalesAlgorithmTest {

    @Test
    void should_return_empty_analysis_result_given_an_empty_sales_list() {

        // Given
        List<Sale> sales = Collections.emptyList();
        var topSalesAlgorithm = new TopSalesAlgorithm(sales);

        // When
        final var topSalesAnalyseResult = topSalesAlgorithm.run();

        // Assert
        isTopSaleResultType(topSalesAnalyseResult);
        assertThat(topSalesAnalyseResult)
                .hasFieldOrPropertyWithValue("quantitySold", 0)
                .hasFieldOrPropertyWithValue("productsRefence", Collections.EMPTY_LIST);
    }

    @Test
    void should_return_analysis_result_with_one_sale_given_different_sales() {

        // Given
        var sales = List.of(
                SalesTestUtils.CHARLES_FROM_PARIS_SOLD_10_T_233,
                SalesTestUtils.KARINE_FROM_ROUEN_SOLD_5_T_233,
                SalesTestUtils.KARINE_FROM_ROUEN_SOLD_5_S_888,
                SalesTestUtils.CHARLES_FROM_PARIS_SOLD_4_S_888);
        var topSalesAlgorithm = new TopSalesAlgorithm(sales);

        // When
        final var topSalesAnalyseResult = topSalesAlgorithm.run();

        // Assert
        isTopSaleResultType(topSalesAnalyseResult);
        assertThat(topSalesAnalyseResult)
                .hasFieldOrPropertyWithValue("quantitySold", 15)
                .hasFieldOrPropertyWithValue("productsRefence", List.of("T233"));
    }

    @Test
    void should_return_analysis_result_with_exequo_sale_given_different_sales() {

        // Given
        var sales = List.of(
                SalesTestUtils.CHARLES_FROM_PARIS_SOLD_10_T_233,
                SalesTestUtils.KARINE_FROM_ROUEN_SOLD_5_T_233,
                SalesTestUtils.KARINE_FROM_ROUEN_SOLD_5_S_888,
                SalesTestUtils.CHARLES_FROM_PARIS_SOLD_4_S_888,
                SalesTestUtils.CHARLES_FROM_PARIS_SOLD_6_S_888);

        var topSalesAlgorithm = new TopSalesAlgorithm(sales);

        // When
        final var topSalesAnalyseResult = topSalesAlgorithm.run();

        // Assert
        isTopSaleResultType(topSalesAnalyseResult);
        assertThat(topSalesAnalyseResult.getQuantitySold()).isEqualTo(15);
        assertThat(topSalesAnalyseResult.getProductsRefence())
                .containsExactlyInAnyOrderElementsOf(List.of("T233","S888"));
    }

    private static void isTopSaleResultType(TopSalesAnalyseResult result) {
        assertThat(result.getAnalyseType()).isEqualTo(AnalyseType.TOPSALE);
    }
}