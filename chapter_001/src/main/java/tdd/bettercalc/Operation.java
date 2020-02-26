package tdd.bettercalc;

import java.util.Objects;

public class Operation {

    private boolean initial;
    private final String key;
    private final String name;
    private final Runnable operation;

    public Operation(String key, String name, Runnable operation, boolean initial) {
        this.key = key;
        this.name = name;
        this.operation = operation;
        this.initial = initial;
    }

    public Operation(String key, String name, Runnable operation) {
        this(key, name, operation, false);
    }

    public void run() {
        operation.run();
    }

    @Override
    public String toString() {
        return key + ": " + name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Operation operation = (Operation) o;
        return key.equals(operation.key);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key);
    }

    public String getKey() {
        return key;
    }

    public boolean isInitial() {
        return initial;
    }
}
