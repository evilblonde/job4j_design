package tdd.bettercalc;

import java.util.Collection;

public interface Calculator {

    Collection<Operation> getOperations();

    boolean isRunning();

    void perform(String command);

}
