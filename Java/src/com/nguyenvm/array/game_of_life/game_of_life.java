package com.nguyenvm.array.game_of_life;

// https://leetcode.com/problems/game-of-life/

import java.util.Arrays;

/***
 * check 8 cells around
 * < 2: 1 -> 0
 * 2: ^ 0
 * > 3: 1 -> 0
 * == 3: | 1
 */
public class game_of_life {
    public static void main(String[] args) {
        int[][] board =
                {
                        {0, 1, 0},
                        {0, 0, 1},
                        {1, 1, 1},
                        {0, 0, 0}
                };

        int m = board.length;
        int n = board[0].length;
        System.out.println(Arrays.deepToString(calculateBoard(board, m, n)));
    }

    public static int[][] calculateBoard(int[][] board, int m, int n) {
        int[][] result = new int[m][n];
        // i - 1, j - 1
        // i - 1, j
        // i - 1, j + 1
        // i, j + 1
        // i + 1, j + 1
        // i + 1, j
        // i + 1, j - 1
        // i, j - 1
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int check = 0;
                if (i - 1 >= 0 && j - 1 >= 0) {
                    check += board[i - 1][j - 1];
                }

                if (i - 1 >= 0 && j >= 0) {
                    check += board[i - 1][j];
                }

                if (i - 1 >= 0 && j + 1 <= n - 1) {
                    check += board[i - 1][j + 1];
                }

                if (i >= 0 && j + 1 <= n - 1) {
                    check += board[i][j + 1];
                }

                if (i + 1 >= 0 && i + 1 <= m - 1 && j + 1 <= n - 1) {
                    check += board[i + 1][j + 1];
                }

                if (i + 1 >= 0 && i + 1 <= m - 1) {
                    check += board[i + 1][j];
                }

                if (j - 1 >= 0 && i + 1 <= m - 1) {
                    check += board[i + 1][j - 1];
                }

                if (j - 1 >= 0) {
                    check += board[i][j - 1];
                }

                if (check < 2) {
                    result[i][j] = 0;
                    continue;
                }

                if (check == 2) {
                    result[i][j] = board[i][j];
                    continue;
                }

                if (check == 3) {
                    result[i][j] = board[i][j] | 1;
                    continue;
                }

                if (check > 3) {
                    result[i][j] = 0;
                    continue;
                }
            }
        }

        return result;
    }
}
