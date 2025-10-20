package calculator;

import java.math.BigInteger;
import java.util.List;

public interface ValidationStrategy {

    void validate(List<BigInteger> number);
}
