package tdd.bettercalc;

import java.util.Optional;

public class ListParser extends ScannerParser {

    private String[] commands;
    private int commandNumber;

    public void setSource(String[] commands) {
        this.commands = commands;
        commandNumber = 0;
    }

    @Override
    public double parseNumberFromSource() {
        if (commandNumber >= commands.length) {
            throw new RuntimeException("Unable to get next command");
        }
        for (int i = commandNumber; i < commands.length; i++) {
            Optional<Double> inputOptional = parseNumberFromInput(commands[i]);
            if (inputOptional.isPresent()) {
                commandNumber = i + 1;
                return inputOptional.get();
            }
        }
        throw new RuntimeException("No valid commands");
    }
}
