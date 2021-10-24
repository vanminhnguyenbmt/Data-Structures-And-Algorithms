package com.nguyenvm.graph.find_center_of_star_graph;

//https://leetcode.com/problems/find-center-of-star-graph/

public class FindCenterOfStarGraph {
    public static int findCenter(int[][] edges) {
        int n = edges.length;
        int[] count = new int[n + 2];

        for (int i = 0; i < n; i++) {
            int[] edge = edges[i];
            if (count[edge[0]] == n - 1) return edge[0];
            if (count[edge[1]] == n - 1) return edge[1];

            count[edge[0]] = count[edge[0]] + 1;
            count[edge[1]] = count[edge[1]] + 1;
        }

        return -1;
    }

    public static int tricky(int[][] edges) {
        return edges[0][0] == edges[1][0] || edges[0][0] == edges[1][1] ? edges[0][0] : edges[0][1];
    }

    public static void main(String[] args) {
//        int[][] edges = new int[][]{{1, 2}, {5, 1}, {1, 3}, {1, 4}};
        int[][] edges = new int[][]{{1, 2}, {2, 3}, {4, 2}};
        findCenter(edges);
    }
}
