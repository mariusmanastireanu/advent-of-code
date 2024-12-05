package org.advent._2024.day05;

import java.util.*;

public class GraphUtils {

    public static List<Integer> topologicalSort(List<Integer> sequence, List<int[]> rules) {
        var graphData = createGraph(rules, sequence);
        var graph = graphData.getGraph();
        var inDegree = graphData.getInDegree();
        var result = new ArrayList<Integer>();
        var queue = new LinkedList<Integer>();

        for (Map.Entry<Integer, Integer> entry : inDegree.entrySet()) {
            if (entry.getValue() == 0) {
                queue.add(entry.getKey());
            }
        }

        while (!queue.isEmpty()) {
            var current = queue.poll();
            result.add(current);

            for (Integer next : graph.get(current)) {
                inDegree.put(next, inDegree.get(next) - 1);
                if (inDegree.get(next) == 0) {
                    queue.add(next);
                }
            }
        }
        return result;
    }

    private static Graph createGraph(List<int[]> rules, List<Integer> numbers) {
        var graph = new HashMap<Integer, Set<Integer>>();
        var inDegree = new HashMap<Integer, Integer>();
        var pageSet = new HashSet<>(numbers);

        for (Integer page : numbers) {
            graph.put(page, new HashSet<>());
            inDegree.put(page, 0);
        }

        for (int[] rule : rules) {
            int before = rule[0];
            int after = rule[1];
            if (pageSet.contains(before) && pageSet.contains(after)) {
                graph.get(before).add(after);
                inDegree.put(after, inDegree.get(after) + 1);
            }
        }
        return new Graph(graph, inDegree);
    }
}
