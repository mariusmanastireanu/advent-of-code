package org.advent._2024.day05;

import java.util.*;

public class Part2Solver extends Day05Solver {

    private int result = 0;

    @Override
    protected void processInstruction(final List<Integer> numbers) {
        if (!isInstructionOrderedCorrectly(numbers)) {
            result += GraphUtils.topologicalSort(numbers, rules).get(numbers.size() / 2);
        }
    }

    @Override
    public Object getResult() {
        return result;
    }

}
