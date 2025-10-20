package calculator;

import java.util.regex.Pattern;

public class DefaultDelimiterParser implements DelimiterParser {

    private static final String BASIC_DELIMITER_REGEX = "[,:]";

    @Override
    public DelimiterInfo parse(String input) {
        String finalDelimiterRegex = BASIC_DELIMITER_REGEX;
        String numbersToSplit = input;

        if(input.startsWith("//")) {
            int delimiterEndIndex = input.indexOf("\n");

            if (delimiterEndIndex == -1) {
                throw new IllegalArgumentException("커스텀 구분자 선언 후 반드시 줄 바꿈(\\n)을 해야합니다.");
            }

            String customDelimiter = input.substring(2, delimiterEndIndex);
            finalDelimiterRegex += "|" + Pattern.quote(customDelimiter);

            numbersToSplit = input.substring(delimiterEndIndex + 1);
        }

        return new DelimiterInfo(finalDelimiterRegex, numbersToSplit);
    }

}