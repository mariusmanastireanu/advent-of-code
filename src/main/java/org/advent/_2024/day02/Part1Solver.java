package org.advent._2024.day02;

import java.util.List;

public class Part1Solver extends Day02Solver {

    private int result = 0;

    @Override
    void processRecord(final List<Integer> entryRecord) {
        if (isRecordSafe(entryRecord)) {
            result++;
        }
    }

    @Override
    public Object getResult() {
        return result;
    }

}
