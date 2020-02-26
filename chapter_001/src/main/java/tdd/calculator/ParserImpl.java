package tdd.calculator;

import java.util.Optional;
import java.util.Scanner;

public class ParserImpl implements Parser {

    @Override
    public Optional<Double> parseNumberFromInput(String input) {
        try {
            Double result = Double.parseDouble(input);
            return Optional.of(result);
        } catch (Exception e) {
            System.out.println("Unable to parse input, try again");
        }
        return Optional.empty();
    }

    @Override
    public double parseNumberFromScanner() {
        System.out.println("Enter number");
        while (true) {
            Scanner scanner = new Scanner(System.in);
            Optional<Double> doubleOptional = parseNumberFromInput(scanner.nextLine());
            if (doubleOptional.isPresent()) {
                return doubleOptional.get();
            }
        }
    }

}
