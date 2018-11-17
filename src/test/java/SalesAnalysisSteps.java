import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import fnag.core.analyse.SalesAnalyser;
import fnag.core.analyse.result.AnalyseResult;
import fnag.core.analyse.result.AnalyseResultToStringMapper;
import fnag.core.analyse.topsales.TopSalesAlgorithm;
import fnag.core.analyse.topseller.TopSellerAlgorithm;
import fnag.core.product.Product;
import fnag.core.product.ProductMapper;
import fnag.core.sale.Sale;
import fnag.core.sale.SaleMapper;
import io.cucumber.datatable.DataTable;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

public class SalesAnalysisSteps {

    public static final String PARAMETER_DELIMITER = "\\|";
    private List<Product> products = new ArrayList<>();
    private List<Sale> sells = new ArrayList<>();

    private ProductMapper productMapper = new ProductMapper();
    private SaleMapper saleMapper = new SaleMapper();

    private SalesAnalyser analyser = new SalesAnalyser();
    private List<AnalyseResult> analyse;

    @Given("^a product (.*)")
    public void aProduct(String productLine) throws Throwable {
        products.add(productMapper.fromString(productLine, PARAMETER_DELIMITER));
    }

    @Given("^a sale (.*)")
    public void aSell(String sellLine) throws Throwable {
        sells.add(saleMapper.fromString(sellLine, PARAMETER_DELIMITER));
    }

    @When("analyse sales")
    public void computeSellAnalysis() {
        analyse = analyser.analyseWith(
                new TopSalesAlgorithm(sells),
                new TopSellerAlgorithm(sells, products));
    }

    @Then("the analyse result should display")
    public void theAnalysisResultLinesShouldBe(DataTable resultLines) {

        var mapper = new AnalyseResultToStringMapper(products);

        final var actualResultsLines = analyse.stream()
                .map(mapper::map)
                .collect(toList());

        final var expectedResult = resultLines.asList();
        assertThat(actualResultsLines).containsExactlyElementsOf(expectedResult);

    }

}
