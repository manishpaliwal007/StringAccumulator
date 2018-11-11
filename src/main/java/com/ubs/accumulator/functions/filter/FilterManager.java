package com.ubs.accumulator.functions.filter;

import java.util.List;

public class FilterManager {
    private List<Filters> filters;

    public FilterManager(List<Filters> filters) {
        super();
        this.filters = filters;
    }

    public List<Integer> filterData(List<Integer> numbers) {
        for (Filters filter : filters) {
            numbers = filter.filter(numbers);
        }
        return numbers;
    }
}
