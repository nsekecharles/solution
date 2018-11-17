package fnag.core.product;

public class Product {

    private final String reference;
    private final double price;
    private final String description;

    public Product(String reference, double price, String description) {
        this.reference = reference;
        this.price = price;
        this.description = description;
    }

    public String getReference() {
        return reference;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Product{" +
                "reference='" + reference + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                '}';
    }
}
