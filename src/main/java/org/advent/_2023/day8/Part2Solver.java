package org.advent._2023.day8;

import org.advent.helper.MathUtil;

import java.util.Collection;

public class Part2Solver extends Day8Solver {

    @Override
    public void solve(Collection<String> lines) {
        processInput(lines);
        result = nodes.keySet().stream()
                .filter(key -> key.endsWith("A"))
                .map(this::solveForNode)
                .reduce(MathUtil::lcm)
                .orElse(0L);
    }

}
