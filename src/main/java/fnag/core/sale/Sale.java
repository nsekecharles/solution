package fnag.core.sale;

public class Sale {

    private final String storeName;
    private final String seller;
    private final String productReference;
    private final int quantity;

    public Sale(String storeName, String seller, String productReference, int quantity) {
        this.storeName = storeName;
        this.seller = seller;
        this.productReference = productReference;
        this.quantity = quantity;
    }

    public String getStoreName() {
        return storeName;
    }

    public String getSeller() {
        return seller;
    }

    public String getProductReference() {
        return productReference;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "SellResult{" +
                "storeName='" + storeName + '\'' +
                ", seller='" + seller + '\'' +
                ", productReference='" + productReference + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
