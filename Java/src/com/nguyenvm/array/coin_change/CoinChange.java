package com.nguyenvm.array.coin_change;

public class CoinChange {

  public int coinChange(int[] coins, int amount) {
    int[] dp = new int[amount + 1];

    for (int i = 1; i <= amount; i++) {
      int min = Integer.MAX_VALUE;
      for (int coin : coins) {
        int remainder = i - coin;
        if (remainder < 0) {
          continue;
        }

        if (dp[remainder] != -1) {
          min = Math.min(min, dp[remainder]);
        }
      }

      if (min == Integer.MAX_VALUE) {
        dp[i] = -1;
        continue;
      }
      dp[i] = min + 1;
    }

    return dp[amount];
  }

  public static void main(String[] args) {
    System.out.println(new CoinChange().coinChange(new int[]{1, 2, 5}, 11));
  }
}
