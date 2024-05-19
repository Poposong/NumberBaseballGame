package org.example.entity;

import java.util.Arrays;
import java.util.Random;


public class Baseball {
    private int[] position;

    public Baseball(int ballNum) {
        position = new int[ballNum];
    }

    public int[] getRandomNumber() {
        return this.position;
    }

    public void setRandomNumber() {
        boolean[] visited = new boolean[10]; // 서로 다른 3자리의 숫자를 만들기 위해 중복을 확인하기 위한 배열
        Arrays.fill(visited, false);

        for (int i = 0; i < position.length; i++) {
            int choiceNumber = choiceOneRandomNumber();
            if (!visited[choiceNumber]) {
                position[i] = choiceNumber;
                visited[choiceNumber] = true;
                continue;
            }
            i--; // 이전에 방문한 경우 숫자를 다시 뽑기 위해 i를 감소시킨다.
        }
    }

    // 랜덤으로 1~9 사이의 숫자를 뽑는 함수
    private int choiceOneRandomNumber() {
        return new Random().nextInt(9) + 1;
    }
}
