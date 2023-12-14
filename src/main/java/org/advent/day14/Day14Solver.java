package org.advent.day14;

import org.advent.day14.model.Platform;
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
