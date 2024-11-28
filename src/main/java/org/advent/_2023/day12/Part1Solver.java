package org.advent._2023.day12;

public class Part1Solver extends Day12Solver {

    @Override
    protected ConditionRecord createConditionRecord(String line) {
        return new ConditionRecord(line);
    }

}
