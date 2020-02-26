package tdd.food;

import java.util.Collection;
import java.util.Collections;

public class Trash implements Storage {

    @Override
    public void store(Product product) {

    }

    @Override
    public Collection<Product> getProducts() {
        return Collections.emptyList();
    }

}
