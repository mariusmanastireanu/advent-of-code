package org.advent.day16;

import java.util.Collection;

public class Part2Solver extends Day16Solver {

    @Override
    public void solve(Collection<String> lines) {
        var matrix = new Matrix(lines);
        result = 0;
        for (int i = 0; i < lines.size(); i++) {
            matrix.reset();
            matrix.traverse(i, 0, Direction.RIGHT);
            checkResult(matrix);

            matrix.reset();
            matrix.traverse(i, lines.size() - 1, Direction.LEFT);
            checkResult(matrix);

            matrix.reset();
            matrix.traverse(0, i, Direction.DOWN);
            checkResult(matrix);

            matrix.reset();
            matrix.traverse(lines.size() - 1, i, Direction.UP);
            checkResult(matrix);
        }
    }

    private void checkResult(Matrix matrix) {
        if (matrix.getEnergizedCells() > result) {
            result = matrix.getEnergizedCells();
        }
    }

}
