package org.advent._2023.day18;

import org.advent.helper.Direction;

public class Part1Solver extends Day18Solver {

    @Override
    protected Direction getDirection(String line) {
        return Direction.getDirection(line.charAt(0));
    }

    @Override
    protected int getSteps(String line) {
        return Integer.parseInt(line.split(" ")[1]);
    }

}
