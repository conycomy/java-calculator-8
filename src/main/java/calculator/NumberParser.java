package calculator;

import java.math.BigInteger;

public class NumberParser {

    public BigInteger parse(String token) {
        String trimmedToken = token.trim();

        if (trimmedToken.isEmpty()) {
            return BigInteger.ZERO;
        }

        try {
            return new BigInteger(trimmedToken);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자 이외의 문자열이 포함되어 변환할 수 없습니다: " + trimmedToken);
        }
    }
}
