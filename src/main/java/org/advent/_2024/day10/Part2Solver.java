package org.advent._2024.day10;

import org.advent.helper.Direction;

import java.util.Arrays;

public class Part2Solver extends Day10Solver {

    @Override
    public int countPaths(final int[][] map, final int row, final int col, final boolean[][] visited) {
        if (map[row][col] == 9) {
            return 1;
        }

        visited[row][col] = true;
        var count = 0;

        for (var dir : Arrays.asList(Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST)) {
            var newRow = row + dir.getRowOffset();
            var newCol = col + dir.getColOffset();

            if (isValidCell(map, newRow, newCol, map[row][col], visited)) {
                count += countPaths(map, newRow, newCol, visited);
            }
        }

        visited[row][col] = false;
        return count;
    }

}
