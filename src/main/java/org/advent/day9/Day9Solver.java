package org.advent.day9;

import org.advent.helper.AbstractSolver;
import org.advent.helper.InputReader;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Day9Solver extends AbstractSolver {

    private long result;

    @Override
    public Object getResult() {
        return result;
    }

    @Override
    public void solve(Collection<String> lines) {
        result = lines.stream()
                .map(this::getValueForLine)
                .mapToLong(Long::longValue)
                .sum();
    }

    private long getValueForLine(String line) {
        var sequences = populateSequencesList(line);
        return solveSequence(sequences);
    }

    private List<List<Long>> populateSequencesList(String line) {
        var sequences = new ArrayList<List<Long>>();
        sequences.add(InputReader.extractCollection(line, Long::parseLong, Collectors.toList()));
        while (!sequences.get(sequences.size() - 1).stream().allMatch(l -> l == 0L)) {
            sequences.add(getNextSequence(sequences.get(sequences.size() - 1)));
        }
        return sequences;
    }

    private List<Long> getNextSequence(List<Long> sequence) {
        var nextSequence = new ArrayList<Long>();
        for (int i = 0; i < sequence.size() - 1; i++) {
            nextSequence.add(sequence.get(i + 1) - sequence.get(i));
        }
        return nextSequence;
    }

    protected abstract long solveSequence(List<List<Long>> sequences);

}
