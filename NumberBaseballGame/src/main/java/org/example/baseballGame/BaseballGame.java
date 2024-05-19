package org.example.baseballGame;

import org.example.validator.Validator;
import org.example.entity.Baseball;
import org.example.entity.State;
import org.example.util.StringUtil;
import org.example.view.InputView;
import org.example.view.ResultView;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class BaseballGame {

    private InputView inputView;
    private ResultView resultView;


    public BaseballGame() {
        inputView = new InputView();
        resultView = new ResultView();
    }

    public boolean start(int ballNum) throws IOException {
        int[] computerNumber = computerTurn(ballNum); // 컴퓨터
        return userTurn(ballNum, computerNumber); // 사용자
    }

    // 사용자가 컴퓨터가 뽑은 숫자를 맞출 때까지 게임을 반복하는 함수
    private boolean userTurn(int ballNum, int[] computerNumber) throws IOException {
        while (true) {
            String userAnswer = inputView.getUserInputForNumber(ballNum);

            int[] userNumber = StringUtil.strToArray(userAnswer, ballNum); // 사용자가 뽑은 숫자

            if (!Validator.isValidTypeInputNumber(ballNum, userNumber)) {
                inputView.userNumberDiffFormatErrMsg(ballNum);
                continue;
            }

            int[] baseballGameResult = this.baseballGameResult(computerNumber, userNumber); // 게임을 실행한 결과가 배열에 담긴다.

            if (Validator.isStrike(baseballGameResult)) {
                return inputView.getUserExitDecision();
            }
        }
    }

    // 컴퓨터가 랜덤으로 ballNum자리의 숫자를 뽑는 함수
    private int[] computerTurn(int ballNum) {
        resultView.displayGameStartMessage();
        return choiceComputerNumber(ballNum); // 컴퓨터가 뽑은 숫자
//        int[] computerNumber = choiceComputerNumber(ballNum); // 컴퓨터가 뽑은 숫자
//        System.out.println("컴퓨터가 뽑은 숫자: " + StringUtil.arrayToStr(computerNumber, ballNum));
//        return computerNumber;
    }

    // 랜덤으로 숫자를 뽑는 함수
    private int[] choiceComputerNumber(int ballNum) {
        Baseball baseball = new Baseball(ballNum);
        baseball.setRandomNumber();
        return baseball.getRandomNumber();
    }

    // 컴퓨터의 숫자와 사용자의 숫자를 비교해서 스트라이크와 볼의 수를 계산하는 함수
    private int[] baseballGameResult(int[] computerNumber, int[] userNumber) {
        int size = computerNumber.length;
        int[] strikeAndBallCount = new int[2]; // [0]: STRIKE, [1]: BALL

        Set<Integer> uniqueDigits = new HashSet<>();
        for (int i = 0; i < size; i++) {
            if (computerNumber[i] == userNumber[i]) { // STRIKE
                strikeAndBallCount[0]++; // 스트라이크의 수 증가
                continue;
            }
            ballCheck(uniqueDigits, strikeAndBallCount, computerNumber[i]);
            ballCheck(uniqueDigits, strikeAndBallCount, userNumber[i]);
        }

        // 게임 결과 출력하기
        resultView.displayComputerGameResult(strikeAndBallCount);

        return strikeAndBallCount;
    }

    // 볼이 나온 경우를 확인하기 위한 함수
    private void ballCheck(Set<Integer> ballCheck, int[] strikeAndBallCount, int checkNumber) {
        if (ballCheck.contains(checkNumber)) { // 컴퓨터와 사용자 모두 같은 수가 나왔다는 건 BALL이 발생한 것을 말한다.
            strikeAndBallCount[1]++;
            return;
        }
        ballCheck.add(checkNumber);
    }
}
