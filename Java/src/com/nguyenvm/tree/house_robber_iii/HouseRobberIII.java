package com.nguyenvm.tree.house_robber_iii;

// https://leetcode.com/problems/house-robber-iii/

import com.nguyenvm.tree.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class HouseRobberIII {
    public static int rob(TreeNode root) {
        if (root == null) return 0;
        Map<TreeNode, int[]> cache = new HashMap<>();

        Deque<TreeNode> deque = new ArrayDeque<>();
        TreeNode current = root;
        TreeNode lastVisited = null;

        while (!deque.isEmpty() || current != null) {
            int[] total = new int[2];
            if (current != null) {
                deque.offer(current);
                current = current.left;
                continue;
            }

            current = deque.peekLast();
            if (current.right == null || current.right == lastVisited) {
                lastVisited = deque.pollLast();

                int[] left = cache.getOrDefault(current.left, new int[2]);
                int[] right = cache.getOrDefault(current.right, new int[2]);
                total[0] = current.val + left[1] + right[1];
                total[1] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);

                cache.put(current, total);
                current = null;
                continue;
            }

            if (current.right != null) {
                current = current.right;
            }
        }

        int[] result = cache.get(root);
        return Math.max(result[0], result[1]);
    }

    public static void main(String[] args) {
        Integer[] data = new Integer[]{4, 1, null, 2, null, 3};
        rob(TreeNode.buildTreeNode(data));
    }
}
