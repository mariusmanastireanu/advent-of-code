package org.advent._2024.day01;

import java.util.*;

public class Part2Solver extends Day01Solver {

    private final Map<Integer, Integer> appearancesMap = new HashMap<>();
    private Long result = 0L;

    @Override
    void processDestinationList(final int i) {
        appearancesMap.putIfAbsent(i, 0);
        appearancesMap.put(i, appearancesMap.get(i) + 1);
    }

    @Override
    void computeResult() {
        sourceList.stream()
                .filter(appearancesMap::containsKey)
                .forEach(number -> result += (long) number * appearancesMap.get(number));
    }

    @Override
    public Object getResult() {
        return result;
    }

}
