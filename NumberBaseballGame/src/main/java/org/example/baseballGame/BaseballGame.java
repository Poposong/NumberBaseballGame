package org.example.baseballGame;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class BaseballGame {
    public int[] baseballGameResult(int[] computerChoiceNumber, int[] userChoiceNumber) {
        int size = computerChoiceNumber.length;

        int[] strikeAndBallCount = new int[2]; // 0:strike, 1:ball
        Arrays.fill(strikeAndBallCount, 0);

        Set<Integer> ballCheck = new HashSet<>();

        for (int i = 0; i < size; i++) {
            if (computerChoiceNumber[i] == userChoiceNumber[i]) { // strike
                strikeAndBallCount[0]++; // 스트라이크의 수 증가
                continue;
            }

            // computerChoiceNumber
            ballCheck(ballCheck, strikeAndBallCount, computerChoiceNumber[i]);

            // userChoiceNumber
            ballCheck(ballCheck, strikeAndBallCount, userChoiceNumber[i]);
        }

        return strikeAndBallCount;
    }

    public void ballCheck(Set<Integer> ballCheck, int[] strikeAndBallCount, int checkNumber) {
        if (ballCheck.contains(checkNumber)) {
            ballCheck.remove(checkNumber);
            strikeAndBallCount[1]++;
            return;
        }
        ballCheck.add(checkNumber);
    }

    public String baseballGameResultToStr(int[] strikeAndBallCount) {
        StringBuilder sb = new StringBuilder();

        if (strikeAndBallCount[0] != 0) {
            sb.append(strikeAndBallCount[0]).append("STRIKE\n");
        }

        if (strikeAndBallCount[1] != 0) {
            sb.append(strikeAndBallCount[1]).append("BALL\n");
        }

        if (sb.toString().length() == 0) {
            sb.append("NOTHING");
        }

        return sb.toString();
    }

    public boolean isStrike(int[] baseballGameResult) {
        if (baseballGameResult[0] == 3) {
            return true;
        }
        return false;
    }

    public boolean validator(String answer, int size, int[] numberArray) {
        if (answer.length() != size) { // 1. 사이즈와 배열의 크기가 같지 않는 경우
            return false;
        }

        for (int i = 0; i < size; i++) {
            if (numberArray[i] == 0) { // 2. 값이 0인 경우
                return false;
            }
            for (int j = i + 1; j < size; j++) {
                if (numberArray[i] == numberArray[j]) { // 3. 중복된 값이 있는 경우
                    return false;
                }
            }
        }

        return true;
    }
}
