package calculator;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class StringCalculator implements Calculator {

    private final DelimiterParser delimiterParser;
    private final NumberParser numberParser;
    private final ValidationStrategy validationStrategy; // ⭐️ ValidationStrategy 필드 추가

    public StringCalculator(DelimiterParser delimiterParser, NumberParser numberParser, ValidationStrategy validationStrategy) {
        this.delimiterParser = delimiterParser;
        this.numberParser = numberParser;
        this.validationStrategy = validationStrategy; // ⭐️ Strategy 주입
    }

    @Override
    public BigInteger calculate(String input) {
        if (input == null || input.trim().isEmpty()) {
            return BigInteger.ZERO;
        }

        DelimiterInfo info = delimiterParser.parse(input);

        String[] parts = info.getNumbersString().split(info.getDelimiterRegex());

        return sum(parts);
    }

    private BigInteger sum(String[] parts) {

        List<BigInteger> numbers = new ArrayList<>();
        for (String token : parts) {
            numbers.add(numberParser.parse(token));
        }


        validationStrategy.validate(numbers);

        BigInteger sum = BigInteger.ZERO;
        for (BigInteger number : numbers) {
            // 검증이 완료된 숫자만 합산합니다.
            sum = sum.add(number);
        }

        return sum;
    }
}