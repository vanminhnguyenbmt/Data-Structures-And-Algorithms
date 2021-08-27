package com.nguyenvm.binary_tree_inorder_traversal;

//https://leetcode.com/problems/binary-tree-inorder-traversal/

import java.util.*;
import java.util.stream.Collectors;

public class binary_tree_inorder_traversal {
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

        public TreeNode buildTreeNode(Integer[] data) {
            TreeNode treeNode = new TreeNode(data[0]);

            Queue<Integer> queue = new LinkedList<>();
            int length = data.length;
            for (int i = 1; i < length; i++) {
                queue.offer(data[i]);
            }

            Queue<TreeNode> treeNodeList = new LinkedList<>();
            treeNodeList.offer(treeNode);

            while (!queue.isEmpty()) {
                TreeNode current = treeNodeList.poll();
                Integer left = queue.poll();
                Integer right = queue.poll();

                if (left != null) {
                    TreeNode nLeft = new TreeNode(left);
                    current.left = nLeft;
                    treeNodeList.offer(nLeft);
                }

                if (right != null) {
                    TreeNode nRight = new TreeNode(right);
                    current.right = nRight;
                    treeNodeList.offer(nRight);
                }
            }

            return treeNode;
        }
    }

    public static List<Integer> inorderTraversal(TreeNode root) {
        if (root == null) return Collections.EMPTY_LIST;

        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> treeNodeList = new Stack<>();
        treeNodeList.add(root);

        TreeNode current = root;
        while (current != null) {
            current = current.left;
            if(current == null) break;
            treeNodeList.add(current);
        }
        current = null;

        while (!treeNodeList.isEmpty()) {
            TreeNode pick = treeNodeList.pop();
            result.add(pick.val);

            TreeNode nRight = pick.right;

            while (nRight != null) {
                treeNodeList.add(nRight);
                nRight = nRight.left;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        Integer[] data = new Integer[]{1, null, 2, 3};

        TreeNode treeNode = new TreeNode();
        treeNode = treeNode.buildTreeNode(data);

        inorderTraversal(treeNode);
    }
}
