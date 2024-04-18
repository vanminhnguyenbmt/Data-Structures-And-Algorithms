package com.nguyenvm.array.maximal_rectangle;

public class MaximalRectangle {

    public int maximalRectangle(char[][] matrix) {
        return Math.max(rectangleArea(matrix), areaRotated(matrix));
    }

    /**
     * matrix = {char[7][]@2549}
     * 0 = {char[5]@2550} [1, 0, 1, 0, 0]
     * 1 = {char[5]@2551} [1, 0, 1, 1, 1]
     * 2 = {char[5]@2552} [1, 1, 1, 1, 1]
     * 3 = {char[5]@2553} [1, 0, 1, 1, 0]
     * 4 = {char[5]@2554} [0, 1, 1, 1, 0]
     * 5 = {char[5]@2555} [1, 1, 1, 1, 0]
     * 6 = {char[5]@2556} [1, 1, 1, 1, 0]
     * <p>
     * rectangle = {int[7][]@2549}
     * 0 = {int[5]@2553} [1, 0, 1, 0, 0]
     * 1 = {int[5]@2554} [2, 0, 2, 1, 1]
     * 2 = {int[5]@2555} [3, 1, 3, 2, 2]
     * 3 = {int[5]@2556} [4, 0, 4, 3, 0]
     * 4 = {int[5]@2557} [0, 1, 5, 4, 0]
     * 5 = {int[5]@2558} [1, 2, 6, 5, 0]
     * 6 = {int[5]@2559} [2, 3, 7, 6, 0]
     */
    private int rectangleArea(char[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        int[][] rectangle = buildRectangle(rows, cols, matrix);
        int area = 0;

        for (int i = 0; i < rows; i++) {
            int min = -1;
            int width = 0;
            for (int j = 0; j < cols; j++) {
                if (rectangle[i][j] < 1) {
                    width = 0;
                    min = -1;
                    continue;
                }

                if (rectangle[i][j] < min || min == -1) {
                    min = rectangle[i][j];
                }

                if (rectangle[i][j] > min && min == 1) {
                    width = 0;
                    min = rectangle[i][j];
                }
                area = Math.max(min * ++width, area);
            }
        }

        return area;
    }

    private int[][] buildRectangle(int rows, int cols, char[][] matrix) {
        int[][] rectangle = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int cur = Character.getNumericValue(matrix[i][j]);
                if (i == 0) {
                    rectangle[i][j] = cur;
                    continue;
                }

                if (cur == 1) {
                    int prev = rectangle[i - 1][j];
                    rectangle[i][j] = cur + prev;
                }
            }
        }
        return rectangle;
    }

    private int areaRotated(char[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        char[][] retRotated = new char[col][row];
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                retRotated[c][row - 1 - r] = matrix[r][c];
            }
        }

        return rectangleArea(retRotated);
    }

    public static void main(String[] args) {
        char[][] matrix = new char[][]{
                {'0', '0', '1', '0', '1', '0', '0'},
                {'0', '1', '1', '1', '1', '1', '0'},
                {'0', '0', '1', '1', '1', '0', '0'},
                {'0', '1', '1', '1', '1', '1', '0'},
                {'0', '0', '1', '0', '1', '0', '0'}
        };
        System.out.println(new MaximalRectangle().maximalRectangle(matrix));
    }
}
