package org.advent._2024.day14;

import org.advent.helper.Point;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Part2Solver extends Day14Solver {

    private int result = 0;

    @Override
    public void solve(final Collection<String> lines) {
        super.solve(lines);
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            var points = new HashSet<>();
            for (Robot robot : robots) {
                var location = robot.move(i);
                if (points.contains(location)) {
                    break;
                }
                points.add(location);
            }
            if (points.size() == robots.size()) {
                result = i;
                break;
            }
        }
    }

    @Override
    public Object getResult() {
        return result;
    }

}
