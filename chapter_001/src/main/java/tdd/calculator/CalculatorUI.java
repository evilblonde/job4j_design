package tdd.calculator;

import java.util.Optional;
import java.util.Scanner;

public class CalculatorUI {

    private Calculator calculator;
    private Parser parser;

    public CalculatorUI(Calculator calculator, Parser parser) {
        this.calculator = calculator;
        this.parser = parser;
    }

    public static void main(String[] args) {
        CalculatorUI ui = new CalculatorUI(new CalculatorImpl(), new ParserImpl());
        ui.openStartMenu();
    }

    private void openStartMenu() {
        while (true) {
            System.out.println("Enter a number, or type exit to close application");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            if ("exit".equals(input)) {
                break;
            }
            Optional<Double> result = parser.parseNumberFromInput(input);
            if (result.isPresent()) {
                calculator.setInitialValue(result.get());
                openCalculatorMenu();
            }
        }
    }

    private void openCalculatorMenu() {
        boolean operating = true;
        while (operating) {
            System.out.println("Choose operation (+,-,*,/) or type stop to start over");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            switch (input) {
                case "+":
                    calculator.add(parser.parseNumberFromScanner());
                    break;
                case "-":
                    calculator.subtract(parser.parseNumberFromScanner());
                    break;
                case "*":
                    calculator.multiply(parser.parseNumberFromScanner());
                    break;
                case "/":
                    calculator.divide(parser.parseNumberFromScanner());
                    break;
                case "stop":
                    operating = false;
                    break;
                default:
                    System.out.println("Unknown command, try again");
            }
        }
    }
}
