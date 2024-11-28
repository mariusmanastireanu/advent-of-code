package org.advent._2023.day12;

public class Part2Solver extends Day12Solver {

    @Override
    protected ConditionRecord createConditionRecord(String line) {
        var row = new ConditionRecord(line);
        row.expandBrokenRecordGroups();
        return row;
    }

}
