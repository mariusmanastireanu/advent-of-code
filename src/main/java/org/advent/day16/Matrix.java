package org.advent.day16;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Matrix {

    private final char[][] matrix;
    private final Map<String, Integer> map = new HashMap<>();
    private final Map<String, Direction> cycleMap = new HashMap<>();

    public Matrix(Collection<String> lines) {
        matrix = new char[lines.size()][lines.iterator().next().length()];
        int row = 0;
        for (String line : lines) {
            matrix[row] = line.toCharArray();
            row++;
        }
    }

    public void reset() {
        map.clear();
        cycleMap.clear();
    }

    public int traverse(int row, int col, Direction direction) {
        if (direction.equals(cycleMap.getOrDefault(row + "," + col, null))) {
            return 0;
        }
        cycleMap.put(row + "," + col, direction);
        if (row < 0 || row >= matrix.length || col < 0 || col >= matrix[0].length) {
            return 0;
        }
        count(row, col);
        if (matrix[row][col] == '.') {
            switch (direction) {
                case RIGHT:
                    return traverse(row, col + 1, Direction.RIGHT);
                case LEFT:
                    return traverse(row, col - 1, Direction.LEFT);
                case UP:
                    return traverse(row - 1, col, Direction.UP);
                case DOWN:
                    return traverse(row + 1, col, Direction.DOWN);
            }
        } else if (matrix[row][col] == '/') {
            switch (direction) {
                case RIGHT:
                    return traverse(row - 1, col, Direction.UP);
                case LEFT:
                    return traverse(row + 1, col, Direction.DOWN);
                case UP:
                    return traverse(row, col + 1, Direction.RIGHT);
                case DOWN:
                    return traverse(row, col - 1, Direction.LEFT);
            }
        } else if (matrix[row][col] == '\\') {
            switch (direction) {
                case RIGHT:
                    return traverse(row + 1, col, Direction.DOWN);
                case LEFT:
                    return traverse(row - 1, col, Direction.UP);
                case UP:
                    return traverse(row, col - 1, Direction.LEFT);
                case DOWN:
                    return traverse(row, col + 1, Direction.RIGHT);
            }
        } else if (matrix[row][col] == '|') {
            if (direction == Direction.UP || direction == Direction.DOWN) {
                switch (direction) {
                    case UP:
                        return traverse(row - 1, col, Direction.UP);
                    case DOWN:
                        return traverse(row + 1, col, Direction.DOWN);
                }
            } else {
                return traverse(row - 1, col, Direction.UP) + traverse(row + 1, col, Direction.DOWN);
            }
        } else if (matrix[row][col] == '-') {
            if (direction == Direction.LEFT || direction == Direction.RIGHT) {
                switch (direction) {
                    case LEFT:
                        return traverse(row, col - 1, Direction.LEFT);
                    case RIGHT:
                        return traverse(row, col + 1, Direction.RIGHT);
                }
            } else {
                return traverse(row, col - 1, Direction.LEFT) + traverse(row, col + 1, Direction.RIGHT);
            }
        }
        return 0;
    }

    private void count(int row, int col) {
        var key = row + "," + col;
        map.put(key, map.getOrDefault(key, 0) + 1);
    }

    public int getEnergizedCells() {
        return map.size();
    }

}
