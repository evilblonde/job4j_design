package tdd.calculator;

import java.util.Optional;

public interface Parser {

    Optional<Double> parseNumberFromInput(String input);

    double parseNumberFromScanner();

}
