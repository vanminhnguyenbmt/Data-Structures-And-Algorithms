package com.nguyenvm.binary_tree.maximum_product_of_splitted_binary_tree;

//https://leetcode.com/problems/maximum-product-of-splitted-binary-tree/

import com.nguyenvm.binary_tree.TreeNode;

import java.util.LinkedList;
import java.util.List;

public class MaximumProductOfSplittedBinaryTree {
    public static int sumTreeNode(TreeNode treeNode) {
        int total = 0;
        List<TreeNode> treeNodeList = new LinkedList<>();
        treeNodeList.add(treeNode);

        while (!treeNodeList.isEmpty()) {
            TreeNode temp = treeNodeList.get(0);
            total += temp.val;

            if (temp.left != null) {
                treeNodeList.add(temp.left);
            }

            if (temp.right != null) {
                treeNodeList.add(temp.right);
            }

            treeNodeList.remove(0);
        }

        return total;
    }

    public static int maxProductOfSubtrees(TreeNode treeNode) {
        int mod = 1000000007;
        int total = sumTreeNode(treeNode);
        long max = 0l;

        List<TreeNode> treeNodeList = new LinkedList<>();
        treeNodeList.add(treeNode);

        while (!treeNodeList.isEmpty()) {
            TreeNode pick = treeNodeList.get(0);
            if (pick.left != null) {
                treeNodeList.add(pick.left);
            }

            if (pick.right != null) {
                treeNodeList.add(pick.right);
            }
            int totalPicked = sumTreeNode(pick);
            max = Math.max((long) (total - totalPicked) * totalPicked, max);
            treeNodeList.remove(0);
        }

        return (int) (max % mod);
    }

    public static class Recursive {
        public double max = 0;
        public int total = 0;

        public int sumTreeNodeRecursive(TreeNode treeNode) {
            if (treeNode == null) return 0;
            return sumTreeNodeRecursive(treeNode.left) + treeNode.val + sumTreeNodeRecursive(treeNode.right);
        }

        public int maxProductOfSubtreesRecursive(TreeNode treeNode) {
            if (treeNode == null) return 0;

            int totalSub = maxProductOfSubtreesRecursive(treeNode.left) + maxProductOfSubtreesRecursive(treeNode.right) + treeNode.val;

            max = Math.max((long) (total - totalSub) * totalSub, max);

            return totalSub;
        }
    }

    public static void main(String[] args) {
        Integer[] data = new Integer[]{2, 3, 9, 10, 7, 8, 6, 5, 4, 11, 1};
        int mod = 1000000007;

        TreeNode treeNode = TreeNode.buildTreeNode(data);

        Recursive recursive = new Recursive();
        recursive.total = recursive.sumTreeNodeRecursive(treeNode);
        recursive.maxProductOfSubtreesRecursive(treeNode);
        int max = (int) (recursive.max % mod);
    }
}
