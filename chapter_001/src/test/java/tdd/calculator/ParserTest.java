package tdd.calculator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import tdd.bettercalc.ListParser;
import tdd.bettercalc.Parser;
import tdd.bettercalc.ScannerParser;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Optional;

import static org.junit.Assert.*;

public class ParserTest {

    private final PrintStream systemOut = System.out;

    private ByteArrayOutputStream testOut;

    @Before
    public void setUpOutput() {
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
    }

    private String getOutput() {
        return testOut.toString();
    }

    @After
    public void restoreSystemInputOutput() {
        System.setOut(systemOut);
    }

    @Test
    public void whenNumberInInputParseSuccessfully() {
        Parser parser = new ScannerParser();
        Optional<Double> result = parser.parseNumberFromInput("123");
        assertTrue(result.isPresent());
        assertEquals(123d, result.get(), 1E-6);
    }

    @Test
    public void whenBadInputThenAskToRetry() {
        Parser parser = new ScannerParser();
        Optional<Double> result = parser.parseNumberFromInput("asd");
        assertFalse(result.isPresent());
        assertEquals("Unable to parse input, try again", getOutput().trim());
    }

    @Test
    public void parseSeveralNumbersFromInputArray() {
        ListParser parser = new ListParser();
        parser.setSource(new String[]{"123", "333", "4ooo", "4321"});
        assertEquals(123, parser.parseNumberFromSource(), 1e-6);
        assertEquals(333, parser.parseNumberFromSource(), 1e-6);
        assertEquals(4321, parser.parseNumberFromSource(), 1e-6);
    }
}