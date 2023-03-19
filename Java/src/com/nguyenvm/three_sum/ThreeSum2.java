package com.nguyenvm.three_sum;

import java.util.*;

// https://leetcode.com/problems/3sum/

public class ThreeSum2 {
    public static List<List<Integer>> threeSum(int[] nums) {
        int length = nums.length;
        if (length < 3) {
            return Collections.emptyList();
        }
        Set<List<Integer>> results = new HashSet<>();
        Arrays.sort(nums);
        for (int i = 0; i < length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            int p1 = i + 1;
            int p2 = length - 1;
            while (p1 < p2) {
                int sum = nums[i] + nums[p1] + nums[p2];
                if (sum == 0) {
                    results.add(Arrays.asList(nums[i], nums[p1++], nums[p2--]));
                }
                if (sum < 0) {
                    p1++;
                }
                if (sum > 0) {
                    p2--;
                }
            }
        }
        return new ArrayList<>(results);
    }

    public static void main(String[] args) {
        int[] nums = {-7, -4, -6, 6, 4, -6, -9, -10, -7, 5, 3, -1, -5, 8, -1, -2, -8, -1, 5, -3, -5, 4, 2, -5, -4, 4, 7};
        threeSum(nums);
    }
}
