package org.advent._2023.day16;

import org.advent.helper.Direction;

import java.util.Collection;

public class Part1Solver extends Day16Solver {

    @Override
    public void solve(Collection<String> lines) {
        var matrix = new Matrix(lines);
        matrix.traverse(0, 0, Direction.EAST);
        result = matrix.getEnergizedCells();
    }
}
