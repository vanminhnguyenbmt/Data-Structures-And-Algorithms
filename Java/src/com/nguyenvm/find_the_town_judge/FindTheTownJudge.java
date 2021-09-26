package com.nguyenvm.find_the_town_judge;

//https://leetcode.com/problems/find-the-town-judge/

import java.util.HashMap;
import java.util.HashSet;

public class FindTheTownJudge {
    public static int findJudge(int n, int[][] trust) {
        if (trust.length == 0) return n == 1 ? 1 : -1;

        int[] level = new int[n + 1];
        int length = trust.length;
        for (int i = 0; i < length; i++) {
            level[trust[i][1] - 1] += 1;
            level[trust[i][0] - 1] -= 1;
        }

        int j = 0;
        while (j < n) {
            if (level[j] == n - 1) return j + 1;
            j++;
        }

        return -1;
    }

    public static void main(String[] args) {
        int[][] trust = new int[][]{{1, 8}, {1, 3}, {2, 8}, {2, 3}, {4, 8}, {4, 3}, {5, 8}, {5, 3}, {6, 8}, {6, 3}, {7, 8}, {7, 3}, {9, 8}, {9, 3}, {11, 8}, {11, 3}};
        findJudge(11, trust);
    }
}
