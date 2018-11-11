package com.ubs.accumulator.functions.filter;

import java.util.ArrayList;
import java.util.List;

public class LessThanNumberFilter implements Filters {

    private int maxNumber;

    public LessThanNumberFilter(int maxNumber) {
        this.maxNumber = maxNumber;
    }

    @Override
    public List<Integer> filter(List<Integer> numbers) {
        List<Integer> validNumbers = new ArrayList<Integer>();

        for (Integer number : numbers) {
            if(number <= maxNumber) {
                validNumbers.add(number);
            }
        }

        return validNumbers;
    }

}
