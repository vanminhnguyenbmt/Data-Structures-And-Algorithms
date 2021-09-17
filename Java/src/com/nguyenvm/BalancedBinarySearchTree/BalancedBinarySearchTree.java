package com.nguyenvm.BalancedBinarySearchTree;

//https://leetcode.com/problems/balance-a-binary-search-tree/

import com.nguyenvm.TreeNode.TreeNode;

import java.util.*;

public class BalancedBinarySearchTree {
    public static TreeNode balanceBST(TreeNode root) {
        if (root == null) return null;
        List<Integer> items = new ArrayList<>();
        inorderArray(root, items);
        int l = 0;
        int r = items.size() - 1;
        int m = (l + r) / 2;
        root = new TreeNode(items.get(m));

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        Queue<Integer[]> integers = new LinkedList<>();
        integers.offer(new Integer[]{l, m - 1});
        integers.offer(new Integer[]{m + 1, r});

        while (!integers.isEmpty()) {
            Integer[] left = integers.poll();
            Integer[] right = integers.poll();
            TreeNode current = queue.poll();

            if (left[0] <= left[1]) {
                l = left[0];
                r = left[1];
                m = (l + r) / 2;
                if (l <= r) {
                    integers.offer(new Integer[]{l, m - 1});
                    integers.offer(new Integer[]{m + 1, r});
                }

                current.left = new TreeNode(items.get(m));
                queue.offer(current.left);
            }

            if (right[0] <= right[1]) {
                l = right[0];
                r = right[1];
                m = (l + r) / 2;
                if (l <= r) {
                    integers.offer(new Integer[]{l, m - 1});
                    integers.offer(new Integer[]{m + 1, r});
                }

                current.right = new TreeNode(items.get(m));
                queue.offer(current.right);
            }
        }

        return root;
    }

    public static void inorderArray(TreeNode root, List<Integer> items) {
        Stack<TreeNode> treeNodes = new Stack<>();
        TreeNode current = root;
        while (!treeNodes.isEmpty() || current != null) {
            if (current != null) {
                treeNodes.add(current);
                current = current.left;

                continue;
            }

            current = treeNodes.pop();
            items.add(current.val);

            if (current.right == null) {
                current = null;
                continue;
            }

            if (current.right != null) {
                current = current.right;
            }
        }
    }

    public static TreeNode balanceInsertionBST(TreeNode root) {
        if (root == null) return null;

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

                // Left case
                if (hL > hR && (hL - hR) > 1) {
                    int tmpH = cachingHeight.get(lastVisited.left);

                    current = lastVisited.left;
                    // Left Right case, Left Rotate
                    if (cachingHeight.getOrDefault(current.left, 0) < cachingHeight.getOrDefault(current.right, 0)) {
                        int checkH = cachingHeight.get(current);

                        TreeNode tmpRoot = current.right;
                        current.right = tmpRoot.left;
                        tmpRoot.left = current;

                        cachingHeight.put(tmpRoot, checkH);
                        lastVisited.left = tmpRoot;
                    }

                    // Left Left case, Right rotate
                    TreeNode tmpRoot = lastVisited.left;
                    lastVisited.left = tmpRoot.right;
                    tmpRoot.right = lastVisited;

                    cachingHeight.put(tmpRoot, tmpH);
                    if (treeNodes.isEmpty()) {
                        return tmpRoot;
                    }
                    current = treeNodes.peek();
                    if (current.left == lastVisited) {
                        current.left = tmpRoot;
                    }
                    if (current.right == lastVisited) {
                        current.right = tmpRoot;
                    }

                    current = null;
                    continue;
                }

                // Right case
                if (hL < hR && (hR - hL) > 1) {
                    int tmpH = cachingHeight.get(lastVisited.right);

                    current = lastVisited.right;
                    // Right Left case, Left Rotate
                    if (cachingHeight.getOrDefault(current.left, 0) > cachingHeight.getOrDefault(current.right, 0)) {
                        int checkH = cachingHeight.get(current);

                        TreeNode tmpRoot = current.left;
                        current.left = tmpRoot.right;
                        tmpRoot.right = current;

                        cachingHeight.put(tmpRoot, checkH);
                        lastVisited.right = tmpRoot;
                    }

                    // Right Right case, Left rotate
                    TreeNode tmpRoot = lastVisited.right;
                    lastVisited.right = tmpRoot.left;
                    tmpRoot.left = lastVisited;

                    cachingHeight.put(tmpRoot, tmpH);
                    if (treeNodes.isEmpty()) {
                        return tmpRoot;
                    }
                    current = treeNodes.peek();
                    if (current.left == lastVisited) {
                        current.left = tmpRoot;
                    }
                    if (current.right == lastVisited) {
                        current.right = tmpRoot;
                    }
                    current = null;
                    continue;
                }

                cachingHeight.put(lastVisited, Math.max(hL, hR) + 1);

                current = null;
                continue;
            }

            if (current.right != null) {
                current = current.right;
            }
        }

        return root;
    }

    public static void main(String[] args) {
        // left left {10, 8, 11, 6, 9, null, 12, 4, 7, null, null, null, null, 3}
        // left right {10, 6, 11, 5, 8, null, null, null, null, 7, 9, null, null, null}
        // right right {10, 6, 12, null, null, 11, 14, null, null, 13, 15}
        Integer[] data = new Integer[]{1, null, 15, 14, 17, 7, null, null, null, 2, 12, null, 3, 9, null, null, null, null, 11};
        TreeNode treeNode = new TreeNode();
        treeNode = treeNode.buildTreeNode(data);

        balanceBST(treeNode);
    }
}
