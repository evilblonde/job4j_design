package tdd.bettercalc;

import org.junit.Test;
import tdd.bettercalc.engicalc.EngineeringCalculator;
import tdd.bettercalc.simplecalc.SimpleCalculator;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class CalculatorTest {

    @Test
    public void simpleCalculatorCommandSequenceCorrect() {
        Set<String> output = new HashSet<>();
        ListParser listParser = new ListParser();
        listParser.setSource(new String[]{"20", "30", "10", "400", "25"});
        SimpleCalculator calculator = new SimpleCalculator(listParser, output::add);
        calculator.perform("r");
        calculator.perform("+");
        assertEquals(50, calculator.getResult(), 1e-6);
        assertTrue(output.contains("20.0 + 30.0 = 50.0"));
        calculator.perform("*");
        assertEquals(500d, calculator.getResult(), 1e-6);
        assertTrue(output.contains("50.0 * 10.0 = 500.0"));
        calculator.perform("-");
        assertEquals(100d, calculator.getResult(), 1e-6);
        assertTrue(output.contains("500.0 - 400.0 = 100.0"));
        calculator.perform("/");
        assertEquals(4d, calculator.getResult(), 1e-6);
        assertTrue(output.contains("100.0 / 25.0 = 4.0"));
    }

    @Test
    public void engineeringCalculatorCommandSequenceCorrect() {
        Set<String> output = new HashSet<>();
        ListParser listParser = new ListParser();
        listParser.setSource(new String[]{"20", "30", "10", "400", "25"});
        EngineeringCalculator calculator = new EngineeringCalculator(listParser, output::add);
        calculator.perform("r");
        calculator.perform("+");
        assertEquals(50d, calculator.getResult(), 1e-6);
        assertTrue(output.contains("20.0 + 30.0 = 50.0"));
        calculator.perform("*");
        assertEquals(500d, calculator.getResult(), 1e-6);
        assertTrue(output.contains("50.0 * 10.0 = 500.0"));
        calculator.perform("-");
        assertEquals(100d, calculator.getResult(), 1e-6);
        assertTrue(output.contains("500.0 - 400.0 = 100.0"));
        calculator.perform("/");
        assertEquals(4d, calculator.getResult(), 1e-6);
        assertTrue(output.contains("100.0 / 25.0 = 4.0"));

        listParser.setSource(new String[]{"0", "1.5708"});
        calculator.perform("r");
        calculator.perform("s");
        assertTrue(output.contains("sin(0.0) = 0.0"));
        calculator.perform("c");
        assertTrue(output.contains("cos(0.0) = 1.0"));
        calculator.perform("r");
        calculator.perform("c");
        assertEquals(0, calculator.getResult(), 1e-5);
    }
}