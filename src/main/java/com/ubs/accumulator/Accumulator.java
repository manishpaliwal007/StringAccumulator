package com.ubs.accumulator;

import com.ubs.accumulator.exceptions.AccumulatorException;
import com.ubs.accumulator.functions.StringManager;
import com.ubs.accumulator.functions.filter.FilterManager;
import com.ubs.accumulator.functions.filter.Filters;
import com.ubs.accumulator.functions.filter.LessThanNumberFilter;
import com.ubs.accumulator.functions.validations.NegativeNumberValidation;
import com.ubs.accumulator.functions.validations.ValidationManager;
import com.ubs.accumulator.functions.validations.Validator;
import com.ubs.accumulator.service.AccumulatorService;
import com.ubs.accumulator.service.AccumulatorServiceImpl;
import com.ubs.accumulator.value.InputContainer;

import java.util.ArrayList;
import java.util.List;

/**
 * 1. Create a simple string calculator with a method int add(String numbers)
 * a. The method can take 0, 1 or 2 numbers and will return their sum (for an empty string it will return 0) for example “” or “1” or “1,2”
 * b. Start with the simplest test case of an empty string and move to 1 and 2 numbers.
 * 2. Allow the add method to handle an unknown amount of numbers.
 * 3. Allow the add method to handle new lines between numbers (instead of commas).
 * a. The following input is ok: “1\n2,3” (will equal 6).
 * b. The following input is NOT ok: “1,\n” (don’t need to prove it - just clarifying).
 * 4. Support different delimiters.
 * a. To change a delimiter, the beginning of the string will contain a separate line that looks like this: “//<delimiter>\n<numbers…>”, for example “//;\n1;2” should return 3 where the delimiter is ‘;’.
 * b. The first line is optional, all existing scenarios should still be supported.
 * 5. Calling add with a negative number will throw an exception with the message “negatives not allowed” - and the negative that was passed.
 * a. If there are multiple negatives, show all of them in the exception message.
 * 6. Numbers bigger than 1000 should be ignored, so adding 2 + 1001 = 2.
 * 7. Delimiters can be of any length, for example: “//***\n1***2***3” should return 6.
 * 8. Allow multiple delimiters like this: “//delim1|delim2\n” (with a “|” separating delimiters), for example “//*|%\n1*2%3” should return 6.
 * 9. Make sure you can also handle multiple delimiters with length longer than one character.
 */


public class Accumulator {

    public static String DEFAULT_DELIMITER = ",|\n";
    public static int MAX_NUMBER_LIMIT = 1000;

    private StringManager stringManager = null;
    private AccumulatorService accumulatorService = null;
    private ValidationManager validationManager = null;
    private FilterManager filterManager = null;


    public Accumulator() {
        stringManager = new StringManager(DEFAULT_DELIMITER);
        accumulatorService = new AccumulatorServiceImpl();
        validationRules();
        applicableFilters();
    }


    public long addition(String inputNumber) throws AccumulatorException {
        InputContainer inputMetaData = stringManager.delimiterOrchestrator(inputNumber);
        List<Integer> numbers = accumulatorService.extractNumbers(inputMetaData);
        validationManager.validateInput(numbers);
        numbers = filterManager.filterData(numbers);
        long sum = accumulatorService.addition(numbers);
        return sum;
    }


    private void validationRules() {
        List<Validator> validationRules = new ArrayList<Validator>();
        Validator validateNegativeNumbers = new NegativeNumberValidation();
        validationRules.add(validateNegativeNumbers);
        validationManager = new ValidationManager(validationRules);
    }

    private void applicableFilters() {
        List<Filters> numberFilters = new ArrayList<Filters>();
        Filters lessThanNumberFilter = new LessThanNumberFilter(MAX_NUMBER_LIMIT);
        numberFilters.add(lessThanNumberFilter);
        filterManager = new FilterManager(numberFilters);
    }
}
