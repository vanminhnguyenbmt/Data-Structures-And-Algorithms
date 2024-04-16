package com.nguyenvm.tree.operations_on_tree;

import java.util.*;
import java.util.function.BiPredicate;

public class LockingTree {
    int[] parent;
    int[] userLocked;
    List<Integer>[] children;

    public LockingTree(int[] parent) {
        this.parent = parent;
        userLocked = new int[parent.length];
        children = new ArrayList[parent.length];
        for (int i = 0; i < parent.length; i++) {
            children[i] = new ArrayList<>();
        }

        for (int i = 1; i < parent.length; i++) {
            children[parent[i]].add(i);
        }
    }

    public boolean lock(int num, int user) {
        if (userLocked[num] != 0)
            return false;
        userLocked[num] = user;
        return true;
    }

    public boolean unlock(int num, int user) {
        if (userLocked[num] == user) {
            userLocked[num] = 0;
            return true;
        }

        return false;
    }

    public boolean upgrade(int num, int user) {
        if (userLocked[num] != 0)
            return false;
        if (hasAncestorLocked(num))
            return false;
        if (!hasDescendantLocked(num))
            return false;

        unLockDescendants(num);
        userLocked[num] = user;
        return true;
    }

    public boolean hasDescendantLocked(int num) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(num);

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            if (userLocked[cur] != 0)
                return true;

            for (int child : children[cur]) {
                queue.offer(child);
            }
        }

        return false;
    }

    public boolean hasAncestorLocked(int num) {
        int ancestor = this.parent[num];
        while (ancestor != -1) {
            if (userLocked[ancestor] != 0)
                return true;
            ancestor = this.parent[ancestor];
        }

        return false;
    }

    public void unLockDescendants(int num) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(num);

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            userLocked[cur] = 0;

            for (int child : children[cur]) {
                queue.offer(child);
            }
        }
    }

    public static void main(String[] args) {
        List<String> actions = Arrays.asList("LockingTree", "lock", "unlock", "unlock", "lock", "upgrade", "lock");
        int[][] data = new int[][]{{-1, 0, 0, 1, 1, 2, 2}, {2, 2}, {2, 3}, {2, 2}, {4, 5}, {0, 1}, {0, 1}};

        LockingTree lockingTree = new LockingTree(data[0]);
        Map<String, BiPredicate<Integer, Integer>> handler = new HashMap<>();
        handler.put("lock", (num, user) -> lockingTree.lock(num, user));
        handler.put("unlock", (num, user) -> lockingTree.unlock(num, user));
        handler.put("upgrade", (num, user) -> lockingTree.upgrade(num, user));

        List<Boolean> results = new ArrayList<>();
        results.add(null);
        for (int i = 1; i < actions.size(); i++) {
            results.add(handler.get(actions.get(i)).test(data[i][0], data[i][1]));
        }

        System.out.println(results);
    }
}
