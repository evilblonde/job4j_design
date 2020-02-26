package tdd.bettercalc;

import java.util.Scanner;

public class CalculatorUI {

    private Calculator calculator;

    public CalculatorUI(Calculator calculator) {
        this.calculator = calculator;
    }

    public void start() {
        while (calculator.isRunning()) {
            showPossibleOperations();
            String command = new Scanner(System.in).nextLine();
            calculator.perform(command);
        }
    }

    private void showPossibleOperations() {
        System.out.println("Choose operation:");
        calculator.getOperations().forEach(o -> System.out.println(o.toString()));
    }

}
