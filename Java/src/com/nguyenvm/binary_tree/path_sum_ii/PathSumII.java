package com.nguyenvm.binary_tree.path_sum_ii;

//https://leetcode.com/problems/path-sum-ii/

import com.nguyenvm.binary_tree.TreeNode;

import java.util.*;

public class PathSumII {
    public static List<List<Integer>> pathSumII(TreeNode root, int targetSum) {
        List<List<Integer>> result = new ArrayList<>();

        if (root == null) return result;
        Queue<TreeNode> qNode = new LinkedList<>();
        Queue<List<Integer>> qPathValue = new LinkedList<>();
        Queue<Integer> qSum = new LinkedList<>();
        qNode.add(root);
        qPathValue.add(new LinkedList() {{
            add(root.val);
        }});
        qSum.add(root.val);

        while (!qNode.isEmpty()) {
            TreeNode last = qNode.poll();
            List<Integer> pathSum = qPathValue.poll();
            Integer tmpSum = qSum.poll();

            if (tmpSum == targetSum && last.left == null && last.right == null) {
                result.add(pathSum);
            }

            if (last.left != null) {
                qNode.add(last.left);

                List<Integer> tmpSums = new LinkedList<>(pathSum);
                tmpSums.add(last.left.val);

                qPathValue.add(tmpSums);

                qSum.add(tmpSum + last.left.val);
            }

            if (last.right != null) {
                qNode.add(last.right);

                pathSum.add(last.right.val);
                qPathValue.add(pathSum);

                qSum.add(tmpSum + last.right.val);
            }
        }

        return result;
    }

    public static void main(String[] args) {
//        Integer[] data = new Integer[]{5, 4, 8, 11, null, 13, 4, 7, 2, null, null, 5, 1, 12, 13, 15, 16, 17, 18, 19, 20, null, 22};
        Integer[] data = new Integer[]{1, 0, 1, 1, 2, 0, -1, 0, 1, -1, 0, -1, 0, 1, 0};
        TreeNode treeNode = TreeNode.buildTreeNode(data);
        pathSumII(treeNode, 2);
    }
}
