package org.advent._2024.day06;

import org.advent.helper.AbstractSolver;
import org.advent.helper.Direction;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public abstract class Day06Solver extends AbstractSolver {

    protected char[][] matrix;
    protected int startRow;
    protected int startCol;

    protected final Set<String> visited = new HashSet<>();

    @Override
    public void solve(Collection<String> lines) {
        processInput(lines);
        traverseMatrix(matrix, startRow, startCol, Direction.NORTH);
    }

    private void processInput(final Collection<String> lines) {
        matrix = new char[lines.size()][];
        int row = 0;
        for (String line : lines) {
            if (line.contains("^")) {
                startRow = row;
                startCol = line.indexOf("^");
            }
            matrix[row] = line.toCharArray();
            row++;
        }
    }

    protected void traverseMatrix(char[][] matrix, int row, int col, Direction direction) {
        if (row >= matrix.length || row < 0 || col >= matrix[0].length || col < 0) {
            return;
        }
        if (isLoop(row, col, direction)) {
            return;
        }
        if (matrix[row][col] == '#') {
            var newDir = direction.getRight();
            traverseMatrix(matrix,
                    row - direction.getRowOffset() + newDir.getRowOffset(),
                    col - direction.getColOffset() + newDir.getColOffset(),
                    newDir);
        } else {
            visited.add(getNodeKey(row, col, direction));
            traverseMatrix(matrix, row + direction.getRowOffset(), col + direction.getColOffset(), direction);
        }
    }

    protected abstract boolean isLoop(final int row, final int col, final Direction direction);
    protected abstract String getNodeKey(final int row, final int col, final Direction direction);

}
