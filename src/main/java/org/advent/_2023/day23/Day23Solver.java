package org.advent._2023.day23;

import org.advent.helper.AbstractSolver;
import org.advent.helper.Direction;
import org.advent.helper.Point;

import java.util.*;

public class Day23Solver extends AbstractSolver {
    private char[][] matrix;
    private long result = 0L;

    private final Point start = new Point(0, 1);
    private final Point exit = new Point(140, 139);

    @Override
    public Object getResult() {
        return result;
    }

    @Override
    public void solve(Collection<String> lines) {
        readMatrix(lines);
        result = getLongestPath(start, new LinkedHashSet<>());
    }

    private void readMatrix(Collection<String> lines) {
        matrix = new char[lines.size()][lines.iterator().next().length()];
        int row = 0;
        for (String line : lines) {
            int column = 0;
            for (char c : line.toCharArray()) {
                matrix[row][column++] = c;
            }
            row++;
        }
    }

    private int getLongestPath(Point current, Set<Point> visited) {
        if (current.equals(exit)) {
            return visited.size();
        }
        var addedSinceLastInflection = new LinkedHashSet<Point>();
        addedSinceLastInflection.add(current);
        visited.add(current);
        var nextPositions = getNextPositions(current, visited);
        while (nextPositions.size() == 1) {
            Point next = nextPositions.get(0);
            if (next.equals(exit)) {
                int length = visited.size();
                visited.removeAll(addedSinceLastInflection);
                return length;
            }
            addedSinceLastInflection.add(next);
            visited.add(next);
            nextPositions = getNextPositions(next, visited);
        }
        int maxLength = 0;
        for (Point next : nextPositions) {
            int length = getLongestPath(next, visited);
            if (length > maxLength) {
                maxLength = length;
            }
        }
        return maxLength;
    }

    private List<Point> getNextPositions(Point current, Set<Point> visited) {
        return getPossibleDirections(current)
                .stream()
                .map(dir -> new Point(current.getX() + dir.getRowOffset(), current.getY() + dir.getColOffset()))
                .filter(point -> canVisit(point, visited))
                .toList();
    }

    protected List<Direction> getPossibleDirections(Point point) {
        var row = (int) point.getX();
        var col = (int) point.getY();
        if ('.' == matrix[row][col]) {
            return Arrays.asList(Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST);
        } else if ('>' == matrix[row][col]) {
            return Collections.singletonList(Direction.EAST);
        } else if ('<' == matrix[row][col]) {
            return Collections.singletonList(Direction.WEST);
        } else if ('^' == matrix[row][col]) {
            return Collections.singletonList(Direction.NORTH);
        } else if ('v' == matrix[row][col]) {
            return Collections.singletonList(Direction.SOUTH);
        }
        return Collections.emptyList();
    }

    protected boolean canVisit(Point point, Set<Point> visited) {
        var row = (int) point.getX();
        var col = (int) point.getY();
        return row >= 0 && row < matrix.length
                && col >= 0 && col < matrix[0].length
                && matrix[row][col] != '#'
                && !visited.contains(point);
    }

}
