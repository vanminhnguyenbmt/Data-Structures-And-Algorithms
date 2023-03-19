package com.nguyenvm.array.three_sum;

import java.util.*;

// https://leetcode.com/problems/3sum/

public class ThreeSum1 {
    public static List<List<Integer>> threeSum(int[] nums, int target) {
        Arrays.sort(nums);
        Map<Integer, Queue<Integer>> mapUniqueIndex = convertToMapIndex(nums);
        Set<List<Integer>> results = new HashSet<>();
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            for (int j = i + 1; j < length; j++) {
                int remainingNumber = 0 - nums[i] - nums[j];
                if (remainingNumber > nums[length - 1]) continue;
                Queue<Integer> indexQueue = mapUniqueIndex.get(remainingNumber);
                if (indexQueue == null || indexQueue.isEmpty()) continue;
                if (indexQueue.peek() != null && indexQueue.peek() <= i) indexQueue.poll();
                if (indexQueue.peek() != null && indexQueue.peek() <= i + 1) indexQueue.poll();
                if (indexQueue.peek() != null && indexQueue.peek() <= j && indexQueue.size() == 1) continue;
                if (indexQueue.isEmpty()) continue;

                List<Integer> subResults = Arrays.asList(nums[i], nums[j], remainingNumber);
                subResults.sort(Comparator.naturalOrder());
                results.add(subResults);
            }
        }
        return new ArrayList<>(results);
    }
    public static Map<Integer, Queue<Integer>> convertToMapIndex(int[] nums) {
        Map<Integer, Queue<Integer>> mapUniqueIndex = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int currentIndex = i;
            mapUniqueIndex.compute(nums[i], (k, v) -> {
                if (v == null) {
                    Queue<Integer> initValue = new ArrayDeque<>();
                    initValue.offer(currentIndex);
                    return initValue;
                }
                v.add(currentIndex);
                return v;
            });
        }
        return mapUniqueIndex;
    }

    public static void main(String[] args) {
        int[] nums = {-1,0,1,2,-1,-4};
        threeSum(nums, 0);
    }
}
