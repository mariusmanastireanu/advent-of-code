package org.advent._2023.day6;

import org.advent.helper.AbstractSolver;
import org.advent.helper.InputReader;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day6Solver extends AbstractSolver {

    protected final List<Long> times = new LinkedList<>();
    protected final List<Long> distances = new LinkedList<>();

    private long result = 0L;

    @Override
    public void solve(Collection<String> lines) {
        readInput(lines);
        result = IntStream.range(0, times.size())
                .mapToObj(this::getNumberOfSolutions)
                .mapToInt(Long::intValue)
                .reduce(1, (a, b) -> a * b);
    }

    protected void readInput(Collection<String> lines) {
        var index = 0;
        for (String line : lines) {
            var collection = InputReader.extractCollection(getNumbersStringFromLine(line), Long::parseLong, Collectors.toList());
            if (index == 0) {
                times.addAll(collection);
            } else {
                distances.addAll(collection);
            }
            index++;
        }
    }

    @Override
    public Object getResult() {
        return result;
    }

    protected long getNumberOfSolutions(int forRace) {
        var time = times.get(forRace);
        var distance = distances.get(forRace);
        var startingFrom = 0L;
        var until = time - 1L;
        for (int seconds = 1; seconds < time; seconds++) {
            var actualDistance = seconds * (time - seconds);
            if (startingFrom == 0 && actualDistance > distance) {
                startingFrom = seconds;
            }
            if (startingFrom != 0 && actualDistance <= distance) {
                until = seconds;
                break;
            }
        }
        return until - startingFrom;
    }


    protected String getNumbersStringFromLine(String line) {
        return line.split(":")[1].trim();
    }

}
