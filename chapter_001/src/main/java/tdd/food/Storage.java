package tdd.food;

import java.util.Collection;

public interface Storage {

    void store(Product product);

    Collection<Product> getProducts();

}
