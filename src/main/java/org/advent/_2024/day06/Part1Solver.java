package org.advent._2024.day06;

import org.advent.helper.Direction;

public class Part1Solver extends Day06Solver {

    @Override
    protected boolean isLoop(final int row, final int col, final Direction direction) {
        return false;
    }

    @Override
    protected String getNodeKey(final int row, final int col, final Direction direction) {
        return row + "," + col;
    }

    @Override
    public Object getResult() {
        return visited.size();
    }

}
