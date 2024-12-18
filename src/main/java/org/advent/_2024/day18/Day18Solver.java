package org.advent._2024.day18;

import org.advent.helper.AbstractSolver;
import org.advent.helper.Direction;

import java.util.*;

public abstract class Day18Solver extends AbstractSolver {

    protected List<String> lines;
    protected char[][] matrix;

    private static final int SIZE = 71;

    private static final int START_X = 0;
    private static final int START_Y = 0;

    private static final int END_X = 70;
    private static final int END_Y = 70;

    @Override
    public void solve(Collection<String> lines) {
        this.lines = new ArrayList<>(lines);
    }

    protected void processInputs(final int numberOfInputs) {
        initializeMatrix();
        for (int i = 0; i < numberOfInputs; i++) {
            var tokens = this.lines.get(i).split(",");
            var x = Integer.parseInt(tokens[0]);
            var y = Integer.parseInt(tokens[1]);
            matrix[x][y] = '#';
        }
    }

    protected void initializeMatrix() {
        matrix = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            Arrays.fill(matrix[i], '.');
        }
    }

    protected int findShortestPath() {
        int rows = matrix.length;
        int cols = matrix[0].length;
        boolean[][] visited = new boolean[rows][cols];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{START_X, START_Y, 0});
        visited[START_X][START_Y] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];
            int distance = current[2];

            if (x == END_X && y == END_Y) {
                return distance;
            }

            for (Direction direction : Direction.values()) {
                int newX = x + direction.getRowOffset();
                int newY = y + direction.getColOffset();

                if (isValidMove(newX, newY, visited)) {
                    queue.add(new int[]{newX, newY, distance + 1});
                    visited[newX][newY] = true;
                }
            }
        }

        return -1;
    }

    private boolean isValidMove(int x, int y, boolean[][] visited) {
        return x >= 0 && x < matrix.length && y >= 0 && y < matrix[0].length && matrix[x][y] == '.' && !visited[x][y];
    }

}
