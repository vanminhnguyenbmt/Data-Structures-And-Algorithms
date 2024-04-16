package com.nguyenvm.tree;

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

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

    public static TreeNode buildTreeNodeRecursive(int[] data, TreeNode root, int i) {
        if (i < data.length) {
            TreeNode treeNode = new TreeNode(data[i]);
            root = treeNode;

            root.left = buildTreeNodeRecursive(data, root.left, 2 * i + 1);
            root.right = buildTreeNodeRecursive(data, root.right, 2 * i + 2);
        }

        return root;
    }

    public static TreeNode buildTreeNode(Integer[] data) {
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

    public static int height(TreeNode root) {
        if (root == null) return 0;

        int height = 0;
        Queue<TreeNode> treeNodes = new LinkedList<>();
        treeNodes.add(root);
        treeNodes.add(null);

        while (!treeNodes.isEmpty()) {
            TreeNode tmp = treeNodes.poll();

            if (tmp == null) {
                height++;
            }

            if (tmp != null) {
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

    public static void cachingHeight(TreeNode root, Map<TreeNode, Integer> cachingHeight) {
        if (root == null) return;
        if (cachingHeight.containsKey(root)) return;

        Stack<TreeNode> treeNodes = new Stack<>();

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

                cachingHeight.put(lastVisited, Math.max(hL, hR) + 1);

                current = null;
                continue;
            }

            if (current.right != null) {
                current = current.right;
            }
        }
    }
}
