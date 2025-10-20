package calculator;

import java.math.BigInteger;
import java.util.List;

public class CollectAllValidator implements ValidationStrategy {

    @Override
    public void validate(List<BigInteger> numbers) {
        StringBuilder negativeNumbers = new StringBuilder();

        for (BigInteger number : numbers) {
            if (number.compareTo(BigInteger.ZERO) < 0) {
                if (negativeNumbers.length() > 0) {
                    negativeNumbers.append(", ");
                }
                negativeNumbers.append(number);
            }
        }

        if (negativeNumbers.length() > 0) {
            throw new IllegalArgumentException("음수는 입력이 불가능합니다 :" + negativeNumbers.toString());        }
    }

}