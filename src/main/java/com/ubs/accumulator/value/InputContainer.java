package com.ubs.accumulator.value;

public class InputContainer {

    private String delimiter;

    private String operationalInput;

    public InputContainer(String delimiter, String operationalInput) {
        this.delimiter = delimiter;
        this.operationalInput = operationalInput;
    }

    public String getDelimiter() {
        return delimiter;
    }

    public void setDelimiter(String delimiter) {
        this.delimiter = delimiter;
    }

    public String getOperationalInput() {
        return operationalInput;
    }
}
