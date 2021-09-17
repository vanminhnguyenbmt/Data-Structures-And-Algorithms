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
                    current = lastVisited.left;
                    // Left Right case, Left Rotate
                    if (cachingHeight.getOrDefault(current.left, 0) < cachingHeight.getOrDefault(current.right, 0)) {
                        lastVisited.left = leftRotate(current, cachingHeight);
                    }

                    // Left Left case, Right rotate
                    lastVisited = rightRotate(lastVisited, cachingHeight);

                    if (treeNodes.isEmpty()) {
                        root = lastVisited;
                        break;
                    }
                    current = treeNodes.peek();
                    if (current.left == lastVisited.right) {
                        current.left = lastVisited;
                    }
                    if (current.right == lastVisited.right) {
                        current.right = lastVisited;
                    }

                    current = null;
                    continue;
                }

                // Right case
                if (hR > hL && (hR - hL) > 1) {
                    current = lastVisited.right;
                    // Right Left case, Right Rotate
                    if (cachingHeight.getOrDefault(current.left, 0) > cachingHeight.getOrDefault(current.right, 0)) {
                        lastVisited.right = rightRotate(current, cachingHeight);
                    }

                    // Right Right case, Left rotate
                    lastVisited = leftRotate(lastVisited, cachingHeight);

                    if (treeNodes.isEmpty()) {
                        root = lastVisited;
                        break;
                    }
                    current = treeNodes.peek();
                    if (current.left == lastVisited.left) {
                        current.left = lastVisited;
                    }
                    if (current.right == lastVisited.left) {
                        current.right = lastVisited;
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

    public static boolean balanceFactor(TreeNode lastVisited, Map<TreeNode, Integer> cachingHeight) {
        int hR = cachingHeight.getOrDefault(lastVisited.left, 0);
        int hL = cachingHeight.getOrDefault(lastVisited.right, 0);

        if ((hL > hR && (hL - hR) > 1) || (hR > hL && (hR - hL) > 1)) {
            return true;
        }

        return false;
    }

    public static TreeNode leftRotate(TreeNode current, Map<TreeNode, Integer> cachingHeight) {
        cachingHeight.remove(current);

        TreeNode tmpRoot = current.right;
        current.right = tmpRoot.left;
        tmpRoot.left = current;

        cachingHeight.remove(tmpRoot);
        TreeNode.cachingHeight(current, cachingHeight);
        TreeNode.cachingHeight(tmpRoot, cachingHeight);

        return tmpRoot;
    }

    public static TreeNode rightRotate(TreeNode current, Map<TreeNode, Integer> cachingHeight) {
        cachingHeight.remove(current);

        TreeNode tmpRoot = current.left;
        current.left = tmpRoot.right;
        tmpRoot.right = current;

        cachingHeight.remove(tmpRoot);
        TreeNode.cachingHeight(current, cachingHeight);
        TreeNode.cachingHeight(tmpRoot, cachingHeight);

        return tmpRoot;
    }

    public static void main(String[] args) {
        // left left {10, 8, 11, 6, 9, null, 12, 4, 7, null, null, null, null, 3}
        // left right {10, 6, 11, 5, 8, null, null, null, null, 7, 9, null, null, null}
        // right right {10, 6, 12, null, null, 11, 14, null, null, 13, 15}
        // right left {10, 6, 14, null, null, 12, 15, 11, 13, null}
//        {1,null,15,14,17,7,null,null,null,2,12,null,3,9,null,null,null,null,11}
        Integer[] data = new Integer[]{1, null, 15, 14, 17, 7, null, null, null, 2, 12, null, 3, 9, null, null, null, null, 11};
        TreeNode treeNode = new TreeNode();
        treeNode = treeNode.buildTreeNode(data);

        balanceInsertionBST(treeNode);
    }
}
