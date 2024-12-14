package org.advent._2024.day14;

import java.util.Collection;

public class Part1Solver extends Day14Solver {

    @Override
    public void solve(final Collection<String> lines) {
        super.solve(lines);
        robots.forEach(this::processRobot);
    }

    protected void processRobot(Robot robot) {
        var newLocation = robot.move(100);
        var quadrant = robot.getQuadrant(newLocation);
        quadrantMap.put(quadrant, quadrantMap.getOrDefault(quadrant, 0) + 1);
    }

    @Override
    public Object getResult() {
        var result = 1L;
        for (int i = 1; i <= 4; i++) {
            result *= quadrantMap.get(i);
        }
        return result;
    }
}
