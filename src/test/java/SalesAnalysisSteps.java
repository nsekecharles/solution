import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import fnag.core.analyse.SalesAnalyser;
import fnag.core.analyse.result.AnalyseResult;
import fnag.core.analyse.result.AnalysisResultToStringMapper;
import fnag.core.analyse.topsales.TopSalesAlgorithm;
import fnag.core.analyse.topseller.TopSellerAlgorithm;
import fnag.core.product.Product;
import fnag.core.product.ProductMapper;
import fnag.core.sale.Sale;
import fnag.core.sale.SaleMapper;
import io.cucumber.datatable.DataTable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class SalesAnalysisSteps {

    private List<Product> products = new ArrayList<>();
    private List<Sale> sells = new ArrayList<>();
    private ProductMapper productMapper = new ProductMapper();
    private SaleMapper saleMapper = new SaleMapper();
    private SalesAnalyser analyser;
    private List<AnalyseResult> analyse;

    @Given("^a product (.*)")
    public void aProduct(String productLine) throws Throwable {
        products.add(productMapper.fromString(productLine, "\\|"));
    }

    @Given("^a sale (.*)")
    public void aSell(String sellLine) throws Throwable {
        sells.add(saleMapper.fromString(sellLine, "\\|"));
    }

    @When("analyse sells")
    public void computeSellAnalysis() {
        analyser = new SalesAnalyser();

        analyse = analyser.analyseWith(new TopSalesAlgorithm(sells), new TopSellerAlgorithm(sells, products));
    }

    @Then("the analysis result should display")
    public void theAnalysisResultLinesShouldBe(DataTable resultLines) {

        var mapper = new AnalysisResultToStringMapper(products);

        final var actualResultsLines = analyse.stream().map(r -> mapper.map(r)).collect(Collectors.toList());

        final var expectedResult = resultLines.asList();
        assertThat(actualResultsLines).containsExactlyElementsOf(expectedResult);

    }

}
