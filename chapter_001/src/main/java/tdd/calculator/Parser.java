package tdd.calculator;

import java.util.Optional;

public interface Parser {

    Optional<Double> parseInput(String input);

}
