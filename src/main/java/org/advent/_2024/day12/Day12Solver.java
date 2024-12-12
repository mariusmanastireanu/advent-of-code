package org.advent._2024.day12;

import org.advent.helper.AbstractSolver;
import org.advent.helper.Direction;
import org.advent.helper.MatrixHelper;
import org.advent.helper.Point;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class Day12Solver extends AbstractSolver {

    protected char[][] matrix;
    protected long result = 0L;

    protected Set<Point> visited = new HashSet<>();

    @Override
    public void solve(Collection<String> lines) {
        matrix = MatrixHelper.readMatrix(lines);

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                processLocation(i, j);
            }
        }
    }

    private void processLocation(int row, int col) {
        if (visited.contains(new Point(row, col))) {
            return;
        }
        var area = new AtomicInteger(0);
        var value = new AtomicInteger(0);
        processLocation(matrix[row][col], row, col, area, value);
        result += area.get() * value.get();
    }

    private void processLocation(char initial, int row, int col, AtomicInteger area, AtomicInteger value) {
        if (visited.contains(new Point(row, col))) {
            return;
        }
        if (row < 0 || row >= matrix.length || col < 0 || col >= matrix[0].length || matrix[row][col] != initial) {
            return;
        }
        value.addAndGet(computeValueForLocation(row, col));
        visited.add(new Point(row, col));
        area.incrementAndGet();
        for (Direction d : Direction.values()) {
            processLocation(initial, row + d.getRowOffset(), col + d.getColOffset(), area, value);
        }
    }

    protected abstract int computeValueForLocation(int row, int col);

    @Override
    public Object getResult() {
        return result;
    }

}
