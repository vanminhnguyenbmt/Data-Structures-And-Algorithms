package com.nguyenvm.binary_tree_level_order_traversal_ii;

import com.nguyenvm.tree_node.TreeNode;

import java.util.*;

//https://leetcode.com/problems/binary-tree-level-order-traversal-ii/
public class LevelOrderTraversal {
    public static List<List<Integer>> levelOrderBottom(TreeNode root) {
        if (root == null) return Collections.EMPTY_LIST;

        LinkedList<List<Integer>> results = new LinkedList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        queue.offer(null);

        Stack<Integer> tmp = new Stack<>();
        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            if (current == null) {
                List<Integer> rowResult = new ArrayList<>();
                while (!tmp.isEmpty()) {
                    rowResult.add(tmp.pop());
                }
                results.addFirst(rowResult);

                if (!queue.isEmpty()) queue.add(null);
                continue;
            }

            tmp.push(current.val);
            if (current.right != null) queue.offer(current.right);
            if (current.left != null) queue.offer(current.left);
        }

        return results;
    }

    public static void main(String[] args) {
        Integer[] data = new Integer[]{5, 4, 8, 11, null, 13, 4, 7, 2, null, null, 5, 1, 12, 13, 15, 16, 17, 18, 19, 20};

        TreeNode treeNode = new TreeNode();
        treeNode = treeNode.buildTreeNode(data);

        levelOrderBottom(treeNode);
    }
}
