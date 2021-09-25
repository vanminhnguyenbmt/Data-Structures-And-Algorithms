package com.nguyenvm.binary_tree_preorder_traversal;

import com.nguyenvm.tree_node.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class BinaryTreePreorderTraversal {
    public static List<Integer> preorderTraversal(TreeNode root) {
        if (root == null) return Collections.EMPTY_LIST;
        List<Integer> results = new ArrayList<>();

        Stack<TreeNode> stack = new Stack();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode current = stack.pop();
            results.add(current.val);
            if (current.right != null) {
                stack.push(current.right);
            }

            if (current.left != null) {
                stack.push(current.left);
            }
        }

        return results;
    }

    public static void main(String[] args) {
        Integer[] data = new Integer[]{5, 4, 8, 11, null, 13, 4, 7, 2, null, null, 5, 1, 12, 13, 15, 16, 17, 18, 19, 20};

        TreeNode treeNode = new TreeNode();
        treeNode = treeNode.buildTreeNode(data);

        List<Integer> result = new ArrayList<>();
        result.addAll(preorderTraversal(treeNode));
    }
}
