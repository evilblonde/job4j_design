package tdd.food;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class ExpirableTest {

    private LocalDate now;
    private LocalDate nowPlusYear;
    private LocalDate nowMinusYear;

    @Before
    public void initDates() {
        now = LocalDate.now();
        nowPlusYear = now.plusYears(1);
        nowMinusYear = now.minusYears(1);
    }

    @Test
    public void whenProductIsNewQualityIsHundred() {
        Expirable expirable = new Product("a", nowPlusYear, now, 1000, 15);
        assertEquals(100, expirable.checkQuality());
    }

    @Test
    public void whenProductIsHalfExpiredQualityIsFifty() {
        Expirable expirable = new Product("a", nowPlusYear, nowMinusYear, 1000, 15);
        assertEquals(50, expirable.checkQuality());
    }

    @Test
    public void whenProductIsExpiredQualityIsMinusOne() {
        Expirable expirable = new Product("a", now.minusDays(1), nowMinusYear, 1000, 15);
        assertEquals(-1, expirable.checkQuality());
    }

}