package tdd.kiss;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class MaxMinTest {

    private final static List<Rectangle> VALID_LIST = new LinkedList<>();
    private final static Rectangle SMALLEST_RECTANGLE = new Rectangle(1, 3);
    private final static Rectangle BIGGEST_RECTANGLE = new Rectangle(100, 300);

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    static {
        VALID_LIST.add(new Rectangle(20, 3));
        VALID_LIST.add(SMALLEST_RECTANGLE);
        VALID_LIST.add(new Rectangle(1, 30));
        VALID_LIST.add(new Rectangle(11, 13));
        VALID_LIST.add(BIGGEST_RECTANGLE);
        VALID_LIST.add(new Rectangle(12, 23));
    }

    @Test
    public void whenMaxCalledThenCorrectValueReturned() throws Exception {
        MaxMin<Rectangle> maxMin = new MaxMin<>();
        assertEquals(BIGGEST_RECTANGLE, maxMin.max(VALID_LIST, new Rectangle.RectangleComparator()));
    }

    @Test
    public void whenMinCalledThenCorrectValueReturned() throws Exception {
        MaxMin<Rectangle> maxMin = new MaxMin<>();
        assertEquals(SMALLEST_RECTANGLE, maxMin.min(VALID_LIST, new Rectangle.RectangleComparator()));
    }

    @Test
    public void whenNullListInMaxThenThrowException() throws Exception {
        MaxMin<Rectangle> maxMin = new MaxMin<>();

        exceptionRule.expect(Exception.class);
        exceptionRule.expectMessage("Empty input list");

        maxMin.max(null, new Rectangle.RectangleComparator());
    }

    @Test
    public void whenNullListInMinThenThrowException() throws Exception {
        MaxMin<Rectangle> maxMin = new MaxMin<>();

        exceptionRule.expect(Exception.class);
        exceptionRule.expectMessage("Empty input list");

        maxMin.min(null, new Rectangle.RectangleComparator());
    }

    @Test
    public void whenEmptyListInMaxThenThrowException() throws Exception {
        MaxMin<Rectangle> maxMin = new MaxMin<>();

        exceptionRule.expect(Exception.class);
        exceptionRule.expectMessage("Empty input list");

        maxMin.max(Collections.emptyList(), new Rectangle.RectangleComparator());
    }

    @Test
    public void whenEmptyListInMinThenThrowException() throws Exception {
        MaxMin<Rectangle> maxMin = new MaxMin<>();

        exceptionRule.expect(Exception.class);
        exceptionRule.expectMessage("Empty input list");

        maxMin.min(Collections.emptyList(), new Rectangle.RectangleComparator());
    }

    @Test
    public void whenNullValueInListNoExceptionThrown() throws Exception {
        MaxMin<Rectangle> maxMin = new MaxMin<>();
        VALID_LIST.add(null);
        assertEquals(BIGGEST_RECTANGLE, maxMin.max(VALID_LIST, new Rectangle.RectangleComparator()));
        assertEquals(SMALLEST_RECTANGLE, maxMin.min(VALID_LIST, new Rectangle.RectangleComparator()));
    }

    @Test
    public void whenNoComparatorInMaxThenThrowException() throws Exception {
        MaxMin<Rectangle> maxMin = new MaxMin<>();

        exceptionRule.expect(Exception.class);
        exceptionRule.expectMessage("No comparator provided");

        maxMin.max(VALID_LIST, null);
    }

    @Test
    public void whenNoComparatorInMinThenThrowException() throws Exception {
        MaxMin<Rectangle> maxMin = new MaxMin<>();

        exceptionRule.expect(Exception.class);
        exceptionRule.expectMessage("No comparator provided");

        maxMin.max(VALID_LIST, null);
    }
}