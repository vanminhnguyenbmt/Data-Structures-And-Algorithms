package com.nguyenvm.subsests;

import java.util.ArrayList;
import java.util.List;

//https://leetcode.com/problems/subsets/

public class SubsetsBacktracking {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 4, 5};
        int length = nums.length;

        List<List<Integer>> listResult = new ArrayList();
        for (int k = 0; k < length + 1; k++) {
            backTracking(0, k, length, nums, new ArrayList<>(), listResult);
        }

        System.out.println(listResult);
    }

    public static List<List<Integer>> backTracking(int first, int currIndex, int length, int[] nums, List<Integer> curr, List<List<Integer>> result) {
        if (curr.size() == currIndex) {
            result.add(new ArrayList<>(curr));
            return result;
        }

        for (int i = first; i < length; i++) {
            curr.add(nums[i]);
            backTracking(i + 1, currIndex, length, nums, curr, result);
            curr.remove(curr.size() - 1);
        }

        return result;
    }
}
