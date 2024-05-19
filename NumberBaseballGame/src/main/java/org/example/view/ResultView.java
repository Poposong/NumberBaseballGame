package org.example.view;

import org.example.entity.State;
import org.example.util.StringUtil;

public class ResultView {

    public void displayGameStartMessage() {
        System.out.println("[숫자 야구 게임]");
        System.out.println(">>>> 컴퓨터가 숫자를 뽑겠다.");
    }

    public void displayComputerGameResult(int[] strikeAndBallCount){
        StringBuilder sb = new StringBuilder();

        if (strikeAndBallCount[0] > 0) {
            sb.append(strikeAndBallCount[0]).append(State.STRIKE.name()).append(" ");
        }

        if (strikeAndBallCount[1] > 0) {
            sb.append(strikeAndBallCount[1]).append(State.BALL.name()).append(" ");
        }

        if (sb.toString().isEmpty()) {
            sb.append(State.NOTHING.name());
        }

        System.out.printf("결과: %s\n", sb.toString());
    }
}
