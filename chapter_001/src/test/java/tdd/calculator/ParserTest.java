package tdd.calculator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
        Parser parser = new ParserImpl();
        Optional<Double> result = parser.parseNumberFromInput("123");
        assertTrue(result.isPresent());
        assertEquals(123d, result.get(), 1E-6);
    }

    @Test
    public void whenBadInputThenAskToRetry() {
        Parser parser = new ParserImpl();
        Optional<Double> result = parser.parseNumberFromInput("asd");
        assertFalse(result.isPresent());
        assertEquals("Unable to parse input, try again", getOutput().trim());
    }
}