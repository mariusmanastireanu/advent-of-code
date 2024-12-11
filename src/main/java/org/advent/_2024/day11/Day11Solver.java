package org.advent._2024.day11;

import org.advent.helper.AbstractSolver;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public abstract class Day11Solver extends AbstractSolver {

    protected long result = 0L;
    @Override
    public void solve(Collection<String> lines) {
        processStones(Arrays.stream(lines.iterator().next().split(" ")).map(Long::parseLong).toList());
    }

    protected abstract void processStones(List<Long> stones);

    @Override
    public Object getResult() {
        return result;
    }

}
