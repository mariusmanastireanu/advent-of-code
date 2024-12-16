package org.advent._2024.day15;

import org.advent.helper.AbstractSolver;
import org.advent.helper.Direction;
import org.advent.helper.MatrixHelper;
import org.advent.helper.Point;

import java.util.Collection;
import java.util.List;

public abstract class Day15Solver extends AbstractSolver {

    protected char[][] matrix;
    protected Point location;

    @Override
    public void solve(Collection<String> lines) {
        matrix = MatrixHelper.readMatrix(lines.stream().filter(l -> l.startsWith("#")).toList());
        findInitialLocation();
        processInstructions(lines.stream().filter(l -> !l.startsWith("#") && !l.isBlank()).toList());
    }

    private void findInitialLocation() {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == '@') {
                    location = new Point(i, j);
                }
            }
        }
    }

    private void processInstructions(final List<String> list) {
        for (String line : list) {
            for (Character c : line.toCharArray()) {
                move(Direction.getDirection(c));
            }
        }
    }

    private void move(final Direction direction) {
        Point newLocation = getNextPoint(location, direction);

        if (isOutOfBounds(newLocation) || matrix[(int) newLocation.getX()][(int) newLocation.getY()] == '#') {
            return; // Out of bounds or hit a wall, stop
        }

        if (matrix[(int) newLocation.getX()][(int) newLocation.getY()] == '.') {
            moveToEmptyCell(newLocation);
        } else if (matrix[(int) newLocation.getX()][(int) newLocation.getY()] == 'O') {
            try {
                if (canMove(newLocation, direction) > 0) {
                    moveO(newLocation, direction);
                    moveToEmptyCell(newLocation);
                }
            } catch (IllegalArgumentException e) {
                // hit a wall, ignore
            }
        }
    }

    private void moveO(Point point, Direction direction) {
        Point newPoint = getNextPoint(point, direction);

        if (isOutOfBounds(newPoint) || matrix[(int) newPoint.getX()][(int) newPoint.getY()] == '#') {
            return; // Out of bounds or hit a wall, stop
        }

        if (matrix[(int) newPoint.getX()][(int) newPoint.getY()] == '.') {
            moveToEmptyCell(newPoint, point);
        } else if (matrix[(int) newPoint.getX()][(int) newPoint.getY()] == 'O') {
            try {
                if (canMove(point, direction) > 0) {
                    moveO(newPoint, direction);
                    moveToEmptyCell(newPoint, point);
                }
            } catch (IllegalArgumentException e) {
                // hit a wall, ignore
            }
        }
    }

    private void moveToEmptyCell(Point newLocation) {
        matrix[(int) newLocation.getX()][(int) newLocation.getY()] = '@';
        matrix[(int) location.getX()][(int) location.getY()] = '.';
        location = newLocation;
    }

    private void moveToEmptyCell(Point newLocation, Point oldLocation) {
        matrix[(int) newLocation.getX()][(int) newLocation.getY()] = 'O';
        matrix[(int) oldLocation.getX()][(int) oldLocation.getY()] = '.';
    }

    private Point getNextPoint(Point point, Direction direction) {
        return new Point(point.getX() + direction.getRowOffset(), point.getY() + direction.getColOffset());
    }

    private boolean isOutOfBounds(Point point) {
        return point.getX() < 0 || point.getX() >= matrix.length || point.getY() < 0 || point.getY() >= matrix[0].length;
    }

    private int canMove(final Point point, final Direction direction) {
        Point newPoint = getNextPoint(point, direction);

        if (isOutOfBounds(newPoint) || matrix[(int) newPoint.getX()][(int) newPoint.getY()] == '#') {
            throw new IllegalArgumentException("Hit a wall");
        }

        if (matrix[(int) newPoint.getX()][(int) newPoint.getY()] == '.') {
            return 1; // Can move to an empty cell
        }

        if (matrix[(int) newPoint.getX()][(int) newPoint.getY()] == 'O') {
            return 1 + canMove(newPoint, direction); // Can move if the next cell can move
        }

        return 0; // Default case
    }

    @Override
    public Object getResult() {
        var sum = 0L;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 'O') {
                    sum += 100 * i + j;
                }
            }
        }
        return sum;
    }
}
