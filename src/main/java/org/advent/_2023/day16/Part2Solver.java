package org.advent._2023.day16;

import org.advent.helper.Direction;

import java.util.Collection;

public class Part2Solver extends Day16Solver {

    @Override
    public void solve(Collection<String> lines) {
        var matrix = new Matrix(lines);
        result = 0;
        for (int i = 0; i < lines.size(); i++) {
            matrix.reset();
            matrix.traverse(i, 0, Direction.EAST);
            checkResult(matrix);

            matrix.reset();
            matrix.traverse(i, lines.size() - 1, Direction.WEST);
            checkResult(matrix);

            matrix.reset();
            matrix.traverse(0, i, Direction.SOUTH);
            checkResult(matrix);

            matrix.reset();
            matrix.traverse(lines.size() - 1, i, Direction.NORTH);
            checkResult(matrix);
        }
    }

    private void checkResult(Matrix matrix) {
        if (matrix.getEnergizedCells() > result) {
            result = matrix.getEnergizedCells();
        }
    }

}
