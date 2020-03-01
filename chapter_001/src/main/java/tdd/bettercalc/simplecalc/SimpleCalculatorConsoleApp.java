package tdd.bettercalc.simplecalc;

import tdd.bettercalc.CalculatorUI;
import tdd.bettercalc.ScannerParser;

public class SimpleCalculatorConsoleApp {

    public static void main(String[] args) {
        CalculatorUI ui = new CalculatorUI(new SimpleCalculator(new ScannerParser(), System.out::println));
        ui.start();
    }

}
