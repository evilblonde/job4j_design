package tdd.calculator;

public class CalculatorImpl implements Calculator {

    private double result;
    private StringBuilder expression = new StringBuilder();

    @Override
    public void setInitialValue(double value) {
        this.result = value;
    }

    @Override
    public void add(double value) {
        prepareExpression(value, " + ");
        result += value;
        printExpression();
    }

    @Override
    public void subtract(double value) {
        prepareExpression(value, " - ");
        result -= value;
        printExpression();
    }

    @Override
    public void multiply(double value) {
        prepareExpression(value, " * ");
        result *= value;
        printExpression();
    }

    @Override
    public void divide(double value) {
        if (value == 0) {
            System.out.println("divide by zero!");
            return;
        }
        prepareExpression(value, " / ");
        result /= value;
        printExpression();
    }

    private void printExpression() {
        expression.append(result);
        System.out.println(expression.toString());
        expression.delete(0, expression.length());
    }

    private void prepareExpression(double value, String operation) {
        expression.append(result).append(operation).append(value).append(" = ");
    }
}
