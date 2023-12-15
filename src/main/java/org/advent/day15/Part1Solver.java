package org.advent.day15;

import org.advent.helper.AbstractSolver;

import java.util.Arrays;
import java.util.Collection;

public class Part1Solver extends AbstractSolver {

    protected int result = 0;
    @Override
    public Object getResult() {
        return result;
    }

    @Override
    public void solve(Collection<String> lines) {
        result = Arrays.stream(lines.iterator().next().split(","))
                .map(AsciiInstruction::new)
                .map(AsciiInstruction::hashCode)
                .mapToInt(Integer::intValue)
                .sum();
    }

}
