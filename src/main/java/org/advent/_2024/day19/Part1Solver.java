package org.advent._2024.day19;

public class Part1Solver extends Day19Solver {

    @Override
    protected long countSolutions(final String input) {
        return countCombinations(input) != 0 ? 1 : 0;
    }

}
