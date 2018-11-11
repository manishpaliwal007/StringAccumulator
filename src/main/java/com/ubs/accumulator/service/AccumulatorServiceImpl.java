package com.ubs.accumulator.service;

import com.ubs.accumulator.value.InputContainer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AccumulatorServiceImpl implements AccumulatorService {

    @Override
    public List<Integer> extractNumbers(InputContainer data) {
        List<Integer> numbersList = new ArrayList<Integer>();
        Scanner scanner = new Scanner(data.getOperationalInput());

        try {
            scanner.useDelimiter(data.getDelimiter());

            while (scanner.hasNext()) {
                numbersList.add(Integer.parseInt(scanner.next()));
            }

        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
        return numbersList;
    }

    @Override
    public long addition(List<Integer> numbers) {
        long sum = 0;
        for (Integer integer : numbers) {
            sum += integer.longValue();
        }
        return sum;
    }

}
