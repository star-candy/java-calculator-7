package calculator.model;

import java.util.List;
import java.util.stream.Stream;

public class CalculatorLogic {
    private static final String CUSTOM_DELIMITER_PREFIX = "//";
    private static final int DELIMITER_INDEX = 2;  // 구분자 위치 인덱스
    private static final String DEFAULT_DELIMITER = ",|:";  // 기본 구분자
    private static final String NUMBER_PATTERN = "^[0-9]+$";  // 숫자 패턴 정규식

    public String extractDelimiter(String input) {
        if (input.startsWith(CUSTOM_DELIMITER_PREFIX)) {
            return String.valueOf(input.charAt(DELIMITER_INDEX)); // //이후 입력된 첫번째 문자는 구분자
        }
        return DEFAULT_DELIMITER;  //정규식 문법으로 , 또는 :를 구분자로 인식함(|는 or)
    }

    public List<Integer> extractNumbers(String input, String delimiter) {
        if (input.matches(NUMBER_PATTERN)) {
            return List.of(Integer.parseInt(input));
        }
        String[] numbers = input.split(delimiter);
        return Stream.of(numbers)
                .map(Integer::parseInt)
                .toList();
    }

    public int calculate(List<Integer> numbers) {
        return numbers.stream()
                .mapToInt(Integer::intValue)
                .sum();//Integer 객체에 대해서는 sum()사용 불가, int로 unboxing 필요
    }
}