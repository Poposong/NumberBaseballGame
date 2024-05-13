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
        /**
         * 1. strike를 먼저 확인한다. strike가 되었다면 나머지 자리의 수를 확인한다.
         * 2. ball을 확인한다. 이때까지 확인했을 때 strike나 ball이 없었다면 nothing이다.
         * */
        while(true){
            System.out.println("[숫자 야구 게임]");
            System.out.println(">>>> 컴퓨터가 숫자를 뽑겠다.");

            int[] computerChoiceNumber = makeFullRandomNumber();

            System.out.println("컴퓨터가 뽑은 숫자: "+ computerChoiceNumber[0]+computerChoiceNumber[1]+computerChoiceNumber[2]);

            System.out.println(">>>> 지금부터 컴퓨터가 뽑은 3자리의 숫자가 무엇인지 맞춰봐라.");

            if(!userGame(computerChoiceNumber)){
                break; // 종료
            }
        }

    }

    public static boolean userGame(int[] computerChoiceNumber) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(true){
            System.out.println(">>>>> 서로 다른 3자리의 숫자를 입력해주세요.");

            String answer = br.readLine(); // 서로 다른 3자의 수, 같은 길이

            int[] userChoiceNumber = strToArray(answer, 3);

            if(!answerValidator(answer, userChoiceNumber)){
                System.out.println(">>>> 형식에 맞춰서 입력해주세요.");
                continue;
            }

            System.out.println("사용자가 뽑은 숫자: "+ userChoiceNumber[0]+userChoiceNumber[1]+userChoiceNumber[2]);

            int[] baseBallResult = baseBallResult(computerChoiceNumber, userChoiceNumber);

            String baseBallResultAnswer = baseBallResultAnswer(baseBallResult);

            System.out.println(baseBallResultAnswer);
            // 스트라이크
            if(baseBallResult[0] == 3){
                System.out.println("게임을 종료하시겠습니까? (0은 종료, 나머지는 계속)");
                answer = br.readLine();
                if(Integer.parseInt(answer) == 0){
                    return false; // 종료
                }
                return true;
            }
        }
    }

    public static boolean answerValidator(String answer, int[] numberArray){
        if(answer.length() != 3){
            return false;
        }

        if(numberArray[0] == numberArray[1] || numberArray[1] == numberArray[2] || numberArray[0] == numberArray[2]){
            return false;
        }

        if(numberArray[0] == 0 || numberArray[1] == 0 || numberArray[2] == 0){
            return false;
        }

        return true;
    }

    // 랜덤으로 1~9 사이의 숫자를 뽑는 메서드
    public static int choiceOneRandomNumber(){
        return new Random().nextInt(9) + 1;
    }

    // 뽑은 숫자로 3자리 숫자를 만드는 메서드
    public static int[] makeFullRandomNumber(){
        boolean[] visited = new boolean[10];
        Arrays.fill(visited, false);

        int[] computerChoiceNumber = new int[3];

        for(int i = 0; i < 3; i++) {
            int randomNumber = choiceOneRandomNumber();
            if(!visited[randomNumber]){
                computerChoiceNumber[i] = randomNumber;
                visited[randomNumber] = true;
                continue;
            }
            i--;
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
            return;
        }
        set.add(checkNumber);
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


}