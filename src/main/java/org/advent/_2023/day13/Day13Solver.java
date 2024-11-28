package org.advent._2023.day13;

import org.advent._2023.day13.model.AbstractPattern;
import org.advent.helper.AbstractSolver;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class Day13Solver extends AbstractSolver {

    private long result = 0L;

    @Override
    public Object getResult() {
        return result;
    }

    @Override
    public void solve(Collection<String> lines) {
        result = extractPatterns(lines).stream()
                .map(AbstractPattern::solvePattern)
                .mapToLong(Long::longValue)
                .sum();
    }

    private List<AbstractPattern> extractPatterns(Collection<String> lines) {
        List<AbstractPattern> patterns = new ArrayList<>();
        List<String> patternLines = new ArrayList<>();
        for (String line : lines) {
            if (line.isEmpty()) {
                patterns.add(createPattern(new ArrayList<>(patternLines)));
                patternLines.clear();
            } else {
                patternLines.add(line);
            }
        }
        patterns.add(createPattern(new ArrayList<>(patternLines)));
        return patterns;
    }

    protected abstract AbstractPattern createPattern(List<String> lines);
}
