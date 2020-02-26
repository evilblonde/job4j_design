package tdd.bettercalc.simplecalc;

import tdd.bettercalc.Calculator;
import tdd.bettercalc.Operation;
import tdd.calculator.Parser;
import tdd.calculator.ParserImpl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class SimpleCalculator implements Calculator {

    private final StringBuilder expression = new StringBuilder();
    private final Parser parser = new ParserImpl();
    private Map<String, Operation> operations = new HashMap<>();
    private Double result;
    private boolean running;

    public SimpleCalculator() {
        running = true;
        addOperation(new Operation("r", "enter value to operate with", this::setValue, true));
        addOperation(new Operation("e", "exit", this::exit, true));
        addOperation(new Operation("+", "add", this::add));
        addOperation(new Operation("-", "subtract", this::subtract));
        addOperation(new Operation("*", "multiply", this::multiply));
        addOperation(new Operation("/", "divide", this::divide));
    }

    private void exit() {
        this.running = false;
    }

    public Double getResult() {
        return result;
    }

    public void setResult(Double result) {
        this.result = result;
    }

    public StringBuilder getExpression() {
        return expression;
    }

    private void setValue() {
        result = parser.parseNumberFromScanner();
    }

    public void addOperation(Operation operation) {
        operations.put(operation.getKey(), operation);
    }

    public boolean isRunning() {
        return running;
    }

    @Override
    public void perform(String command) {
        Operation operation = operations.get(command);
        if (operation != null && isPossibleToPerform(operation)) {
            operation.run();
        } else {
            System.out.println("invalid command, try again");
        }
    }

    @Override
    public Collection<Operation> getOperations() {
        if (result == null) {
            return operations.values().stream().filter(Operation::isInitial).collect(Collectors.toList());
        }
        return operations.values();
    }

    private void add() {
        double value = parser.parseNumberFromScanner();
        prepareExpression(value, " + ");
        result += value;
        printAndClearExpression();
    }

    private void subtract() {
        double value = parser.parseNumberFromScanner();
        prepareExpression(value, " - ");
        result -= value;
        printAndClearExpression();
    }

    private void multiply() {
        double value = parser.parseNumberFromScanner();
        prepareExpression(value, " * ");
        result *= value;
        printAndClearExpression();
    }

    private void divide() {
        double value = parser.parseNumberFromScanner();
        prepareExpression(value, " / ");
        result /= value;
        printAndClearExpression();
    }

    private void prepareExpression(Double value, String operation) {
        expression.append(result).append(operation).append(value).append(" = ");
    }

    public void printAndClearExpression() {
        expression.append(result);
        System.out.println(expression.toString());
        expression.delete(0, expression.length());
    }

    protected boolean isPossibleToPerform(Operation operation) {
        return result != null || operation.isInitial();
    }

}
