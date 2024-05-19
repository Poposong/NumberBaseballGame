package org.example;

import org.example.baseballGame.BaseballGame;
import java.io.IOException;


public class Main {
    private static final int ballNum = 3;

    public static void main(String[] args) throws IOException {
        BaseballGame baseballGame = new BaseballGame();
        boolean status = true;
        while (status) {
            status = baseballGame.start(ballNum);
        }
    }
}