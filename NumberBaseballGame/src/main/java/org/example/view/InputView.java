package org.example.view;


import org.example.validator.Validator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class InputView {
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public String getUserInputForNumber(int ballNum) throws IOException {
        String userAnswer;
        while (true) {
            System.out.printf(">>>>> 서로 다른 %d자리의 숫자를 입력해주세요.\n", ballNum);
            userAnswer = br.readLine();

            if(Validator.isValidLenInputNumber(userAnswer, ballNum)){
                return userAnswer;
            }
            this.userNumberCountFormatErrMsg(ballNum);
        }
    }

    public void userNumberCountFormatErrMsg(int ballNum) {
        System.out.printf(">>>> 길이를 맞춰서 적어주세요.\n", ballNum);
    }

    public void userNumberDiffFormatErrMsg(int ballNum) {
        System.out.printf(">>>> 서로 다른 %d자리의 숫자, 1~9 사이의 숫자를 적어주세요. \n", ballNum);
    }

    public boolean getUserExitDecision() throws IOException {
        System.out.println("게임을 종료하시겠습니까? (0은 종료, 나머지는 계속)");
        String userAnswer = br.readLine();
        return !userAnswer.equals("0");
    }
}
