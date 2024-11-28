package org.advent._2023.day14;

import org.advent._2023.day14.model.Platform;
import org.advent.helper.AbstractSolver;

import java.util.Collection;

public abstract class Day14Solver extends AbstractSolver {

    private long result = 0L;
    @Override
    public Object getResult() {
        return result;
    }

    @Override
    public void solve(Collection<String> lines) {
        result = createPlatform(lines).tiltAndComputeWeight();
    }

    protected abstract Platform createPlatform(Collection<String> lines);
}
