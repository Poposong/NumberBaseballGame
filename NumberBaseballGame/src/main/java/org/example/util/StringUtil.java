package org.example.util;

public class StringUtil {
    // 사용자가 뽑은 answer이라는 문자열을 size 만큼의 int 배열에 넣어서 반환한다.
    public static int[] strToArray(String str, int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = Integer.parseInt(String.valueOf(str.charAt(i)));
        }
        return array;
    }
}
