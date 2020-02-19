package tdd.calculator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class CalculatorTest {

    private final PrintStream systemOut = System.out;

    private ByteArrayOutputStream testOut;

    @Before
    public void setUpOutput() {
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
    }

    private String getOutput() {
        return testOut.toString().trim();
    }

    @After
    public void restoreSystemInputOutput() {
        System.setOut(systemOut);
    }

    @Test
    public void whenAddThenCorrectOutput() {
        Calculator calculator = new CalculatorImpl();
        calculator.setInitialValue(456);
        calculator.add(100);
        assertEquals("456.0 + 100.0 = 556.0", getOutput());
    }

    @Test
    public void whenSubtractThenCorrectOutput() {
        Calculator calculator = new CalculatorImpl();
        calculator.setInitialValue(456);
        calculator.subtract(100);
        assertEquals("456.0 - 100.0 = 356.0", getOutput());
    }

    @Test
    public void whenMultiplyThenCorrectOutput() {
        Calculator calculator = new CalculatorImpl();
        calculator.setInitialValue(456);
        calculator.multiply(10);
        assertEquals("456.0 * 10.0 = 4560.0", getOutput());
    }

    @Test
    public void whenDivideThenCorrectOutput() {
        Calculator calculator = new CalculatorImpl();
        calculator.setInitialValue(456);
        calculator.divide(2);
        assertEquals("456.0 / 2.0 = 228.0", getOutput());
    }

    @Test
    public void whenMultipleOperationsThenCorrectResult() {
        Calculator calculator = new CalculatorImpl();
        calculator.setInitialValue(456);
        calculator.divide(2);
        assertEquals("456.0 / 2.0 = 228.0", getOutput());
        calculator.add(200);
        assertEquals("456.0 / 2.0 = 228.0"
                + System.lineSeparator()
                + "228.0 + 200.0 = 428.0", getOutput());
        calculator.subtract(420);
        assertEquals("456.0 / 2.0 = 228.0"
                + System.lineSeparator()
                + "228.0 + 200.0 = 428.0"
                + System.lineSeparator()
                + "428.0 - 420.0 = 8.0", getOutput());
        calculator.multiply(8);
        assertEquals("456.0 / 2.0 = 228.0"
                + System.lineSeparator()
                + "228.0 + 200.0 = 428.0"
                + System.lineSeparator()
                + "428.0 - 420.0 = 8.0"
                + System.lineSeparator()
                + "8.0 * 8.0 = 64.0",
                getOutput());
    }

}