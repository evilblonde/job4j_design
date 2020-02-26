package tdd.food;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class Product implements Expirable {

    private final String name;
    private final LocalDate expireDate;
    private final LocalDate createDate;
    private final long lifespanInDays;
    private double price;
    private final int discount;
    private boolean discounted;

    public Product(String name, LocalDate expireDate, LocalDate createDate, double price, int discount) {
        this.name = name;
        this.expireDate = expireDate;
        this.createDate = createDate;
        this.price = price;
        this.discount = discount;
        this.lifespanInDays = daysBetween(createDate, expireDate);
    }

    private long daysBetween(LocalDate past, LocalDate future) {
        return past.until(future, ChronoUnit.DAYS);
    }

    public String getName() {
        return name;
    }

    public LocalDate getExpireDate() {
        return expireDate;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public double getPrice() {
        return price;
    }

    public void applyDiscount() {
        if (!discounted) {
            this.price = price * (100 - discount) / 100;
            this.discounted = true;
        }
    }

    public int checkQuality() {
        LocalDate now = LocalDate.now();
        if (now.isAfter(expireDate)) {
            return -1;
        }
        return (int) (daysBetween(now, expireDate) * 100 / lifespanInDays);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Product product = (Product) o;
        return name.equals(product.name)
                && expireDate.equals(product.expireDate)
                && createDate.equals(product.createDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, expireDate, createDate);
    }
}
