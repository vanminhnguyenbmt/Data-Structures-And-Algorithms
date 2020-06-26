package com.nguyenvm.subsests;

import java.util.ArrayList;
import java.util.List;

public class SubsetsCascading {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 4, 5};
        int length = nums.length;
        System.out.println(computingCascading(length, nums));
    }

    public static List<List<Integer>> computingCascading(int length, int[] nums) {
        List<List<Integer>> listResult = new ArrayList();
        listResult.add(new ArrayList<>());

        for (int num : nums) {
            List<List<Integer>> tmpResult = new ArrayList<>();
            for (List<Integer> results : listResult) {
                tmpResult.add(new ArrayList<Integer>(results) {{
                    add(num);
                }});
            }
            listResult.addAll(tmpResult);
        }

        return listResult;
    }
}
