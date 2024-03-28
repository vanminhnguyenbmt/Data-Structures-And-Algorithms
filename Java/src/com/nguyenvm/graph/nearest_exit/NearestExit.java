package com.nguyenvm.graph.nearest_exit;

import java.util.ArrayDeque;
import java.util.Queue;

public class NearestExit {
    public static void main(String[] args) {
        char[][] maze = new char[][]{{'+', '+', '+'}, {'.', '.', '.'}, {'+', '+', '+'}};
        int[] entrance = new int[]{1, 0};

        System.out.println(nearestExit(maze, entrance));
    }

    public static int nearestExit(char[][] maze, int[] entrance) {
        int row = maze.length;
        int column = maze[0].length;
        char walls = '+';
        int point = 0;
        int currentRow;
        int currentColumn;
        int[][] walkArray = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(entrance);
        maze[entrance[0]][entrance[1]] = walls;

        while (!queue.isEmpty()) {
            point++;

            int n = queue.size();
            for (int i = 0; i < n; i++) {
                int[] current = queue.poll();

                for (int[] position : walkArray) {
                    currentRow = current[0] + position[0];
                    currentColumn = current[1] + position[1];

                    if (currentRow < 0 || currentRow >= row || currentColumn >= column || currentColumn < 0) continue;
                    if (maze[currentRow][currentColumn] == walls) continue;
                    if (currentRow == 0 || currentRow == row - 1 || currentColumn == 0 || currentColumn == column - 1)
                        return point;

                    maze[currentRow][currentColumn] = walls;
                    queue.add(new int[]{currentRow, currentColumn});
                }
            }
        }

        return -1;
    }
}
