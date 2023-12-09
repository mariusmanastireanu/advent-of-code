package org.advent.day9;

import java.util.List;

public class Part1Solver extends Day9Solver {

    @Override
    protected long solveSequence(List<List<Long>> sequences) {
        sequences.get(sequences.size() - 1).add(0L);
        for (int i = sequences.size() - 2; i >= 0; i--) {
            var currentSequence = sequences.get(i);
            var previousSequence = sequences.get(i + 1);
            currentSequence.add(currentSequence.get(currentSequence.size() - 1) + previousSequence.get(previousSequence.size() - 1));
        }
        return sequences.get(0).get(sequences.get(0).size() - 1);
    }

}
