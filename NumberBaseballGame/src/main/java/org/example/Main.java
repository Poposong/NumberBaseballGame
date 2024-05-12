package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * 숫자 야구 게임
 * Strike, Ball, Nothing
 * */

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("---------숫자 야구 게임을 시작한다---------");
        System.out.println("먼저, 컴퓨터가 숫자를 뽑겠다.");

        int[] computerChoiceNumber = makeFullRandomNumber();

        System.out.println("컴퓨터가 뽑은 숫자: "+ computerChoiceNumber[0]+computerChoiceNumber[1]+computerChoiceNumber[2]);

        System.out.println("지금부터 컴퓨터가 뽑은 3자리의 숫자가 무엇인지 맞춰봐라.");


        /**
         * 1. strike를 먼저 확인한다. strike가 되었다면 나머지 자리의 수를 확인한다.
         * 2. ball을 확인한다. 이때까지 확인했을 때 strike나 ball이 없었다면 nothing이다.
         * */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(true){
            String answer = br.readLine(); // 서로 다른 3자의 수, 같은 길이

            int[] userChoiceNumber = strToArray(answer, 3);

            System.out.println("사용자가 뽑은 숫자: "+ userChoiceNumber[0]+userChoiceNumber[1]+userChoiceNumber[2]);


            int[] baseBallResult = baseBallResult(computerChoiceNumber, userChoiceNumber);

            String baseBallResultAnswer = baseBallResultAnswer(baseBallResult);

            System.out.println(baseBallResultAnswer);

            // 스트라이크
            if(baseBallResult[0] == 3){
                System.out.println("게임을 종료하시겠습니까?");
                answer = br.readLine();
                // 0: 종료,
            }

        }

    }

    // 랜덤으로 1~9 사이의 숫자를 뽑는 메서드
    public static int choiceOneRandomNumber(){
        return new Random().nextInt(9) + 1;
    }

    // 뽑은 숫자로 3자리 숫자를 만드는 메서드
    public static int[] makeFullRandomNumber(){
        int[] computerChoiceNumber = new int[3];
        for(int i = 0; i < 3; i++) {
            computerChoiceNumber[i] = choiceOneRandomNumber();
        }
        return computerChoiceNumber;
    }

    // 사용자가 뽑은 answer이라는 문자열을 size 만큼의 int 배열에 넣어서 반환한다.
    public static int[] strToArray(String answer, int size){
        int[] array = new int[size];
        for(int i = 0; i < size; i++) {
            array[i] = Integer.parseInt(String.valueOf(answer.charAt(i)));
        }
        return array;
    }

    //
    public static void ballCheck(Set<Integer> set, int[] result, int checkNumber){
        if(set.contains(checkNumber)){
            set.remove(checkNumber);
            result[1]++;
        }else{
            set.add(checkNumber);
        }
    }

    public static String baseBallResultAnswer(int[] baseBallResult){
        StringBuilder sb = new StringBuilder();

        if(baseBallResult[0] != 0){
            sb.append(baseBallResult[0]).append("STRIKE\n");
        }

        if(baseBallResult[1] != 0){
            sb.append(baseBallResult[1]).append("BALL\n");
        }

        if(sb.toString().length() == 0){
            sb.append("NOTHING");
        }

        return sb.toString();
    }

    public static int[] baseBallResult(int[] computerChoiceNumber, int[] userChoiceNumber){
        int size = computerChoiceNumber.length;

        int[] result = new int[2]; // 0:strike, 1:ball
        Arrays.fill(result, 0);

        Set<Integer> set = new HashSet<>();

        for(int i = 0; i < size; i++) {
            if(computerChoiceNumber[i] == userChoiceNumber[i]){ // strike
                result[0]++; // 스트라이크의 수 증가
                continue;
            }

            // computerChoiceNumber
            ballCheck(set, result, computerChoiceNumber[i]);

            // userChoiceNumber
            ballCheck(set, result, userChoiceNumber[i]);
        }

        return result;
    }

    /**
     * Random random = new Random();
     *         int firstNumber = random.nextInt(9) + 1;
     *         int secondNumber = random.nextInt(9) + 1;
     *         int thirdNumber = random.nextInt(9) + 1;
     * */


}