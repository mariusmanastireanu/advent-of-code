package org.advent._2024.day02;

import org.advent.helper.AbstractSolver;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public abstract class Day02Solver extends AbstractSolver {


    @Override
    public void solve(Collection<String> lines) {
        lines.forEach(line -> processRecord(Arrays.stream(line.split(" ")).map(Integer::parseInt).toList()));
    }

    abstract void processRecord(final List<Integer> entryRecord);

    protected boolean isRecordSafe(final List<Integer> entryRecord) {
        boolean ascending = entryRecord.getFirst() < entryRecord.getLast();
        for (int i = 0; i < entryRecord.size() - 1; i++) {
            if (!areLevelsOk(entryRecord.get(i), entryRecord.get(i + 1), ascending)) {
                return false;
            }
        }
        return true;
    }

    protected boolean areLevelsOk(final int levelOne, final int levelTwo, boolean ascending) {
        var dif = Math.abs(levelOne - levelTwo);
        if (dif < 1 || dif > 3) {
            return false;
        }
        return (!ascending || levelOne <= levelTwo) && (ascending || levelOne >= levelTwo);
    }

}
