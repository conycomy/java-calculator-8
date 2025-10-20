package calculator;

import java.math.BigInteger;

public class Application {

    public static void main(String[] args) {
        InputHandler inputHandler = new InputHandler();
        String input = inputHandler.getUserInput();

        try {
            DelimiterParser delimiterParser = new DefaultDelimiterParser();
            NumberParser numberParser = new NumberParser();
            ValidationStrategy validationStrategy = new CollectAllValidator();

            Calculator calculator = new StringCalculator(delimiterParser, numberParser, validationStrategy);

            BigInteger result = calculator.calculate(input);

            System.out.println("결과 : " + result);

        } catch (IllegalArgumentException e) {
            System.out.println("오류: " + e.getMessage());
            throw e;  // 예외를 다시 던져서 테스트에서 감지 가능하도록 처리
        } catch (Exception e) {
            System.out.println("예상치 못한 오류가 발생했습니다.");
            throw e;
        } finally {
            inputHandler.close();
        }
    }
}