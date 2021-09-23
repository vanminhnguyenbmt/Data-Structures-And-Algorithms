package com.nguyenvm.Sqrt;

//https://leetcode.com/problems/sqrtx/

public class SqrtImplementation {
    public static int mySqrt(int x) {
        if (x < 2) return x;
        int min = 0;
        int max = x;

        while (true) {
            double result = (min + max) / 2;
            if (result * result > x) {
                max = (int)result;
                continue;
            }

            if (result == min || result * result == x || (x - 0.01 <= result * result && result * result <= x + 0.01)) return (int)result;

            if (result * result < x) {
                min = (int)result;
            }
        }
    }

    public static void main(String[] args) {
        mySqrt(2147395599);
    }
}
