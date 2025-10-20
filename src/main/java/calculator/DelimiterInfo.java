package calculator;


public class DelimiterInfo {

    final private String delimiterRegex;
    final private String numbersString;

    public DelimiterInfo(String delimiterRegex, String numbersString) {
        this.delimiterRegex = delimiterRegex;
        this.numbersString = numbersString;
    }

    public String getDelimiterRegex() {
        return delimiterRegex;
    }

}
