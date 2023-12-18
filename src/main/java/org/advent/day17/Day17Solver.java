package org.advent.day17;

import org.advent.helper.AbstractSolver;
import org.advent.helper.Direction;

import java.util.*;

public abstract class Day17Solver extends AbstractSolver {

    private int[][] matrix;
    private int result;

    @Override
    public Object getResult() {
        return result;
    }

    @Override
    public void solve(Collection<String> lines) {
        populateMatrix(lines);
        result = findBestPath();
    }

    private void populateMatrix(Collection<String> lines) {
        matrix = new int[lines.size()][lines.iterator().next().length()];
        int row = 0;
        for (String line : lines) {
            int column = 0;
            for (char c : line.toCharArray()) {
                matrix[row][column++] = Integer.parseInt(String.valueOf(c));
            }
            row++;
        }
    }

    public int findBestPath() {
        // Use priority queue to get the lowest cost first
        var queue = new PriorityQueue<State>();
        var visited = new HashSet<State>();
        // add the first two possible directions
        addStartingPoints(queue);
        while (!queue.isEmpty()) {
            var top = queue.poll();
            if (visited.add(top)) {
                if (top.getDirectionCount() >= getMinimumNumberOfSteps()
                        && top.getRow() == matrix.length - 1
                        && top.getColumn() == matrix[top.getRow()].length - 1) {
                    // has reached the end and can stop
                    return top.getCost();
                }
                // has not reached the end yet, add all possible directions
                addNextPoints(top, queue);
            }
        }
        return 0;
    }

    private void addStartingPoints(PriorityQueue<State> queue) {
        queue.add(State.builder()
                .row(0).column(1)
                .direction(Direction.EAST)
                .cost(matrix[0][1])
                .build());
        queue.add(State.builder()
                .row(1).column(0)
                .direction(Direction.SOUTH)
                .cost(matrix[1][0])
                .build());
    }

    private void addNextPoints(State top, PriorityQueue<State> queue) {
        for (Direction direction : getPossibleDirections(top)) {
            if (!isValidDirection(top.getRow(), top.getColumn(), direction)) {
                continue;
            }
            queue.add(State.builder()
                    .row(top.getRow() + direction.getRowOffset())
                    .column(top.getColumn() + direction.getColOffset())
                    .direction(direction)
                    .directionCount(direction == top.getDirection() ? top.getDirectionCount() + 1 : 1)
                    .cost(top.getCost() + matrix[top.getRow() + direction.getRowOffset()][top.getColumn() + direction.getColOffset()])
                    .build()
            );
        }
    }

    public Collection<Direction> getPossibleDirections(State state) {
        var directions = new ArrayList<Direction>();
        if (state.getDirectionCount() < getMaximumNumberOfSteps()) {
            directions.add(state.getDirection());
        }
        if (state.getDirectionCount() >= getMinimumNumberOfSteps()) {
            directions.add(state.getDirection().getLeft());
            directions.add(state.getDirection().getRight());
        }
        return directions;
    }

    protected boolean isValidDirection(int row, int column, Direction direction) {
        return row + direction.getRowOffset() >= 0 && row + direction.getRowOffset() < matrix.length &&
                column + direction.getColOffset() >= 0 && column + direction.getColOffset() < matrix[row].length;
    }

    protected abstract int getMinimumNumberOfSteps();
    protected abstract int getMaximumNumberOfSteps();

}
