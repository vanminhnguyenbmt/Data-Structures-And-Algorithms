package com.nguyenvm.array.validate_stack_sequences;

import java.util.ArrayDeque;

//https://leetcode.com/problems/validate-stack-sequences/

public class ValidateStackSequences {
    public static void main(String[] args) {
        int[] pushed = new int[]{2, 1, 0}, popped = new int[]{0, 1, 2};
        int j = 0, N = pushed.length;
        ArrayDeque<Integer> deque = new ArrayDeque<>();

        for (int i = 0; i < N; i++) {
            deque.add(pushed[i]);

            while (!deque.isEmpty()) {
                int push = deque.getLast();
                if (push == popped[j]) {
                    deque.removeLast();
                    j++;
                } else break;
            }
        }

        System.out.println(j == N);
    }
}
