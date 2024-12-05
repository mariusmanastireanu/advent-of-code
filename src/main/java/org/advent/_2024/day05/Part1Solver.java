package org.advent._2024.day05;

import java.util.List;

public class Part1Solver extends Day05Solver {

    private int result = 0;

    @Override
    protected void processInstruction(final List<Integer> numbers) {
        if (isInstructionOrderedCorrectly(numbers)) {
            result += numbers.get(numbers.size()/2);
        }
    }

    @Override
    public Object getResult() {
        return result;
    }

}
