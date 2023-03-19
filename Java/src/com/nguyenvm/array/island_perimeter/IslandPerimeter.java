package com.nguyenvm.array.island_perimeter;

// https://leetcode.com/problems/island-perimeter/

import java.util.Iterator;
import java.util.LinkedList;

public class IslandPerimeter {
    public static int islandPerimeter(int[][] grid) {
        int perimeter = 0;

        int lengthI = grid.length;
        int lengthJ = grid[0].length;

        for (int i = 0; i < lengthI; i++) {
            for (int j = 0; j < lengthJ; j++) {
                if (grid[i][j] == 0) continue;

                perimeter += 4;
                // i - 1, j
                if (i - 1 >= 0 && i - 1 < lengthI && j >= 0 && j < lengthJ && grid[i - 1][j] == 1) {
                    perimeter -= 1;
                }

                // i + 1, j
                if (i + 1 >= 0 && i + 1 < lengthI && j >= 0 && j < lengthJ && grid[i + 1][j] == 1) {
                    perimeter -= 1;
                }

                // i, j - 1
                if (i >= 0 && i < lengthI && j - 1 >= 0 && j - 1 < lengthJ && grid[i][j - 1] == 1) {
                    perimeter -= 1;
                }

                // i, j + 1
                if (i >= 0 && i < lengthI && j + 1 >= 0 && j + 1 < lengthJ && grid[i][j + 1] == 1) {
                    perimeter -= 1;
                }
            }
        }

        return perimeter;
    }

    public static int islandPerimeterUsingAdj(int[][] grid) {
        int perimeter = 0;

        int lengthI = grid.length;
        int lengthJ = grid[0].length;
        LinkedList<Integer> adj[];
        adj = new LinkedList[lengthI];

        for (int i = 0; i < lengthI; i++) {
            adj[i] = new LinkedList();
            for (int j = 0; j < lengthJ; j++) {
                if (grid[i][j] == 1) adj[i].add(j);
            }
        }

        int adjLength = adj.length;
        for (int k = 0; k < adjLength; k++) {
            LinkedList<Integer> land = adj[k];
            if (land.isEmpty()) continue;

            Iterator<Integer> iLand = land.iterator();
            while (iLand.hasNext()) {
                Integer current = iLand.next();
                perimeter += 4;

                int x = -1, y = -1;

                x = k - 1;
                y = current;
                if (x >= 0 && x < lengthI && y < lengthJ && adj[x].contains(y)) {
                    perimeter -= 1;
                }

                x = k + 1;
                y = current;
                if (x >= 0 && x < lengthI && y < lengthJ && adj[x].contains(y)) {
                    perimeter -= 1;
                }

                x = k;
                y = current - 1;
                if (x >= 0 && x < lengthI && y < lengthJ && adj[x].contains(y)) {
                    perimeter -= 1;
                }

                x = k;
                y = current + 1;
                if (x >= 0 && x < lengthI && y < lengthJ && adj[x].contains(y)) {
                    perimeter -= 1;
                }
            }
        }

        return perimeter;
    }

    public static void main(String[] args) {
        int[][] grid = new int[][]{{0, 1, 0, 0}, {1, 1, 1, 0}, {0, 1, 0, 0}, {1, 1, 0, 0}};
        islandPerimeter(grid);
    }
}
