package tdd.bettercalc.engicalc;

import tdd.bettercalc.CalculatorUI;
import tdd.bettercalc.ScannerParser;

public class EngineeringCalculatorConsoleApp {

    public static void main(String[] args) {
        CalculatorUI ui = new CalculatorUI(new EngineeringCalculator(new ScannerParser(), System.out::println));
        ui.start();
    }

}
