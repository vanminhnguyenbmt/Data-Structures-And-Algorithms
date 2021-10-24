package com.nguyenvm.graph.course_schedule_iii;

//https://leetcode.com/problems/course-schedule-iii/

import java.util.*;

public class CourseScheduleIII {
    public static int scheduleCourse(int[][] courses) {
        Arrays.sort(courses, (a, b) -> a[1] - b[1]);
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<Integer>((a, b) -> (b - a));
        int duration = 0;
        for (int[] course : courses) {
            if (course[0] > course[1]) continue;

            if (duration + course[0] <= course[1]) {
                duration += course[0];

                priorityQueue.add(course[0]);
                continue;
            }

            if (!priorityQueue.isEmpty() && priorityQueue.peek() > course[0]) {
                duration = duration - priorityQueue.poll() + course[0];
                priorityQueue.add(course[0]);
            }
        }

        return priorityQueue.size();
    }

    public static void main(String[] args) {
//        {100, 200}, {100, 300}, {100, 1000}, {100, 2000}, {200, 400}, {200, 500}
//        {5, 5}, {4, 6}, {2, 6}
//        {10, 20}, {4, 13}, {4, 4}, {3, 11}, {3, 5}, {3, 5}
//        {10, 12}, {6, 15}, {1, 12}, {3, 20}, {10, 19}
        int[][] courses = new int[][]{{10, 12}, {6, 15}, {1, 12}, {3, 20}, {10, 19}};
        scheduleCourse(courses);
    }
}
