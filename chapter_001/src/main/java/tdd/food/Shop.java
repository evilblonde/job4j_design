package tdd.food;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Shop implements Storage {

    private final List<Product> products = new ArrayList<>();

    @Override
    public void store(Product product) {
        products.add(product);
    }

    @Override
    public Collection<Product> getProducts() {
        return products;
    }

}
