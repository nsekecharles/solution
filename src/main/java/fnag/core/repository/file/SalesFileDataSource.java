package fnag.core.repository.file;

import fnag.core.product.Product;
import fnag.core.product.ProductMapper;
import fnag.core.repository.AnalysisDataSource;
import fnag.core.sale.Sale;
import fnag.core.sale.SaleMapper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class SalesFileDataSource implements AnalysisDataSource {

    public static final String PARAMETER_DELIMITER = "\\|";
    public static final int NUMBER_OF_PRODUCTS_MAX = 100;
    public static final int NUMBER_OF_SALES_MAX = 1000;

    private final ProductMapper productMapper = new ProductMapper();
    private final SaleMapper saleMapper = new SaleMapper();

    private List<Product> products = new ArrayList<>();
    private List<Sale> sales = new ArrayList<>();

    public SalesFileDataSource(String fileName) throws IOException {
        initializeData(fileName);
    }

    private void initializeData(String fileName) throws IOException {

        final ClassLoader classLoader = getClass().getClassLoader();
        final URL resource = classLoader.getResource(fileName);

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(resource.getFile()))) {
            readProducts(bufferedReader);
            readSales(bufferedReader);
        }
    }

    private void readProducts(BufferedReader bufferedReader) throws IOException, SalesFileDataSourceException {
        int numberOfProducts = Integer.parseInt(bufferedReader.readLine());
        if(numberOfProducts < NUMBER_OF_PRODUCTS_MAX) {
            for(int i = 1; i <= numberOfProducts; i++) {
                products.add(productMapper.fromString(bufferedReader.readLine(), PARAMETER_DELIMITER));
            }
        } else {
            throw new SalesFileDataSourceException("products to read should be less than : " + NUMBER_OF_PRODUCTS_MAX);
        }
    }

    private void readSales(BufferedReader bufferedReader) throws IOException, SalesFileDataSourceException {
        int numberOfsales = Integer.parseInt(bufferedReader.readLine());
        if(numberOfsales < NUMBER_OF_SALES_MAX) {
            for(int i = 1; i <= numberOfsales; i++) {
                sales.add(saleMapper.fromString(bufferedReader.readLine(), PARAMETER_DELIMITER));
            }
        } else {
            throw new SalesFileDataSourceException("sales to read should be less than : " + NUMBER_OF_SALES_MAX);
        }
    }

    @Override
    public List<Product> getProducts() {
        return products;
    }

    @Override
    public List<Sale> getSales() {
        return sales;
    }
}
