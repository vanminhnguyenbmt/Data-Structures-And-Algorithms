package com.nguyenvm.remove_k_digits;

import java.util.ArrayDeque;
import java.util.Deque;

public class RemoveKDigits {
    /**
     * Monotonic Increasing Stack
     * Consider an array Arr[] = {1, 4, 5, 3, 12, 10}
     * For i = 0: stk = {1}
     * For i = 1: stk = {1, 4}
     * For i = 2: stk = {1, 4, 5}
     * For i = 3: stk = {1, 3} [pop 4 and 5 as 4 > 3 and 5 > 3]
     * For i = 4: stk = {1, 3, 12}
     * For i = 5: stk = {1, 3, 10} [pop 12 as 12 > 10]
     */
    public static String removeKdigits(String num, int k) {
        if (num.length() == k)
            return "0";

        Deque<Integer> result = new ArrayDeque<>();

        char[] chars = num.toCharArray();
        result.offer(Character.getNumericValue(chars[0]));

        int count = 0;
        for (int i = 1; i < chars.length; i++) {
            int cur = Character.getNumericValue(chars[i]);

            while (count != k && !result.isEmpty() && result.peekLast() > cur) {
                result.pollLast();
                count++;
            }
            result.offer(cur);
        }

        while (count != k && !result.isEmpty()) {
            result.pollLast();
            count++;
        }

        StringBuilder output = new StringBuilder();
        while (!result.isEmpty()) {
            int cur = result.pollFirst();
            if (output.length() == 0 && cur == 0)
                continue;
            output.append(cur);
        }

        return output.length() == 0 ? "0" : output.toString();
    }

    public static void main(String[] args) {
        System.out.println(removeKdigits("1432219", 3));
    }
}
