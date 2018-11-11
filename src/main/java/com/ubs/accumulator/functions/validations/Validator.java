package com.ubs.accumulator.functions.validations;

import com.ubs.accumulator.exceptions.AccumulatorException;

import java.util.List;

public interface Validator {

    void isInputValid(List<Integer> numbers) throws AccumulatorException;
}
