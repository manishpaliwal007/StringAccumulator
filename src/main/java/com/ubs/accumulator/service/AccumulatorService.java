package com.ubs.accumulator.service;

import com.ubs.accumulator.value.InputContainer;

import java.util.List;

public interface AccumulatorService {

    List<Integer> extractNumbers(InputContainer data);
    long addition(List<Integer> numbers);
}
