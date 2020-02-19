package tdd.calculator;

import java.util.Optional;

public class ParserImpl implements Parser {

    @Override
    public Optional<Double> parseInput(String input) {
        try {
            Double result = Double.parseDouble(input);
            return Optional.of(result);
        } catch (Exception e) {
            System.out.println("Unable to parse input, try again");
        }
        return Optional.empty();
    }


}
