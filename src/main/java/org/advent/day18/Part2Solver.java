package org.advent.day18;

import org.advent.helper.Direction;

public class Part2Solver extends Day18Solver {

    @Override
    protected Direction getDirection(String line) {
        var character = line.split("#")[1].charAt(5);
        if (character == '0') {
            return Direction.EAST;
        } else if (character == '1') {
            return Direction.SOUTH;
        } else if (character == '2') {
            return Direction.WEST;
        } else if (character == '3') {
            return Direction.NORTH;
        }
        return null;
    }

    @Override
    protected int getSteps(String line) {
        return Integer.parseInt(line.split("#")[1].substring(0, 5), 16);
    }

}
