package com.nguyenvm.binary_tree.invert_binary_tree;

import com.nguyenvm.binary_tree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class InvertBinaryTree {
    public static TreeNode invertTree(TreeNode root) {
        if (root == null) return null;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            TreeNode tmp = current.right;
            current.right = current.left;
            current.left = tmp;

            if (current.left != null) {
                queue.offer(current.left);
            }

            if (current.right != null) {
                queue.offer(current.right);
            }
        }
        return root;
    }

    public static void main(String[] args) {
        Integer[] data = new Integer[]{5, 4, 8, 11, null, 13, 4, 7, 2, null, null, 5, 1, 12, 13, 15, 16, 17, 18, 19, 20};

        TreeNode treeNode = TreeNode.buildTreeNode(data);

        invertTree(treeNode);
    }
}
