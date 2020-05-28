package com.nguyenvm.trapping_rain_water;

// https://leetcode.com/problems/trapping-rain-water/

class Problem42 {
    public static void main(String[] args) {
        int[] input = {2, 0 ,2};

        System.out.println(computing(input.length, input));
    }

    public static int computing(int length, int[] input) {
        int[] maxPoint = {0, 0};
        if (maxPoint[0] == (length - 1) || length == 2) {
            return 0;
        }

        int tempCount = 0;
        int realCount = 0;
        for (int i = maxPoint[0]; i < length; i++) {
            if (input[i] != 0 && input[i] >= maxPoint[1]) {
                maxPoint[0] = i;
                maxPoint[1] = input[i];

                if (tempCount > 0) {
                    realCount += tempCount;
                    tempCount = 0;
                    continue;
                }
            }

            if (maxPoint[1] == 0) {
                continue;
            }

            if (input[i] < maxPoint[1]) {
                tempCount += maxPoint[1] - input[i];
            }

            if (i == length - 1 && maxPoint[0] != i && tempCount > 0) {
                int[] tmpInput = new int[length - maxPoint[0]];
                int tmpIndex = 0;
                for (int j = length - 1; j >= maxPoint[0]; j--) {
                    tmpInput[tmpIndex] = input[j];
                    tmpIndex++;
                }
                maxPoint[0] = 0;
                maxPoint[1] = tmpInput[0];
                realCount += computing(tmpInput.length, tmpInput);
                return realCount;
            }
        }

        return realCount;
    }
}
