package org.advent._2023.day10;

import org.advent._2023.day10.model.Node;
import org.advent._2023.day10.model.ParsedInput;
import org.advent.helper.AbstractSolver;

import java.util.*;

public class Day10Solver extends AbstractSolver {

    protected List<Node> longestCycle = new ArrayList<>();
    protected ParsedInput parsedInput;
    private Node startNode;
    protected int result = 0;

    @Override
    public Object getResult() {
        return result;
    }

    @Override
    public void solve(Collection<String> lines) {
        parsedInput = new ParsedInput(lines);
        this.startNode = new Node(new HashMap<>(), parsedInput.getMatrix(), parsedInput.getStartingRowIndex(), parsedInput.getStartingColIndex());
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
