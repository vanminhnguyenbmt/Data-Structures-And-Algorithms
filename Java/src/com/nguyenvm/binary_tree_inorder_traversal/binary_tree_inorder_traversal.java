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

        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> treeNodeList = new Stack<>();
        treeNodeList.add(root);

        TreeNode current = root;
        while (current != null) {
            current = current.left;
            if (current == null) break;
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
        Integer[] data = new Integer[]{5, 4, 8, 11, null, 13, 4, 7, 2, null, null, 5, 1, 12, 13, 15, 16, 17, 18, 19, 20};

        TreeNode treeNode = new TreeNode();
        treeNode = treeNode.buildTreeNode(data);

        inorderTraversal(treeNode);
    }
}
