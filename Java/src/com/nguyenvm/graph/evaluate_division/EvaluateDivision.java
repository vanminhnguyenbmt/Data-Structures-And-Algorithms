package com.nguyenvm.graph.evaluate_division;

import java.util.*;

public class EvaluateDivision {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, Map<String, Double>> adjacency = buildAdjacency(equations, values);

        double[] result = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            result[i] = -1;
            String sourceQuery = queries.get(i).get(0);
            String targetQueue = queries.get(i).get(1);

            if (!adjacency.containsKey(sourceQuery) || !adjacency.containsKey(targetQueue)) {
                result[i] = -1;
                continue;
            }

            if (sourceQuery.equals(targetQueue)) {
                result[i] = 1;
                continue;
            }

            Set<String> visited = new HashSet<>();
            visited.add(sourceQuery);

            Queue<String> queue = new ArrayDeque<>();
            queue.add(sourceQuery);
            Queue<Double> valueQueue = new ArrayDeque<>();
            valueQueue.add(1d);

            while (!queue.isEmpty()) {
                String source = queue.poll();
                double value = valueQueue.poll();

                Map<String, Double> point = adjacency.get(source);
                if (Objects.isNull(point)) {
                    result[i] = -1;
                    break;
                }
                Double targetValue = point.get(targetQueue);
                if (Objects.nonNull(targetValue)) {
                    result[i] = value * targetValue;
                    break;
                }

                Iterator<Map.Entry<String, Double>> adjItem = point.entrySet().iterator();
                while (adjItem.hasNext()) {
                    Map.Entry<String, Double> pointItem = adjItem.next();
                    if (!visited.contains(pointItem.getKey())) {
                        valueQueue.add(value * pointItem.getValue());
                        visited.add(pointItem.getKey());
                        queue.add(pointItem.getKey());
                    }
                }
            }
        }

        return result;
    }

    public Map<String, Map<String, Double>> buildAdjacency(List<List<String>> equations, double[] values) {
        Map<String, Map<String, Double>> adjacency = new HashMap<>(equations.size());
        for (int i = 0; i < equations.size(); i++) {
            adjacency.put(equations.get(i).get(0), new HashMap<>());
            adjacency.put(equations.get(i).get(1), new HashMap<>());
        }

        for (int i = 0; i < equations.size(); i++) {
            String source = equations.get(i).get(0);
            String target = equations.get(i).get(1);

            Map<String, Double> subMap = adjacency.get(source);
            subMap.put(target, values[i]);
            adjacency.put(source, subMap);

            Map<String, Double> reverseSubMap = adjacency.get(target);
            reverseSubMap.put(source, 1 / values[i]);
            adjacency.put(target, reverseSubMap);
        }

        return adjacency;
    }

    public static void main(String[] args) {
        List<List<String>> equations = Arrays.asList(Arrays.asList("x1", "x2"), Arrays.asList("x2", "x3"), Arrays.asList("x3", "x4"), Arrays.asList("x4", "x5"));
        double[] values = new double[]{3.0, 4.0, 5.0, 6.0};
        List<List<String>> queries = Arrays.asList(Arrays.asList("x1", "x5"), Arrays.asList("x5", "x2"), Arrays.asList("x2", "x4"), Arrays.asList("x2", "x2"), Arrays.asList("x2", "x9"), Arrays.asList("x9", "x9"));

        new EvaluateDivision().calcEquation(equations, values, queries);
    }
}
