package com.nguyenvm.binary_tree_postorder_traversal;

//https://leetcode.com/problems/binary-tree-postorder-traversal/

import java.util.*;

public class BinaryTreePostorderTraversal {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }

        public static TreeNode buildTreeNode(Integer[] data) {
            TreeNode treeNode = new TreeNode(data[0]);

            List<TreeNode> treeNodeList = new LinkedList<>();
            treeNodeList.add(treeNode);

            int length = data.length;
            for (int i = 1; i < length; i++) {
                TreeNode temp = treeNodeList.get(0);

                if (temp.left == null) {
                    if (data[i] == null) {
                        temp.left = new TreeNode(-999);
                        continue;
                    }
                    temp.left = new TreeNode(data[i]);
                    treeNodeList.add(temp.left);
                    continue;
                }

                if (temp.right == null) {
                    if (temp.left.val == -999) {
                        temp.left = null;
                    }

                    if (data[i] == null) {
                        treeNodeList.remove(0);
                        continue;
                    }

                    temp.right = new TreeNode(data[i]);
                    treeNodeList.add(temp.right);
                    treeNodeList.remove(0);
                    continue;
                }
            }

            return treeNode;
        }
    }

    public static List<Integer> postorderTraversal(TreeNode root) {
        if (root == null) return Collections.EMPTY_LIST;

        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> treeNodeList = new Stack<>();
        Stack<TreeNode> temp = new Stack<>();
        treeNodeList.add(root);

        while (!treeNodeList.isEmpty()) {
            TreeNode pick = treeNodeList.pop();
            temp.push(pick);

            if (pick.left != null) {
                treeNodeList.add(pick.left);
            }

            if (pick.right != null) {
                treeNodeList.add(pick.right);
            }
        }

        while (!temp.isEmpty()) {
            result.add(temp.pop().val);
        }

        return result;
    }

    public static void main(String[] args) {
        Integer[] data = new Integer[]{4, 2, null, 3, 1, null, null, 5};

        TreeNode treeNode = new TreeNode();
        treeNode = treeNode.buildTreeNode(data);

        List<Integer> result = new ArrayList<>();
        result.addAll(postorderTraversal(treeNode));
    }
}
