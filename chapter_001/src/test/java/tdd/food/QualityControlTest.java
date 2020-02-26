package tdd.food;

import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class QualityControlTest {

    private final Storage warehouse = new Warehouse();
    private final Storage shop = new Shop();
    private final Storage trash = new Trash();

    private final LocalDate yesterday = LocalDate.now().minusDays(1);
    private final LocalDate tomorrow = LocalDate.now().plusDays(1);
    private final LocalDate weekAgo = LocalDate.now().minusDays(7);
    private final LocalDate nextWeek = LocalDate.now().plusDays(7);

    private final Product milkBad = new Product("milk", yesterday, weekAgo, 50, 10);
    private final Product breadBad = new Product("bread", yesterday, weekAgo, 50, 10);
    private final Product jellySale = new Product("jelly", tomorrow, weekAgo, 50, 10);
    private final Product fishSale = new Product("fish", tomorrow, weekAgo, 100, 20);
    private final Product butterGood = new Product("butter", nextWeek, weekAgo, 100, 10);
    private final Product colaGood = new Product("cola", nextWeek, weekAgo, 40, 5);
    private final Product winePerfect = new Product("wine", nextWeek, yesterday, 1000, 7);
    private final Product eggsPerfect = new Product("eggs", nextWeek, yesterday, 80, 25);

    private final List<Product> badProducts = Arrays.asList(milkBad, breadBad);
    private final List<Product> productsWithDiscount = Arrays.asList(jellySale, fishSale);
    private final List<Product> goodProducts = Arrays.asList(butterGood, colaGood);
    private final List<Product> perfectProducts = Arrays.asList(winePerfect, eggsPerfect);

    private final List<Product> allProducts = new ArrayList<>(badProducts);

    {
        allProducts.addAll(productsWithDiscount);
        allProducts.addAll(goodProducts);
        allProducts.addAll(perfectProducts);
    }

    @Test
    public void checkProductsDistribution() {
        QualityControl qualityControl = new QualityControlImpl(warehouse, shop, trash);
        qualityControl.storeProducts(allProducts);
        assertEquals(0, trash.getProducts().size());
        assertEquals(4, shop.getProducts().size());
        assertEquals(2, warehouse.getProducts().size());

        assertEquals(perfectProducts, warehouse.getProducts());
        List<Product> shopProducts = new ArrayList<>(productsWithDiscount);
        shopProducts.addAll(goodProducts);
        assertEquals(shopProducts, shop.getProducts());
    }

    @Test
    public void whenProductAlmostExpiredDiscountApplied() {
        QualityControl qualityControl = new QualityControlImpl(warehouse, shop, trash);
        qualityControl.storeProduct(fishSale);
        qualityControl.storeProduct(jellySale);

        assertEquals(2, shop.getProducts().size());
        assertEquals(80, fishSale.getPrice(), 1E-6);
        assertEquals(45, jellySale.getPrice(), 1E-6);
    }
}