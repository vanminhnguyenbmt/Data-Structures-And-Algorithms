package com.nguyenvm.binary_tree.binary_tree_postorder_traversal;

//https://leetcode.com/problems/binary-tree-postorder-traversal/

import com.nguyenvm.binary_tree.TreeNode;

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

    public static List<Integer> postorderTraversal1(TreeNode root) {
        if (root == null) return Collections.EMPTY_LIST;

        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> treeNodeList = new Stack<>();
        TreeNode current = root;
        TreeNode lastVisited = null;

        // Postorder (Left, Right, Root)
        while (!treeNodeList.isEmpty() || current != null) {
            if (current != null) {
                treeNodeList.add(current);
                current = current.left;
                continue;
            }

            current = treeNodeList.peek();
            if (current.right == null || current.right == lastVisited) {
                lastVisited = treeNodeList.pop();
                result.add(current.val);

                current = null;
                continue;
            }

            if (current.right != null) {
                current = current.right;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        Integer[] data = new Integer[]{5, 4, 8, 11, null, 13, 4, 7, 2, null, null, 5, 1, 12, 13, 15, 16, 17, 18, 19, 20};

        TreeNode treeNode = TreeNode.buildTreeNode(data);

        List<Integer> result = new ArrayList<>();
        result.addAll(postorderTraversal1(treeNode));
    }
}
