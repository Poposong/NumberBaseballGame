package org.example.baseballGame;

import org.example.util.StringUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class BaseballGame {
    public boolean start(int ballNum) throws IOException {
        int[] computerNumber = computerTurn(ballNum); // 컴퓨터
        return userTurn(ballNum, computerNumber); // 사용자
    }

    // 사용자가 컴퓨터가 뽑은 숫자를 맞출 때까지 게임을 반복하는 함수
    private boolean userTurn(int ballNum, int[] computerNumber) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.printf(">>>>> 서로 다른 %d자리의 숫자를 입력해주세요.\n", ballNum);

            String userAnswer = br.readLine();

            int[] userNumber = StringUtil.strToArray(userAnswer, 3);

            if (!this.validator(userAnswer, ballNum, userNumber)) {
                System.out.println(">>>> 형식에 맞춰서 입력해주세요.");
                continue;
            }

            int[] baseballGameResult = this.baseballGameResult(computerNumber, userNumber);

            System.out.printf("결과: %s\n", this.baseballGameResultToStr(baseballGameResult));

            // 스트라이크
            if (this.isStrike(baseballGameResult)) {
                System.out.println("게임을 종료하시겠습니까? (0은 종료, 나머지는 계속)");

                userAnswer = br.readLine();

                return !userAnswer.equals("0");
            }
        }
    }

    // 컴퓨터가 랜덤으로 ballNum자리의 숫자를 뽑는 함수
    private int[] computerTurn(int ballNum) {
        System.out.println("[숫자 야구 게임]");
        System.out.println(">>>> 컴퓨터가 숫자를 뽑겠다.");

        int[] computerNumber = choiceComputerNumber(ballNum);

        System.out.println("컴퓨터가 뽑은 숫자: " + StringUtil.arrayToStr(computerNumber, ballNum));
        System.out.printf(">>>> 지금부터 컴퓨터가 뽑은 %d자리의 숫자가 무엇인지 맞춰봐라.\n", ballNum);

        return computerNumber;
    }

    // 랜덤으로 숫자를 뽑는 함수
    private int[] choiceComputerNumber(int ballNum) {
        RandomNumber computer = new RandomNumber(ballNum);

        computer.setRandomNumber();

        return computer.getRandomNumber();
    }

    // 컴퓨터의 숫자와 사용자의 숫자를 비교해서 스트라이크와 볼의 수를 계산하는 함수
    private int[] baseballGameResult(int[] computerNumber, int[] userNumber) {
        int size = computerNumber.length;

        int[] strikeAndBallCount = new int[2]; // 0:strike, 1:ball
        Arrays.fill(strikeAndBallCount, 0);

        Set<Integer> ballCheck = new HashSet<>();

        for (int i = 0; i < size; i++) {
            if (computerNumber[i] == userNumber[i]) { // strike
                strikeAndBallCount[0]++; // 스트라이크의 수 증가
                continue;
            }
            ballCheck(ballCheck, strikeAndBallCount, computerNumber[i]);
            ballCheck(ballCheck, strikeAndBallCount, userNumber[i]);
        }

        return strikeAndBallCount;
    }

    // 볼이 나온 경우를 확인하기 위한 함수
    private void ballCheck(Set<Integer> ballCheck, int[] strikeAndBallCount, int checkNumber) {
        if (ballCheck.contains(checkNumber)) {
            ballCheck.remove(checkNumber);
            strikeAndBallCount[1]++;
            return;
        }
        ballCheck.add(checkNumber);
    }

    // 게임의 결과를 문자열로 나타내기 위한 함수
    private String baseballGameResultToStr(int[] strikeAndBallCount) {
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

    // 게임의 결과가 스트라이크인지 확인하기 위한 함수
    private boolean isStrike(int[] baseballGameResult) {
        if (baseballGameResult[0] == 3) {
            return true;
        }
        return false;
    }

    // 사용자가 입력한 문자열의 유효성을 검사하기 위한 함수
    private boolean validator(String answer, int ballNum, int[] numberArray) {
        if (answer.length() != ballNum) { // 1. 사이즈와 배열의 크기가 같지 않는 경우
            return false;
        }

        for (int i = 0; i < ballNum; i++) {
            if (numberArray[i] == 0) { // 2. 값이 0인 경우
                return false;
            }
            for (int j = i + 1; j < ballNum; j++) {
                if (numberArray[i] == numberArray[j]) { // 3. 중복된 값이 있는 경우
                    return false;
                }
            }
        }
        return true;
    }
}
