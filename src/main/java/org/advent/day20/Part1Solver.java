package org.advent.day20;

import org.advent.day20.model.Pulse;

import java.util.*;

public class Part1Solver extends Day20Solver {

    private static final int MAX_ITERATIONS = 1000;

    @Override
    public void solve(Collection<String> lines) {
        processInput(lines);
        for (int i = 0; i < MAX_ITERATIONS; i++) {
            performIteration();
        }
        result = lowSignals * highSignals;
    }

    @Override
    protected void notifyPulseSent(String destination, Pulse pulse) {
        if (pulse == Pulse.LOW) {
            lowSignals++;
        } else {
            highSignals++;
        }
    }
}
