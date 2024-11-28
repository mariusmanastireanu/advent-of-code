package org.advent._2023.day16;

import org.advent.helper.Direction;

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
                case EAST:
                    return traverse(row, col + 1, Direction.EAST);
                case WEST:
                    return traverse(row, col - 1, Direction.WEST);
                case NORTH:
                    return traverse(row - 1, col, Direction.NORTH);
                case SOUTH:
                    return traverse(row + 1, col, Direction.SOUTH);
            }
        } else if (matrix[row][col] == '/') {
            switch (direction) {
                case EAST:
                    return traverse(row - 1, col, Direction.NORTH);
                case WEST:
                    return traverse(row + 1, col, Direction.SOUTH);
                case NORTH:
                    return traverse(row, col + 1, Direction.EAST);
                case SOUTH:
                    return traverse(row, col - 1, Direction.WEST);
            }
        } else if (matrix[row][col] == '\\') {
            switch (direction) {
                case EAST:
                    return traverse(row + 1, col, Direction.SOUTH);
                case WEST:
                    return traverse(row - 1, col, Direction.NORTH);
                case NORTH:
                    return traverse(row, col - 1, Direction.WEST);
                case SOUTH:
                    return traverse(row, col + 1, Direction.EAST);
            }
        } else if (matrix[row][col] == '|') {
            if (direction == Direction.NORTH || direction == Direction.SOUTH) {
                switch (direction) {
                    case NORTH:
                        return traverse(row - 1, col, Direction.NORTH);
                    case SOUTH:
                        return traverse(row + 1, col, Direction.SOUTH);
                }
            } else {
                return traverse(row - 1, col, Direction.NORTH) + traverse(row + 1, col, Direction.SOUTH);
            }
        } else if (matrix[row][col] == '-') {
            if (direction == Direction.WEST || direction == Direction.EAST) {
                switch (direction) {
                    case WEST:
                        return traverse(row, col - 1, Direction.WEST);
                    case EAST:
                        return traverse(row, col + 1, Direction.EAST);
                }
            } else {
                return traverse(row, col - 1, Direction.WEST) + traverse(row, col + 1, Direction.EAST);
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
