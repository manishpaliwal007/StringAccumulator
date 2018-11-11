package com.ubs.accumulator.functions;

import com.ubs.accumulator.value.InputContainer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class StringManager {

    String REGEX_DELIMITER_EXTRACTOR = "//(.*?)\\n";
    String EMPTY_STRING = "";


    private String defaultDelimiter;

    public StringManager(String defaultDelimiter) {
        this.defaultDelimiter = defaultDelimiter;
    }

    public InputContainer delimiterOrchestrator(String input) {
        if(input == null) {
            input = EMPTY_STRING;
        }

        InputContainer inputContainer = extractRawDelimiters(input);
        String rawDelimiter = inputContainer.getDelimiter();
        String operationalDelimiter = escapeMetaCharacters(rawDelimiter);
        inputContainer.setDelimiter(operationalDelimiter);
        return inputContainer;
    }

    public InputContainer extractRawDelimiters(String numbers) {
        String delimiter = defaultDelimiter;
        String operationalInput = numbers;

        Pattern pattern = Pattern.compile(REGEX_DELIMITER_EXTRACTOR);
        Matcher matcher = pattern.matcher(numbers);
        while (matcher.find()) {
            delimiter = matcher.group(1);
            operationalInput = numbers.replaceFirst(REGEX_DELIMITER_EXTRACTOR,EMPTY_STRING);
        }

        InputContainer inputContainer = new InputContainer(delimiter, operationalInput);
        return inputContainer;
    }

    public String escapeMetaCharacters(String inputString) {
        final String[] metaCharacters
                = { "\\", "^", "$", "{", "}", "[", "]", "(", ")", ".", "*", "+", "?", "<", ">", "-", "&", "%" };

        for (int i = 0; i < metaCharacters.length; i++) {
            if (inputString.contains(metaCharacters[i])) {
                inputString = inputString.replace(metaCharacters[i], "\\" + metaCharacters[i]);
            }
        }
        return inputString;
    }
}
