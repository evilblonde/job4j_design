package tdd.bettercalc;

import java.util.Optional;

public interface Parser {

    Optional<Double> parseNumberFromInput(String input);

    double parseNumberFromSource();

}
