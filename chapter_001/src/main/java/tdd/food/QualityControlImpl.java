package tdd.food;

import java.util.Collection;

public class QualityControlImpl implements QualityControl {

    private static final int WAREHOUSE_QUALITY = 75;
    private static final int DISCOUNT_QUALITY = 25;
    private static final int TRASH_QUALITY = 0;

    private final Storage warehouse;
    private final Storage shop;
    private final Storage trash;

    public QualityControlImpl(Storage warehouse, Storage shop, Storage trash) {
        this.warehouse = warehouse;
        this.shop = shop;
        this.trash = trash;
    }

    @Override
    public void storeProducts(Collection<Product> products) {
        products.forEach(this::storeProduct);
    }

    @Override
    public void storeProduct(Product product) {
        getAppropriateStorage(product).store(product);
    }

    private Storage getAppropriateStorage(Product product) {
        int quality = product.checkQuality();
        if (quality > WAREHOUSE_QUALITY) {
            return warehouse;
        } else if (quality > DISCOUNT_QUALITY) {
            return shop;
        } else if (quality > TRASH_QUALITY) {
            product.applyDiscount();
            return shop;
        }
        return trash;
    }


}
