package org.example;

import org.example.baseballGame.BaseballGame;
import org.example.baseballGame.RandomNumber;
import org.example.util.StringUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 숫자 야구 게임
 * Strike, Ball, Nothing
 */

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