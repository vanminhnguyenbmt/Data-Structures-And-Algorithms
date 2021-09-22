package com.nguyenvm.Pow;

//https://leetcode.com/problems/powx-n/

public class PowImplementation {
    public static double myPow(double x, int n) {
        if (x == 0) return 0;
        if (x == 1 || n == 0) return 1;
        if (x == -1) {
            if (n % 2 == 0) return 1;
            return -1;
        }

        double result = 1;
        double tmp = n;
        if (n < 0) tmp *= -1;

        while (tmp > 0) {
            if (tmp % 2 == 1) {
                result *= x;
                if (result == Double.POSITIVE_INFINITY) {
                    break;
                }

                tmp--;
                continue;
            };
            x *= x;
            tmp /= 2;
        }
        return (n < 0) ? 1 / result : result;
    }

    public static void main(String[] args) {
        myPow(2, 10);
    }
}
