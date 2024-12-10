package org.advent._2024.day10;

import org.advent.helper.AbstractSolver;

import java.util.*;

public abstract class Day10Solver extends AbstractSolver {

    protected int[][] map;
    protected final List<Integer> system = new ArrayList<>();
    private int result = 0;

    @Override
    public void solve(Collection<String> lines) {
        processInput(lines);
        countPaths();
    }

    private void processInput(final Collection<String> lines) {
        map = new int[lines.size()][];
        var row = 0;
        for (String line : lines) {
            map[row++] = new int[line.length()];
            var col = 0;
            for (Character c : line.toCharArray()) {
                map[row - 1][col++] = Integer.parseInt("" + c);
            }
        }
    }

    private void countPaths() {
        int rows = map.length;
        int cols = map[0].length;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (map[row][col] == 0) {
                    boolean[][] visited = new boolean[rows][cols];
                    result += countPaths(map, row, col, visited);
                }
            }
        }
    }

    protected abstract int countPaths(int[][] map, int row, int col, boolean[][] visited);

    protected boolean isValidCell(int[][] map, int row, int col, int currentValue, boolean[][] visited) {
        if (row < 0 || row >= map.length || col < 0 || col >= map[0].length) {
            return false;
        }
        if (visited[row][col]) {
            return false;
        }
        return map[row][col] - currentValue == 1;
    }


    @Override
    public Object getResult() {
        return result;
    }
}
