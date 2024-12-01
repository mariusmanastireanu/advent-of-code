package org.advent._2024.day01;

import org.advent.helper.AbstractSolver;

import java.util.*;

public abstract class Day01Solver extends AbstractSolver {

    protected final List<Integer> sourceList = new ArrayList<>();

    @Override
    public void solve(Collection<String> lines) {
        lines.forEach(line -> {
            sourceList.add(Integer.parseInt(line.split("   ")[0]));
            processDestinationList(Integer.parseInt(line.split("   ")[1]));
        });
        computeResult();
    }

    abstract void processDestinationList(final int i);
    abstract void computeResult();

}
