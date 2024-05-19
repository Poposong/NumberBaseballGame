package org.example.validator;

public class Validator {
    // 게임의 결과가 스트라이크인지 확인하기 위한 함수
    public static boolean isStrike(int[] baseballGameResult) {
        if (baseballGameResult[0] == 3) {
            return true;
        }
        return false;
    }

    // 사용자가 입력한 문자열의 유효성을 검사하기 위한 함수
    public static boolean isValidTypeInputNumber(int ballNum, int[] numberArray) {
        for (int i = 0; i < ballNum; i++) {
            if (numberArray[i] == 0) { // 1. 값이 0인 경우
                return false;
            }
            for (int j = i + 1; j < ballNum; j++) {
                if (numberArray[i] == numberArray[j]) { // 2. 중복된 값이 있는 경우
                    return false;
                }
            }
        }
        return true;
    }

    // 사용자가 입력한 문자열의 길이와 게임에서 받으려는 문자열의 길이를 비교한다.
    public static boolean isValidLenInputNumber(String userAnswer, int ballNum){
        if(userAnswer.length() == ballNum){
            return true;
        }
        return false;
    }
}
