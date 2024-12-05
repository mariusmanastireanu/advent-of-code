package org.advent._2024.day05;

import java.util.Map;
import java.util.Set;

public class Graph {

    private final Map<Integer, Set<Integer>> graph;
    private final Map<Integer, Integer> inDegree;

    public Graph(Map<Integer, Set<Integer>> graph, Map<Integer, Integer> inDegree) {
        this.graph = graph;
        this.inDegree = inDegree;
    }

    public Map<Integer, Set<Integer>> getGraph() {
        return graph;
    }

    public Map<Integer, Integer> getInDegree() {
        return inDegree;
    }
}
