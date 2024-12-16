package org.advent._2024.day16;

import org.advent.helper.AbstractSolver;
import org.advent.helper.Direction;
import org.advent.helper.MatrixHelper;
import org.advent.helper.Point;

import java.util.*;

public abstract class Day16Solver extends AbstractSolver {

    protected char[][] matrix;
    protected Point startingLocation;
    protected Point finishLocation;

    @Override
    public void solve(Collection<String> lines) {
        matrix = MatrixHelper.readMatrix(lines);
        findLocations();
    }

    private void findLocations() {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 'S') {
                    startingLocation = new Point(i, j);
                } else if (matrix[i][j] == 'E') {
                    finishLocation = new Point(i, j);
                }
            }
        }
    }

    protected abstract int findPath();

    protected Point getNextPoint(Point point, Direction direction) {
        return new Point(point.getX() + direction.getRowOffset(), point.getY() + direction.getColOffset());
    }

    protected boolean isValid(Point point) {
        return point.getX() >= 0 && point.getX() < matrix.length && point.getY() >= 0 && point.getY() < matrix[0].length && matrix[(int) point.getX()][(int) point.getY()] != '#';
    }

    @Override
    public Object getResult() {
        return findPath();
    }
}
