package org.advent.day20;

import org.advent.day20.model.Pulse;

import java.util.Collection;

public class Part2Solver extends Day20Solver {

    private boolean found = false;

    @Override
    public void solve(Collection<String> lines) {
        processInput(lines);
        var iterations = 0;
        while (!found) {
            iterations++;
            performIteration();
        }
        result = iterations;
    }

    @Override
    protected void notifyPulseSent(String destination, Pulse pulse) {
        if (pulse == Pulse.LOW && "rx".equals(destination)) {
            found = true;
        }
    }
}
