package fnag.core.analyse.topseller;

public class TopSeller {

    private final String store;
    private final String seller;
    private final double totalSales;

    public TopSeller(String store, String seller, double totalSales) {
        this.store = store;
        this.seller = seller;
        this.totalSales = totalSales;
    }

    public String getStore() {
        return store;
    }

    public String getSeller() {
        return seller;
    }

    public double getTotalSales() {
        return totalSales;
    }

    @Override
    public String toString() {
        return "TopSeller{" +
                "store='" + store + '\'' +
                ", seller='" + seller + '\'' +
                ", totalSales=" + totalSales +
                '}';
    }
}
