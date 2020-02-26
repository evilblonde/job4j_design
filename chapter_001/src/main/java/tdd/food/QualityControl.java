package tdd.food;

import java.util.Collection;

public interface QualityControl {

    void storeProducts(Collection<Product> products);

    void storeProduct(Product product);

}
