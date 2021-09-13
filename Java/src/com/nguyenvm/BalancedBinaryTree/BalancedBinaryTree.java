package com.nguyenvm.BalancedBinaryTree;

import com.nguyenvm.TreeNode.TreeNode;

import java.util.*;

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

    public static int height(TreeNode root, Map<TreeNode, Integer> cachingHeight) {
        if (root == null) return 0;
        if (cachingHeight.containsKey(root)) return cachingHeight.get(root);

        int height = 0;
        Queue<TreeNode> treeNodes = new LinkedList<>();
        treeNodes.add(root);
        treeNodes.add(null);

        TreeNode visited = null;
        while (!treeNodes.isEmpty()) {
            TreeNode tmp = treeNodes.poll();

            if (tmp == null) {
                height++;
                cachingHeight.put(visited, height);
            }

            if (tmp != null) {
                visited = tmp;
                if (tmp.left != null) treeNodes.offer(tmp.left);
                if (tmp.right != null) treeNodes.offer(tmp.right);
                continue;
            }

            if (!treeNodes.isEmpty()) {
                treeNodes.add(null);
            }
        }

        return height;
    }

    public static void main(String[] args) {
        Integer[] data = new Integer[]{3,9,20,null,null,15,7};

        TreeNode treeNode = new TreeNode();
        treeNode = treeNode.buildTreeNode(data);

        isBalanced(treeNode);
    }
}
