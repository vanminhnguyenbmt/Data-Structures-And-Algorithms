package com.nguyenvm.binary_tree.balanced_binary_search_tree;

//https://leetcode.com/problems/balance-a-binary-search-tree/

import com.nguyenvm.binary_tree.TreeNode;

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

        if(!isBalanced(root, cachingHeight)) {
            return balanceInsertionBST(root);
        }
        return root;
    }

    public static int getBalance(TreeNode node, Map<TreeNode, Integer> cachingHeight) {
        return cachingHeight.getOrDefault(node.right, 0) - cachingHeight.getOrDefault(node.left, 0);
    }

    public static boolean isBalanced(TreeNode root, Map<TreeNode, Integer> cachingHeight) {
        if(root == null) {
            return true;
        }
        boolean left = isBalanced(root.left, cachingHeight);
        boolean right = isBalanced(root.right, cachingHeight);
        return left && right && Math.abs(getBalance(root, cachingHeight)) <= 1;
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
//        [88265,83067,88942,1931,86070,88632,97916,10,40355,83240,87828,88314,88935,93107,98624,null,445,19602,50100,83231,85900,87243,88165,null,88574,88726,null,89038,95508,98191,99899,310,1273,3621,27988,45914,65855,83127,null,84043,85995,86540,null,null,null,null,null,null,null,88962,91698,95310,95878,98106,98287,98910,null,200,null,1030,1731,2125,4675,27601,31475,43587,49227,55475,70179,null,null,83740,84534,null,null,86196,86796,null,null,89588,91950,94022,null,95751,95893,null,null,null,null,null,98928,null,null,null,1167,1631,1874,2004,2836,4649,17674,19836,null,28897,33618,40903,45489,46813,49677,54413,57975,68558,76860,83546,83905,84430,85306,null,null,null,null,89120,91446,91780,92014,93798,95100,null,null,null,96306,null,99471,null,null,null,null,null,null,null,null,2435,3500,4322,null,5519,19447,19795,24476,28854,30868,31856,38458,40672,42266,44336,null,46167,48818,49228,null,50931,54818,57075,62815,67703,69443,73510,80225,null,83672,null,84017,null,null,85101,85665,89040,89207,90061,91565,null,91925,91991,92484,93639,null,94176,null,null,96865,99333,null,null,null,2987,null,4135,4407,5065,16993,19183,19507,null,null,20737,25949,28298,null,29504,31419,31734,32891,34069,39672,null,40822,42183,43332,44030,45044,null,null,47082,null,null,49631,50439,52667,54768,54975,55787,57157,62661,65359,66013,68271,68827,70019,71891,75218,79639,80319,null,null,null,null,84794,85300,null,null,null,89044,null,89260,null,90548,91449,91662,null,null,null,null,92450,92931,93371,null,94127,null,null,97907,99301,null,2864,null,null,null,null,null,4958,5101,15176,17069,18148,19184,19452,19585,20109,21835,24824,27163,28207,28476,29242,30615,31381,null,null,null,32859,null,34036,36073,38586,null,null,null,41442,null,42510,null,43945,44182,44594,null,46997,47305,49550,null,null,50859,51618,53204,54721,null,null,55449,null,56522,null,null,61661,62795,63069,null,null,66664,68027,68393,68757,69151,null,null,71335,72939,74595,76409,79365,79972,80318,81713,null,85058,null,null,null,89061,null,89297,90249,null,null,null,null,null,92443,null,92586,null,null,null,94125,null,97200,null,99023,null,null,null,4770,null,null,5401,8016,15354,null,17488,null,null,null,null,null,null,null,null,null,20404,20982,23635,24642,25159,26662,27243,null,null,null,28676,28945,null,null,null,null,null,null,null,33671,null,35340,37967,null,39461,41251,41715,null,43060,43701,null,null,44239,null,44868,46992,null,null,47402,null,null,null,null,51286,51747,52726,53728,null,null,null,null,55885,null,60390,62129,null,null,null,64826,66192,null,67896,null,68319,null,null,null,68920,69252,70858,71346,71902,73479,73585,74681,75219,76663,78662,79474,79739,null,null,null,81406,82981,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,5330,5506,5545,13304,15211,15364,17108,null,20243,20435,null,21228,22207,23885,null,24710,24964,25506,26020,27015,null,null,null,null,null,28975,33619,33978,34199,35570,37950,38055,39221,39639,null,null,41556,null,42723,null,null,43831,null,null,null,null,46841,null,null,48278,50941,51288,null,52161,null,52845,null,54319,55854,55907,59194,61633,61817,62460,63205,64934,null,null,null,null,null,null,null,null,null,69400,70508,71148,null,71617,null,null,73185,null,null,73989,null,null,null,75982,76475,76708,78223,78928,null,79618,null,79924,81344,null,82149,null,5167,null,null,null,null,5929,8630,13715,15206,null,null,15585,17105,17347,null,null,null,20669,21062,21831,null,22914,null,24245,null,null,24954,null,25207,null,25958,26130,26967,null,null,null,null,null,null,null,34097,null,35526,36008,36698,null,37988,38147,null,39381,39572,null,null,null,null,null,null,43920,null,null,48090,48670,null,51277,null,null,51996,null,null,null,54318,54359,null,null,null,null,58506,59373,61228,null,null,null,62339,null,63155,64814,null,65240,null,null,null,null,null,null,null,null,null,null,73913,74380,75492,null,null,null,null,null,77369,78462,null,78983,null,null,null,null,81336,null,null,82595,5162,5326,null,7609,8152,12283,null,15120,null,null,null,16296,null,null,null,null,null,null,null,null,21478,null,22346,23369,24101,null,null,null,null,null,null,null,null,26476,null,null,34087,null,null,null,null,null,36492,37733,null,null,null,null,null,null,null,null,null,null,null,48130,48515,48767,null,null,null,null,null,null,null,null,58374,59057,null,60085,61197,null,null,null,null,null,64045,null,65155,65332,null,null,null,74469,75452,null,76956,78151,null,null,null,79165,null,null,82266,null,null,null,null,null,6132,8001,null,8374,9289,12817,15088,null,15586,16312,null,21716,null,null,null,23539,null,null,26436,null,null,null,36128,null,36816,null,null,null,null,null,null,null,null,null,58798,null,60025,60119,null,null,63760,64574,null,null,null,null,null,null,null,null,76950,77307,77933,78183,null,null,null,null,5990,6326,null,8007,8370,null,9176,11394,null,13187,15075,null,null,15689,null,null,null,null,null,null,null,null,null,null,null,null,58792,null,null,null,null,null,null,63951,null,null,null,null,null,null,null,null,null,null,null,null,null,6535,null,null,null,null,9056,null,10519,11998,13029,13228,15060,null,null,16240,58738,null,63867,null,null,null,8698,null,9434,11098,11774,null,12856,13131,null,null,14242,null,null,null,null,null,null,null,null,null,null,10365,10941,null,null,11882,null,null,null,null,13730,null,9699,null,10663,11095,null,null,null,null,null,10009,10581,10797,10966]
        Integer[] data = new Integer[]{1, null, 15, 14, 17, 7, null, null, null, 2, 12, null, 3, 9, null, null, null, null, 11};
        TreeNode treeNode = new TreeNode();
        treeNode = treeNode.buildTreeNode(data);

        balanceInsertionBST(treeNode);
    }
}
