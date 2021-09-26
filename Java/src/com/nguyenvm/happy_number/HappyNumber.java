package com.nguyenvm.happy_number;

import java.util.HashSet;

public class HappyNumber {
    public static boolean isHappy(int n, HashSet<Integer> cache) {
        while (true) {
            int sum = 0;
            while (n > 0) {
                int t = n % 10;
                n = n / 10;
                sum += t * t;
            }

            if (sum == 1) return true;
            if (cache.contains(sum)) {
                return false;
            }
            cache.add(sum);
            n = sum;
        }
    }

    public static void main(String[] args) {
        HashSet<Integer> cache = new HashSet<>();
        isHappy(25, cache);
    }
}
