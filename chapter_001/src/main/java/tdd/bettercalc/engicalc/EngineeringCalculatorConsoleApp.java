package tdd.bettercalc.engicalc;

import tdd.bettercalc.CalculatorUI;

public class EngineeringCalculatorConsoleApp {

    public static void main(String[] args) {
        CalculatorUI ui = new CalculatorUI(new EngineeringCalculator());
        ui.start();
    }

}
