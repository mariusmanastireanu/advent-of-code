package org.advent._2024.day02;

import java.util.ArrayList;
import java.util.List;

public class Part2Solver extends Day02Solver {

    private int result = 0;

    @Override
    void processRecord(final List<Integer> entryRecord) {
        if (isRecordSafe(entryRecord) || generateSublists(entryRecord).stream().anyMatch(this::isRecordSafe)) {
            result++;
        }
    }

    private List<List<Integer>> generateSublists(List<Integer> originalList) {
        var sublists = new ArrayList<List<Integer>>();
        for (int i = 0; i < originalList.size(); i++) {
            var sublist = new ArrayList<>(originalList);
            sublist.remove(i);
            sublists.add(sublist);
        }
        return sublists;
    }

    @Override
    public Object getResult() {
        return result;
    }

}
