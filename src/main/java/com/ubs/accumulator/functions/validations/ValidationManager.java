package com.ubs.accumulator.functions.validations;

import com.ubs.accumulator.exceptions.AccumulatorException;

import java.util.List;

public class ValidationManager {
    private List<Validator> validationRules;

    public ValidationManager(List<Validator> validationRules) {
        super();
        this.validationRules = validationRules;
    }

    public void validateInput(List<Integer> numbers) throws AccumulatorException {
        for (Validator validationRule : validationRules) {
            validationRule.isInputValid(numbers);
        }
    }
}
