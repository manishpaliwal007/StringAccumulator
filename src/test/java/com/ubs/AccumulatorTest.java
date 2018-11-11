package com.ubs;

import com.ubs.accumulator.Accumulator;
import com.ubs.accumulator.exceptions.AccumulatorException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class AccumulatorTest {


    Accumulator accumulator;

    @Before
    public void setUp() {
        accumulator = new Accumulator();
    }

    @After
    public void tearDown() {
        accumulator = null;
    }


    @Test
    public void whenEmptyStringIsPassedThenAdditionShouldBeZero() throws AccumulatorException {
        long expectedSum = 0;
        String input = "";
        assertTrue(expectedSum == accumulator.addition(input));
    }

    @Test
    public void whenSimpleTwoNumbersArePassedThenAdditionShouldBeCorrect() throws AccumulatorException {
        long expectedSum = 3;
        String input = "1,2";
        assertTrue(expectedSum == accumulator.addition(input));
    }

    @Test
    public void whenNewLineIsPassedThenAdditionShouldBeCorrect() throws AccumulatorException {
        long expectedSum = 3;
        String input = "1\n2";
        assertTrue(expectedSum == accumulator.addition(input));
    }

    @Test
    public void whenNewLineAndCommaUsedAsDelimitersThenAdditionShouldBeCorrect() throws AccumulatorException {
        long expectedSum = 6;
        String input = "1\n2,3";
        assertTrue(expectedSum == accumulator.addition(input));
    }

    @Test(expected = NumberFormatException.class)
    public void whenInvalidInputIsPassedExceptionShouldBeThrown() throws AccumulatorException {
        long expectedSum = 6;
        String input = "1,\n";
        assertTrue(expectedSum == accumulator.addition(input));
    }

    @Test
    public void whenSemicolonIsPassedAsDelimiterThenAdditionShouldBeCorrect() throws AccumulatorException {
        long expectedSum = 3;
        String input = "//;\n1;2";
        assertTrue(expectedSum == accumulator.addition(input));
    }

    @Test
    public void whenNegativeNumbersArePassedAsCustomExceptionShouldBeThrown() {
        String input = "//;\n1;2;-3;-1";
        try {
            accumulator.addition(input);
            fail();
        } catch (AccumulatorException e) {
            assertEquals("Negative not allowed : -3 -1", e.getMessage());
        }
    }

    @Test
    public void whenNumbersMoreThousandArePassedThenTheseNumbersShouldBeIgnored() throws AccumulatorException {
        long expectedSum = 6;
        String input = "1,2,3\n1001,10002";
        assertTrue(expectedSum == accumulator.addition(input));
    }

    @Test
    public void whenDelimitersWithMultipleLengthsArePassedThenTheseAdditionShouldBeCorrect() throws AccumulatorException {
        long expectedSum = 6;
        String input = "//***\n1***2***3";
        assertTrue(expectedSum == accumulator.addition(input));
    }

    @Test
    public void whenInputAsNullPassedThenAdditionShouldBeZero() throws AccumulatorException {
        long expectedSum = 0;
        String input = null;
        assertTrue(expectedSum == accumulator.addition(input));
    }

    @Test
    public void whenMultipleDelimitersWithSingleLengthsArePassedThenTheseAdditionShouldBeCorrect() throws AccumulatorException {
        long expectedSum = 6;
        String input = "//*|,\n1*2,3";
        assertTrue(expectedSum == accumulator.addition(input));
    }

    @Test
    public void whenMultipleDelimitersWithMultipleLengthsArePassedThenTheseAdditionShouldBeCorrect() throws AccumulatorException {
        long expectedSum = 8;
        String input = "//**|,,\n1**2,,5";
        assertTrue(expectedSum == accumulator.addition(input));
    }
}
