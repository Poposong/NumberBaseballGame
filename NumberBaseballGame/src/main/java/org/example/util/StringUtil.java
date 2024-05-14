package org.example.util;

public class StringUtil {

    public static int[] strToArray(String str, int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = Integer.parseInt(String.valueOf(str.charAt(i)));
        }
        return array;
    }

    public static String arrayToStr(int[] array, int size) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append(array[i]);
        }
        return sb.toString();
    }
}
