package org.advent._2024.day14;

import org.advent.helper.AbstractSolver;
import org.advent.helper.Point;

import java.util.*;

public abstract class Day14Solver extends AbstractSolver {

    protected final List<Robot> robots = new ArrayList<>();
    protected final Map<Integer, Integer> quadrantMap = new HashMap<>();

    public static final int HEIGHT = 103;
    public static final int WIDTH = 101;

    @Override
    public void solve(Collection<String> lines) {
        for (String line : lines) {
            robots.add(Robot.builder()
                            .location(new Point(
                                    Integer.parseInt(line.substring(2, line.indexOf(" ")).split(",")[0]),
                                    Integer.parseInt(line.substring(2, line.indexOf(" ")).split(",")[1])
                            ))
                            .velocity(new Point(
                                    Integer.parseInt(line.substring(line.indexOf("v=") + 2).split(",")[0]),
                                    Integer.parseInt(line.substring(line.indexOf("v=") + 2).split(",")[1])
                            ))
                    .build());
        }
    }

}
