package com.nguyenvm.validate_stack_sequences;

import java.util.Arrays;

//https://leetcode.com/problems/validate-stack-sequences/

public class ValidateStackSequences {
    public static void main(String[] args) {
        int[] pushed = new int[]{2, 1, 0}, popped = new int[]{0, 1, 2};
        int i = 0, j = 0, N = pushed.length;
        int[] tmp = new int[0];

        while(i < N) {
            tmp = Arrays.copyOf(tmp, tmp.length + 1);
            System.arraycopy(pushed, i, tmp, tmp.length - 1, 1);

            while (tmp.length != 0 && tmp.length - 1 >= 0 && tmp[tmp.length - 1] == popped[j] && j < N) {
                tmp = Arrays.copyOf(tmp, tmp.length - 1);
                j++;
            }

            i++;
        }

        System.out.println(j == N);
    }
}
