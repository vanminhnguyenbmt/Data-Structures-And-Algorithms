package com.nguyenvm.binary_tree_inorder_traversal;

//https://leetcode.com/problems/binary-tree-inorder-traversal/

import com.nguyenvm.TreeNode.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class binary_tree_inorder_traversal {
    public static List<Integer> inorderTraversal(TreeNode root) {
        if (root == null) return Collections.EMPTY_LIST;

        List<Integer> items = new ArrayList<>();

        Stack<TreeNode> treeNodes = new Stack<>();
        TreeNode current = root;

        // Inorder (Left, Root, Right)
        while (!treeNodes.isEmpty() || current != null) {
            if (current != null) {
                treeNodes.add(current);
                current = current.left;

                continue;
            }

            current = treeNodes.pop();
            items.add(current.val);

            current = current.right;
        }

        return items;
    }

    public static void main(String[] args) {
        Integer[] data = new Integer[]{5, 4, 8, 11, null, 13, 4, 7, 2, null, null, 5, 1, 12, 13, 15, 16, 17, 18, 19, 20};

        TreeNode treeNode = new TreeNode();
        treeNode = treeNode.buildTreeNode(data);

        inorderTraversal(treeNode);
    }
}
