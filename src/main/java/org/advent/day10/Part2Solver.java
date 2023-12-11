package org.advent.day10;

import org.advent.day10.model.Node;

import java.awt.*;
import java.util.Collection;

public class Part2Solver extends Day10Solver {

    @Override
    public void solve(Collection<String> lines) {
        super.solve(lines);
        var polygon = new Polygon(longestCycle.stream().mapToInt(Node::getRowIndex).toArray(),
                longestCycle.stream().mapToInt(Node::getColIndex).toArray(),
                longestCycle.size());
        result = 0;
        for (int rowIndex = 0; rowIndex < parsedInput.getMatrix().size(); rowIndex++) {
            for (int colIndex = 0; colIndex < parsedInput.getMatrix().get(rowIndex).size(); colIndex++) {
                if (!longestCycle.contains(new Node(rowIndex, colIndex)) && polygon.contains(rowIndex, colIndex)) {
                    result++;
                }
            }
        }
    }
}
