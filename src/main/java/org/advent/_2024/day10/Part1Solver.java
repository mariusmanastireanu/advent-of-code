package org.advent._2024.day10;

import org.advent.helper.Direction;
import org.advent.helper.Point;

import java.util.Arrays;
import java.util.Stack;

public class Part1Solver extends Day10Solver {

    @Override
    protected int countPaths(final int[][] map, final int row, final int col, final boolean[][] visited) {
        // dfs
        var stack = new Stack<Point>();
        stack.push(new Point(row, col));
        visited[row][col] = true;
        var count = 0;

        while (!stack.isEmpty()) {
            var current = stack.pop();
            int currentValue = map[(int) current.getX()][(int) current.getY()];

            if (currentValue == 9) {
                count++;
            }

            for (var dir : Arrays.asList(Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST)) {
                var newRow = (int) current.getX() + dir.getRowOffset();
                var newCol = (int) current.getY() + dir.getColOffset();

                if (isValidCell(map, newRow, newCol, currentValue, visited)) {
                    stack.push(new Point(newRow, newCol));
                    visited[newRow][newCol] = true;
                }
            }
        }

        return count;
    }
}
