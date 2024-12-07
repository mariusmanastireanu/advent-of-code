package org.advent._2024.day07;

import org.advent.helper.AbstractSolver;

import java.util.*;
import java.util.stream.Stream;

public abstract class Day07Solver extends AbstractSolver {

    private long result = 0L;

    @Override
    public void solve(Collection<String> lines) {
        lines.forEach(line -> {
            var parts = line.split(":");
            var total = Long.parseLong(parts[0]);
            var numbers = Stream.of(parts[1].substring(1).split(" ")).map(Long::parseLong).toList();
            result += processEquation(total, numbers);
        });
    }

    private long processEquation(final long total, final List<Long> numbers) {
        var combinations = Operation.generateCombinations(numbers.size() - 1, getOperations());
        for (List<Operation> combination : combinations) {
            long currentTotal = numbers.getFirst();
            for (int i = 0; i < combination.size(); i++) {
                currentTotal = combination.get(i).getFunction().apply(currentTotal, numbers.get(i + 1));
            }
            if (currentTotal == total) {
                return total;
            }
        }
        return 0L;
    }

    protected abstract List<Operation> getOperations();

    @Override
    public Object getResult() {
        return result;
    }
}
