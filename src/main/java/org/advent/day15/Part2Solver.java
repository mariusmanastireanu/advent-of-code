package org.advent.day15;

import org.advent.helper.AbstractSolver;

import java.util.*;

public class Part2Solver extends AbstractSolver {

    private long result = 0L;

    @Override
    public Object getResult() {
        return result;
    }

    @Override
    public void solve(Collection<String> lines) {
        var boxMap = new HashMap<Integer, LinkedHashMap<String, Integer>>();
        Arrays.stream(lines.iterator().next().split(","))
                .forEach(instruction -> {
                    if (instruction.contains("=")) {
                        var label = new AsciiInstruction(instruction.split("=")[0]);
                        var hash = label.hashCode();
                        var number = Integer.parseInt(instruction.split("=")[1]);
                        boxMap.putIfAbsent(hash, new LinkedHashMap<>());
                        boxMap.get(hash).put(label.getInstruction(), number);
                    } else {
                        var label = new AsciiInstruction(instruction.substring(0, instruction.length() - 1));
                        var hash = label.hashCode();
                        var existingLenses = boxMap.get(hash);
                        if (existingLenses == null) {
                            return;
                        }
                        existingLenses.remove(label.getInstruction());
                        if (existingLenses.isEmpty()) {
                            boxMap.remove(hash);
                        }
                    }
                });
        result = boxMap.entrySet().stream()
                .map(this::computeBoxFocalLength)
                .mapToLong(Long::longValue)
                .sum();
    }

    private long computeBoxFocalLength(Map.Entry<Integer, LinkedHashMap<String, Integer>> entry) {
        var index = 0;
        var sum  = 0L;
        for (Map.Entry<String, Integer> label : entry.getValue().entrySet()) {
            index++;
            sum += (long) (1 + entry.getKey()) * index * label.getValue();
        }
        return sum;
    }

}
