package org.advent._2024.day06;

import org.advent.helper.Direction;

import java.util.Collection;
import java.util.LinkedHashSet;

public class Part2Solver extends Day06Solver {

    private int loops = 0;
    private boolean initializing = true;

    @Override
    public void solve(final Collection<String> lines) {
        super.solve(lines);
        initializing = false;
        visited.remove(startRow + "," + startCol);
        for (String node : new LinkedHashSet<>(visited)) {
            visited.clear();
            traverseMatrix(copyAndReplaceNode(node), startRow, startCol, Direction.NORTH);
        }
    }

    private char[][] copyAndReplaceNode(final String node) {
        char[][] matrixCopy = copyMatrix(matrix);
        var split = node.split(",");
        matrixCopy[Integer.parseInt(split[0])][Integer.parseInt(split[1])] = '#';
        return matrixCopy;
    }

    public char[][] copyMatrix(char[][] original) {
        var copy = new char[original.length][];
        for (int i = 0; i < original.length; i++) {
            copy[i] = original[i].clone();
        }
        return copy;
    }

    @Override
    protected boolean isLoop(final int row, final int col, final Direction direction) {
        if (initializing) {
            return false;
        }
        if (visited.contains(getNodeKey(row, col, direction))) {
            loops++;
            return true;
        }
        return false;
    }

    @Override
    protected String getNodeKey(final int row, final int col, final Direction direction) {
        if (initializing) {
            return row + "," + col;
        } else {
            return row + "," + col + "," + direction;
        }
    }

    @Override
    public Object getResult() {
        return loops;
    }

}
