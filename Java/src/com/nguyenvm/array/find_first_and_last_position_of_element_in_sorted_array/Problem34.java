package com.nguyenvm.array.find_first_and_last_position_of_element_in_sorted_array;

// https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/

public class Problem34 {
    public static int[] searchRange(int[] nums, int target) {
        int[] result = {-1, -1};
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            if (nums[left] == target && result[0] == -1) {
                result[0] = left;
            }
            if (nums[right] == target && result[1] == -1) {
                result[1] = right;
            }
            if (result[0] != -1 && result[1] != -1) break;
            if (result[0] == -1) left++;
            if (result[1] == -1) right--;
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1,3};
        int target = 1;
        System.out.println(searchRange(nums, target));
    }
}
