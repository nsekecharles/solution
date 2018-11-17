package fnag.core;

import fnag.core.product.Product;
import fnag.core.sale.Sale;

import java.util.List;

public interface AnalysisDataSource {
    List<Product> getProducts();
    List<Sale> getSales();
}
