package tdd.food;

import java.util.*;

public class Warehouse implements Storage {

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
