package com.nguyenvm.sort.quick_sort;

// https://leetcode.com/problems/sort-an-array/

public class QuickSort {
    /**
     * Refer: https://www.algolist.net/Algorithms/Sorting/Quicksort
     * Refer: https://stackoverflow.com/a/27886231/8697491
     * <p>
     * There are indeed a few minor problem in your description of the algorithm. One is that either step 3 or step 4 need to include elements that are equal to the pivot. Let's rewrite it like this:
     * <p>
     * My understanding of quick sort is
     * <p>
     * 1. Choose a pivot value (in this case, choose the value of the middle element)
     * 2. Initialize left and right pointers at extremes.
     * 3. Starting at the left pointer and moving to the right, find the first element which is greater than or equal to the pivot value.
     * 4. Similarly, starting at the right pointer and moving to the left, find the first element, which is smaller than pivot value
     * 5. Swap elements found in 3 and 4.
     * 6. Repeat 3,4,5 until left pointer is greater or equal to right pointer.
     * 7. Repeat the whole thing for the two subarray to the left and the right of the left pointer.
     * pivot value: 6, left pointer at 8, right pointer at 11
     * 8,7,1,2,6,9,10,2,11 left pointer stays at 8, right pointer moves to 2
     * 2,7,1,2,6,9,10,8,11 swapped 2 and 8, left pointer moves to 7, right pointer moves to 2
     * 2,2,1,7,6,9,10,8,11 swapped 2 and 7, left pointer moves to 7, right pointer moves to 1
     * pointers have now met / crossed, subdivide between 1 and 7 and continue with two subarrays
     */
    public static void quickSort(int low, int high, int[] work) {
        int index = partition(low, high, work);
        if (low < index - 1) quickSort(low, index - 1, work);
        if (index < high) quickSort(index, high, work);
    }

    public static int partition(int low, int high, int[] work) {
        int left = low, right = high;
        int pivot = work[(low + high) / 2];

        while (left <= right) {
            while (work[left] < pivot) left++;
            while (work[right] > pivot) right--;
            if (left > right) break;

            swap(left, right, work);
            left++;
            right--;
        }

        return left;
    }

    public static void quickSortWithRecursion(int low, int high, int[] work) {
        if (low >= high) return;

        int left = low, right = high;
        int pivot = work[(low + high) / 2];

        while (left <= right) {
            while (work[left] < pivot) left++;
            while (work[right] > pivot) right--;
            if (left > right) break;

            swap(left, right, work);
            left++;
            right--;
        }

        quickSortWithRecursion(low, right, work);
        quickSortWithRecursion(left, high, work);
    }

    public static void print(int[] work) {
        StringBuilder result = new StringBuilder(work.length);
        for (int i = 0; i < work.length; i++) {
            result.append(work[i] + ", ");
        }
        System.out.println(result.toString());
    }

    public static void swap(int i, int j, int[] array) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void main(String[] args) {
//        {}
//        { 1 }
//        { 1, 2 }
//        { 2, 1 }
//        { 1, 2, 3 }
//        { 3, 2, 1 }
//        { 2, 3, 1 }
//        { 8, 3, 7, 9, 6, 1, 9, 10 }
//        { 8, 2, 78, 892, 11, 0, 34 }
//        { 9, 03, 83, 9, 2, 0, 1, 65, 2, 822, 9, 11, 22, 3, 3, 3, 47 }
//        { -6, 9, 0, 1, 17, 91, 0, 178 }
//        { -3, -2, -1, -9, -5, -1, -19, -33 }
//        { -5, -6, -7, 0, 0, 0, 0, -8, 1, 2, 3 }
        int[] work = new int[]{1, 12, 5, 26, 7, 14, 3, 7, 2};
        quickSort(0, work.length - 1, work);
    }
}
