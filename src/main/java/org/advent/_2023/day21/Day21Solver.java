package org.advent._2023.day21;

import org.advent.helper.AbstractSolver;
import org.advent.helper.Direction;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Day21Solver extends AbstractSolver {


    private static final int NUMBER_OF_STEPS = 64;


    protected long result = 0L;

    protected char[][] matrix;
    protected final Set<String> visited = new HashSet<>();

    protected int sX;
    protected int sY;

    @Override
    public Object getResult() {
        return result;
    }

    @Override
    public void solve(Collection<String> lines) {
        parseInput(lines);
        result = solvePosition(sX, sY, 0);
    }

    protected void parseInput(Collection<String> lines) {
        matrix = new char[lines.size()][lines.iterator().next().length()];
        int row = 0;
        for (String line : lines) {
            int column = 0;
            for (char c : line.toCharArray()) {
                matrix[row][column++] = c;
                if ('S' == c) {
                    sX = row;
                    sY = column - 1;
                }
            }
            row++;
        }
    }

    protected int solvePosition(int row, int column, int steps) {
        if (!canVisit(row, column, steps)) {
            return 0;
        }
        visited.add((row) + "," + (column) + "," + steps);
        if (steps == NUMBER_OF_STEPS) {
            return 1;
        }
        var sum = 0;
        for (Direction direction : Direction.values()) {
            sum += solvePosition(row + direction.getRowOffset(), column + direction.getColOffset(), steps + 1);
        }
        return sum;
    }

    protected boolean canVisit(int row, int column, int steps) {
        return row >= 0 && row < matrix.length
                && column >= 0 && column < matrix.length
                && matrix[row][column] != '#'
                && !visited.contains(row + "," + column + "," + steps)
                && steps <= NUMBER_OF_STEPS;
    }

}
