package org.advent._2024.day08;

import org.advent.helper.Point;

public class Part2Solver extends Day08Solver {

    @Override
    protected void processNodes(final Point startPoint, final Point endPoint) {
        super.processNodes(startPoint, endPoint);
        antinodes.add(startPoint);
        antinodes.add(endPoint);
    }

    @Override
    protected int getNumberOfIterations() {
        return Integer.MAX_VALUE;
    }
}
