package calculator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy; // 예외 테스트를 위해 추가 필요

import java.math.BigInteger; // BigInteger 사용을 위해 추가
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StringCalculatorTest {

    private StringCalculator calculator;

    @BeforeEach
    void setUp() {
        DelimiterParser delimiterParser = new DefaultDelimiterParser();
        NumberParser numberParser = new NumberParser();

        // ⭐️ 수정: ValidationStrategy 인터페이스 대신 구현체인 CollectAllValidator를 인스턴스화합니다.
        ValidationStrategy validationStrategy = new CollectAllValidator();

        // StringCalculator의 생성자는 이제 ValidationStrategy를 받습니다.
        calculator = new StringCalculator(delimiterParser, numberParser, validationStrategy);
    }

    @Test
    @DisplayName("5. 커스텀 구분자를 사용하여 합계를 계산한다.")
    void calculate_custom_delimiter() {


        assertThat(calculator.calculate("//;\n1;2;3")).isEqualTo(BigInteger.valueOf(6));

        assertThat(calculator.calculate("//#\n10#20#5")).isEqualTo(BigInteger.valueOf(35));

     
        assertThat(calculator.calculate("//!\n1!2,3")).isEqualTo(BigInteger.valueOf(6));
    }
}