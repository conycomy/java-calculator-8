package calculator;

import camp.nextstep.edu.missionutils.Console;

public class InputHandler {

    private static final String INPUT_MESSAGE = "덧셈할 문자열을 입력해 주세요.";


    public String getUserInput() {
        System.out.println(INPUT_MESSAGE);
        return Console.readLine();
    }


    public void close() {
        Console.close();
    }
}