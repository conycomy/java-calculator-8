package calculator;

import java.util.regex.Pattern;

public class DefaultDelimiterParser implements DelimiterParser {

    private static final String BASIC_DELIMITER_REGEX = "[,:]";

    @Override
    public DelimiterInfo parse(String input) {
        // 테스트 입력의 "\\n" 문자열을 실제 개행 문자 '\n'으로 변환
        String sanitizedInput = input.replace("\r", "").replace("\\n", "\n");

        String finalDelimiterRegex = BASIC_DELIMITER_REGEX;
        String numbersToSplit;

        if (sanitizedInput.startsWith("//")) {
            int delimiterEndIndex = sanitizedInput.indexOf("\n");

            if (delimiterEndIndex == -1) {
                throw new IllegalArgumentException("커스텀 구분자 선언 후 반드시 줄 바꿈(\\n)을 해야합니다.");
            }

            String customDelimiter = sanitizedInput.substring(2, delimiterEndIndex);
            finalDelimiterRegex += "|" + Pattern.quote(customDelimiter);

            numbersToSplit = sanitizedInput.substring(delimiterEndIndex + 1);
        } else {
            numbersToSplit = sanitizedInput;
        }

        return new DelimiterInfo(finalDelimiterRegex, numbersToSplit);
    }
}
