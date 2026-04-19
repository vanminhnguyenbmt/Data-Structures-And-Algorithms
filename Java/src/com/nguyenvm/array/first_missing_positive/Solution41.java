package com.nguyenvm.array.first_missing_positive;

public class Solution41 {

  // Cyclic sort
  public int firstMissingPositive1(int[] nums) {
    for (int i = 0; i < nums.length; i++) {
      int currentValue = nums[i];
      while (currentValue > 0 && currentValue < nums.length && currentValue != nums[currentValue- 1]) {
        nums[i] = nums[currentValue - 1];
        nums[currentValue - 1] = currentValue;
        currentValue = nums[i];
      }
    }

    for (int i = 0; i < nums.length; i++) {
      if (nums[i] != i + 1) {
        return i + 1;
      }
    }
    return nums.length + 1;
  }

  // Replace invalid -> Marking negative -> Scan first positive
  public int firstMissingPositive2(int[] nums) {
    boolean existOne = false;
    for (int i = 0; i < nums.length; i++) {
      if (nums[i] == 1) {
        existOne = true;
        continue;
      }

      if (nums[i] <= 0 || nums[i] > nums.length) {
        nums[i] = 1;
      }
    }

    if (!existOne) {
      return 1;
    }

    for (int i = 0; i < nums.length; i++) {
      int visitedIndex = Math.abs(nums[i]) - 1;
      if (nums[visitedIndex] > 0) {
        nums[visitedIndex] *= -1;
      }
    }

    for (int i = 0; i < nums.length; i++) {
      if (nums[i] > 0) {
        return i + 1;
      }
    }
    return nums.length + 1;
  }

  public static void main(String[] args) {
//    System.out.println(new Solution41().firstMissingPositive2(new int[]{3, 4, -1, 1}));
    System.out.println(new Solution41().firstMissingPositive2(new int[]{7, 8, 9, 11, 12}));
  }
}
