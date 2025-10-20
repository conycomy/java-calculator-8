package calculator;

import java.util.regex.Pattern;
import camp.nextstep.edu.missionutils.Console;

public class Application {

    public static void main(String[] args) {
        System.out.println("===덧셈할 문자열을 입력해주세요===");
        String input = Console.readLine(); // Console.readLine() 사용

        try {
            if (input == null || input.trim().isEmpty()) {
                System.out.println("결과: 0");
                return;
            }
/*

            String basicDelimiterRegex = "[,:]";
            String finalDelimiterRegex = basicDelimiterRegex;
            String numbersToSplit = input;

            // 2. 커스텀 구분자
            if (input.startsWith("//")) {
                int delimiterEndIndex = input.indexOf("\n");

                if (delimiterEndIndex == -1) {
                    throw new IllegalArgumentException("커스텀 구분자 선언 후 반드시 줄 바꿈(\\n)을 해야합니다.");
                }
                String customDelimiter = input.substring(2, delimiterEndIndex);

                finalDelimiterRegex += "|" + Pattern.quote(customDelimiter);

                numbersToSplit = input.substring(delimiterEndIndex + 1);
            }
             String[] parts = numbersToSplit.split(finalDelimiterRegex);

*/
            DelimiterParser parser = new DefaultDelimiterParser();
            DelimiterInfo info = parser.parse(input);

            String numbersToSplit = info.getNumbersString();
            String finalDelimiterRegex = info.getDelimiterRegex();

            String[] parts = numbersToSplit.split(finalDelimiterRegex);

            int sum = 0;
            StringBuilder negativeNumbers = new StringBuilder();

            for (String part : parts) {
                String trimmedS = part.trim();

                if (trimmedS.isEmpty()) {
                    continue;
                }

                if (!trimmedS.matches("^-?\\d+$")) {
                    throw new IllegalArgumentException("잘못된 입력 형식입니다. 숫자만 입력이 가능합니다: " + trimmedS);
                }

                int num = Integer.parseInt(trimmedS);

                if (num < 0) {
                    if(negativeNumbers.length() > 0) {
                        negativeNumbers.append(", ");
                    }
                    negativeNumbers.append(num);
                    continue;
                }

                sum += num;
            }

            if (negativeNumbers.length() > 0) {
                throw new IllegalArgumentException("음수는 입력이 불가능합니다: " + negativeNumbers.toString());
            }

            System.out.println("결과: " + sum);

        } catch (IllegalArgumentException e) {
            System.out.println("오류: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("예상치 못한 오류가 발생했습니다.");
            // e.printStackTrace();
        } finally {
            Console.close();
        }
    }
}