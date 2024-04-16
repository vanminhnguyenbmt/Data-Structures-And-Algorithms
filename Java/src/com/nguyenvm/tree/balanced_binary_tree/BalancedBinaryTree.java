package com.nguyenvm.tree.balanced_binary_tree;

import com.nguyenvm.tree.TreeNode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

//https://leetcode.com/problems/balanced-binary-tree/

public class BalancedBinaryTree {
    public static boolean isBalanced(TreeNode root) {
        if (root == null) return true;

        Stack<TreeNode> treeNodes = new Stack<>();

        Map<TreeNode, Integer> cachingHeight = new HashMap<>();
        TreeNode current = root;
        TreeNode lastVisited = null;
        while (!treeNodes.isEmpty() || current != null) {
            if (current != null) {
                treeNodes.add(current);
                current = current.left;
                continue;
            }

            current = treeNodes.peek();
            if (current.right == null || current.right == lastVisited) {
                lastVisited = treeNodes.pop();

                int hL = cachingHeight.getOrDefault(lastVisited.left, 0);
                int hR = cachingHeight.getOrDefault(lastVisited.right, 0);

                if (Math.abs(hL - hR) > 1) return false;

                cachingHeight.put(lastVisited, Math.max(hL, hR) + 1);

                if (cachingHeight.containsKey(lastVisited.left) && cachingHeight.containsKey(lastVisited.right)) {
                    cachingHeight.remove(lastVisited.left);
                    cachingHeight.remove(lastVisited.right);
                }
                current = null;
                continue;
            }

            if (current.right != null) {
                current = current.right;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        Integer[] data = new Integer[]{3,9,20,null,null,15,7};

        TreeNode treeNode = TreeNode.buildTreeNode(data);

        isBalanced(treeNode);
    }
}
