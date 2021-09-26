package com.nguyenvm.roman_to_integer;

public class RomanToInteger {
    public static int romanToInt(String s) {
        int l = s.length();
        int i = 0;
        int total = 0;
        for (int j = 0; j < l - 1; j++) {
            int current = getRomanValue(s.charAt(j));
            int next = getRomanValue(s.charAt(j + 1));

            if (current >= next) {
                total += current;
            } else {
                total -= current;
            }
        }

        total += getRomanValue(s.charAt(l - 1));

        return total;
    }

    private static int getRomanValue(char next) {
        switch (next) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
        }
        return -1; // if invalid character
    }

    public static void main(String[] args) {
        romanToInt("MCMXCIV");
    }
}
