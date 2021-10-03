package com.nguyenvm.valid_parentheses;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;

//https://leetcode.com/problems/valid-parentheses

public class ValidParentheses {
    public static boolean isValid(String s) {
        int length = s.length();
        if (length % 2 != 0) return false;

        Map<String, String> valid = new HashMap<>();
        valid.put("(", ")");
        valid.put("{", "}");
        valid.put("[", "]");

        ArrayDeque<String> deque = new ArrayDeque<>();
        for (int i = 0; i < length; i++) {
            String s1 = s.substring(i, i + 1);
            if (!deque.isEmpty()) {
                String s2 = deque.peekLast();
                if (s1.equals(valid.get(s2))) {
                    deque.removeLast();
                    continue;
                }
            }
            deque.add(s1);
        }

        return deque.isEmpty();
    }

    public static void main(String[] args) {
        String s = "(([]){})";
        isValid(s);
    }
}
