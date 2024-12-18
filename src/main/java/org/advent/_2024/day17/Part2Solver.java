package org.advent._2024.day17;

import java.util.List;

public class Part2Solver extends Day17Solver {

    private long part2Result = 0;

    @Override
    protected void solve(List<Integer> instructions) {
        for (long i = Integer.MAX_VALUE ; i < Long.MAX_VALUE; i++) {
            a = i;
            result.setLength(0);
            super.solve(instructions);
            if (super.getResult().equals(originalProgram)) {
                part2Result = i;
                break;
            }
        }
    }

    @Override
    protected boolean shouldStop() {
        if (result.isEmpty()) {
            return false;
        }
        return result.length() > originalProgram.length() || !originalProgram.startsWith(result.toString());
    }

    @Override
    public Object getResult() {
        return part2Result;
    }
}
