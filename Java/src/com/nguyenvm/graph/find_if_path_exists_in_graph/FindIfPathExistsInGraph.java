package com.nguyenvm.graph.find_if_path_exists_in_graph;

//https://leetcode.com/problems/find-if-path-exists-in-graph/

import java.util.Iterator;
import java.util.LinkedList;

public class FindIfPathExistsInGraph {
    public static boolean validPath(int n, int[][] edges, int start, int end) {
        LinkedList<Integer>[] adj = new LinkedList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new LinkedList<>();
        }

        int length = edges.length;
        for (int i = 0; i < length; i++) {
            adj[edges[i][0]].add(edges[i][1]);
            adj[edges[i][1]].add(edges[i][0]);
        }

        boolean[] visited = new boolean[n];
        visited[start] = true;

        LinkedList<Integer> queue = new LinkedList();
        queue.add(start);
        while (!queue.isEmpty()) {
            int v = queue.poll();
            if (end == v) {
                return true;
            }
            Iterator<Integer> adjItem = adj[v].listIterator();
            while (adjItem.hasNext()) {
                int item = adjItem.next();
                if (!visited[item]) {
                    visited[item] = true;
                    queue.add(item);
                }
            }
        }

        return false;
    }

    public static int findPath(int[] parent, int x) {
        return parent[x] == -1 ? x : findPath(parent, parent[x]);
    }

    public static boolean isValidPath(int n, int[][] edges, int u, int v) {
//        union find
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = -1;
        }

        for (int[] edge : edges) {
            int p1 = findPath(parent, edge[0]);
            int p2 = findPath(parent, edge[1]);

            if (p1 != p2) parent[p1] = p2;
        }

        return findPath(parent, u) == findPath(parent, v);
    }

    public static void main(String[] args) {
        int n = 10, start = 7, end = 5;
        int[][] edges = new int[][]{{0, 7}, {0, 8}, {6, 1}, {2, 0}, {0, 4}, {5, 8}, {4, 7}, {1, 3}, {3, 5}, {6, 5}};
//        validPath(n, edges, start, end);

        isValidPath(n, edges, start, end);
    }
}
