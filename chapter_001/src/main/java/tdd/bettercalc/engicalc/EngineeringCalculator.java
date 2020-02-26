package tdd.bettercalc.engicalc;

import tdd.bettercalc.Calculator;
import tdd.bettercalc.Operation;
import tdd.bettercalc.simplecalc.SimpleCalculator;

import java.util.Collection;

public class EngineeringCalculator implements Calculator {

    SimpleCalculator simpleCalculator = new SimpleCalculator();

    public EngineeringCalculator() {
        simpleCalculator.addOperation(new Operation("s", "sin", this::sin));
        simpleCalculator.addOperation(new Operation("c", "cos", this::cos));
        simpleCalculator.addOperation(new Operation("t", "tan", this::tan));
    }

    public Double getResult() {
        return simpleCalculator.getResult();
    }

    public void setResult(Double result) {
        simpleCalculator.setResult(result);
    }

    private void sin() {
        getExpression().append("sin(").append(getResult()).append(") = ");
        setResult(Math.sin(getResult()));
        printAndClearExpression();
    }

    private void cos() {
        getExpression().append("cos(").append(getResult()).append(") = ");
        setResult(Math.cos(getResult()));
        printAndClearExpression();
    }

    private void tan() {
        getExpression().append("tan(").append(getResult()).append(") = ");
        setResult(Math.tan(getResult()));
        printAndClearExpression();
    }

    @Override
    public Collection<Operation> getOperations() {
        return simpleCalculator.getOperations();
    }

    @Override
    public boolean isRunning() {
        return simpleCalculator.isRunning();
    }

    @Override
    public void perform(String command) {
        simpleCalculator.perform(command);
    }

    public void printAndClearExpression() {
        simpleCalculator.printAndClearExpression();
    }

    public StringBuilder getExpression() {
        return simpleCalculator.getExpression();
    }

}
