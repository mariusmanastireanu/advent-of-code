package org.advent.day16;

import java.util.Collection;

public class Part1Solver extends Day16Solver {

    @Override
    public void solve(Collection<String> lines) {
        var matrix = new Matrix(lines);
        matrix.traverse(0, 0, Direction.RIGHT);
        result = matrix.getEnergizedCells();
    }
}
