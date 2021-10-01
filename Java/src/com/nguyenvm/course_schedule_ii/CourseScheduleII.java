package com.nguyenvm.course_schedule_ii;

//https://leetcode.com/problems/course-schedule-ii/

import java.util.*;

public class CourseScheduleII {

    public static int[] findOrder(int numCourses, int[][] prerequisites) {
        if (prerequisites.length == 0) {
            int[] result = new int[numCourses];
            for (int i = 0; i < numCourses; i++) {
                result[i] = i;
            }

            return result;
        }

        List<Integer>[] adjacency = new ArrayList[numCourses];
        int[] indegree = new int[numCourses];
        for (int[] prerequisite: prerequisites) {
            int first = prerequisite[1];
            int second = prerequisite[0];

            List<Integer> edgeFirst = adjacency[first] == null ? adjacency[first] = new ArrayList<>() : adjacency[first];
            edgeFirst.add(second);

            indegree[second]++;
        }

        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) queue.add(i);
        }

        int[] result = new int[numCourses];
        int count = 0;
        while (!queue.isEmpty()) {
            int current = queue.poll();
            result[count] = current;
            count++;
            indegree[current]++;

            List<Integer> edgeC = adjacency[current];
            if (edgeC == null) continue;
            for (int edge: edgeC) {
                indegree[edge]--;
                if (indegree[edge] == 0) queue.add(edge);
            }
        }

        return result.length == numCourses ? result : new int[0];
    }

    public static void main(String[] args) {
        int[][] prerequisites = new int[][]{{1, 0}, {2, 0}, {3, 1}, {3, 2}};
        int numCourses = 4;

        // topological sorting indegree
        findOrder(numCourses, prerequisites);
    }
}
