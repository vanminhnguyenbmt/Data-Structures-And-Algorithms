package com.nguyenvm.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode() {
    }

    public TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public TreeNode buildTreeNodeRecursive(int[] data, TreeNode root, int i) {
        if (i < data.length) {
            TreeNode treeNode = new TreeNode(data[i]);
            root = treeNode;

            root.left = buildTreeNodeRecursive(data, root.left, 2 * i + 1);
            root.right = buildTreeNodeRecursive(data, root.right, 2 * i + 2);
        }

        return root;
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
