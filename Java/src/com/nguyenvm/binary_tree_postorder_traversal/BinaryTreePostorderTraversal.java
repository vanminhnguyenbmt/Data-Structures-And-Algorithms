package com.nguyenvm.binary_tree_postorder_traversal;

//https://leetcode.com/problems/binary-tree-postorder-traversal/

import com.nguyenvm.TreeNode.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class BinaryTreePostorderTraversal {
    public static List<Integer> postorderTraversal(TreeNode root) {
        if (root == null) return Collections.EMPTY_LIST;

        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> treeNodeList = new Stack<>();
        Stack<TreeNode> temp = new Stack<>();
        treeNodeList.add(root);

        // Postorder (Left, Right, Root)
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
        Integer[] data = new Integer[]{5, 4, 8, 11, null, 13, 4, 7, 2, null, null, 5, 1, 12, 13, 15, 16, 17, 18, 19, 20};

        TreeNode treeNode = new TreeNode();
        treeNode = treeNode.buildTreeNode(data);

        List<Integer> result = new ArrayList<>();
        result.addAll(postorderTraversal(treeNode));
    }
}
