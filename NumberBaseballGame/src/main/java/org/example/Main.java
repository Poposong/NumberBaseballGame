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
 * */

public class Main {
    public static void main(String[] args) throws IOException {
        /**
         * 1. strike를 먼저 확인한다. strike가 되었다면 나머지 자리의 수를 확인한다.
         * 2. ball을 확인한다. 이때까지 확인했을 때 strike나 ball이 없었다면 nothing이다.
         * */
        while(true){
            System.out.println("[숫자 야구 게임]");
            System.out.println(">>>> 컴퓨터가 숫자를 뽑겠다.");

            RandomNumber computer = new RandomNumber(3);

            computer.setRandomNumber();

            int[] computerChoiceNumber = computer.getRandomNumber();

            System.out.println("컴퓨터가 뽑은 숫자: "+ computerChoiceNumber[0]+computerChoiceNumber[1]+computerChoiceNumber[2]);

            System.out.println(">>>> 지금부터 컴퓨터가 뽑은 3자리의 숫자가 무엇인지 맞춰봐라.");

            if(!userGame(computerChoiceNumber)){ // 종료
                break;
            }
        }

    }

    public static boolean userGame(int[] computerChoiceNumber) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        BaseballGame baseballGame = new BaseballGame();

        while(true){
            System.out.println(">>>>> 서로 다른 3자리의 숫자를 입력해주세요.");

            String userAnswer = br.readLine();

            int[] userChoiceNumber = StringUtil.strToArray(userAnswer, 3);

            if(!baseballGame.validator(userAnswer, 3, userChoiceNumber)){
                System.out.println(">>>> 형식에 맞춰서 입력해주세요.");
                continue;
            }

            System.out.println("사용자가 뽑은 숫자: "+ userChoiceNumber[0]+userChoiceNumber[1]+userChoiceNumber[2]);

            int[] baseballGameResult = baseballGame.baseballGameResult(computerChoiceNumber, userChoiceNumber);

            String baseballGameResultToStr = baseballGame.baseballGameResultToStr(baseballGameResult);

            System.out.println(baseballGameResultToStr);

            // 스트라이크
            if(baseballGame.isStrike(baseballGameResult)){
                System.out.println("게임을 종료하시겠습니까? (0은 종료, 나머지는 계속)");

                userAnswer = br.readLine();

                if(Integer.parseInt(userAnswer) == 0){ // 종료
                    return false;
                }
                return true;
            }
        }
    }




}