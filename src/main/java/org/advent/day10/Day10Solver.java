package org.advent.day10;

import org.advent.day10.model.Node;
import org.advent.day10.model.ParsedInput;
import org.advent.helper.AbstractSolver;

import java.util.*;

public class Day10Solver extends AbstractSolver {

    private List<Node> longestCycle = new ArrayList<>();
    private Node startNode;
    private int result = 0;

    @Override
    public Object getResult() {
        return result;
    }

    @Override
    public void solve(Collection<String> lines) {
        var matrix = new ParsedInput(lines);
        this.startNode = new Node(new HashMap<>(), matrix.getMatrix(), matrix.getStartingRowIndex(), matrix.getStartingColIndex());
        findLongestCycle(startNode, new LinkedList<>());
        result = longestCycle.size() / 2;
    }

    private void findLongestCycle(Node current, List<Node> visited) {
        visited.add(current);
        for (Node next : current.getNeighbors()) {
            if (visited.contains(next)) {
                if (next == startNode && visited.size() > longestCycle.size()) {
                    longestCycle = new ArrayList<>(visited);
                }
            } else {
                findLongestCycle(next, visited);
            }
        }
        visited.remove(visited.size() - 1);
    }

}
