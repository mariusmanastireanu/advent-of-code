package org.advent._2024.day05;

import org.advent.helper.AbstractSolver;

import java.util.*;

public abstract class Day05Solver extends AbstractSolver {

    protected List<int[]> rules = new ArrayList<>();
    protected Map<Integer, Set<Integer>> numberBeforeNumber = new HashMap<>();
    protected Map<Integer, Set<Integer>> numberAfterNumber = new HashMap<>();

    @Override
    public void solve(Collection<String> lines) {
        lines.forEach(line -> {
            if (line.contains("|")) {
                processRule(line);
            } else if (line.contains(",")) {
                processInstruction(Arrays.stream(line.split(","))
                        .map(Integer::parseInt)
                        .toList());
            }
        });
    }

    protected abstract void processInstruction(List<Integer> numbers);

    private void processRule(String line) {
        int from = Integer.parseInt(line.split("\\|")[0]);
        int to = Integer.parseInt(line.split("\\|")[1]);

        numberBeforeNumber.putIfAbsent(from, new HashSet<>());
        numberAfterNumber.putIfAbsent(to, new HashSet<>());

        numberBeforeNumber.get(from).add(to);
        numberAfterNumber.get(to).add(from);

        rules.add(new int[]{from, to});
    }

    protected boolean isInstructionOrderedCorrectly(List<Integer> numbers) {
        for (int i = 0; i < numbers.size(); i++) {
            var currentNumber = numbers.get(i);
            for (int before = 0; before < i; before++) {
                var beforeNumber = numbers.get(before);
                if (!numberAfterNumber.containsKey(currentNumber)
                        || !numberAfterNumber.get(currentNumber).contains(beforeNumber)) {
                    return false;
                }
            }
            for (int after = i + 1; after < numbers.size(); after++) {
                var afterNumber = numbers.get(after);
                if (!numberBeforeNumber.containsKey(currentNumber)
                        || !numberBeforeNumber.get(currentNumber).contains(afterNumber)) {
                    return false;
                }
            }
        }
        return true;
    }

}
