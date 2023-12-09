package org.advent.day9;

import java.util.List;

public class Part2Solver extends Day9Solver {

    @Override
    protected long solveSequence(List<List<Long>> sequences) {
        sequences.get(sequences.size() - 1).add(0, 0L);
        for (int i = sequences.size() - 2; i >= 0; i--) {
            var currentSequence = sequences.get(i);
            var previousSequence = sequences.get(i + 1);
            currentSequence.add(0, currentSequence.get(0) - previousSequence.get(0));
        }
        return sequences.get(0).get(0);
    }

}
