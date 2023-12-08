package org.advent.day3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Part2Solver extends Day3Solver {

    private final Map<Point, List<Integer>> map = new HashMap<>();

    @Override
    public void resolve(List<List<Character>> charMatrix, Integer line, Integer columnStart, Integer columnEnd, Integer number) {
        var point = checkNeighboursOfNumber(charMatrix, line, columnStart, columnEnd, this::isGear);
        if (point == null) {
            return;
        }
        map.putIfAbsent(point, new ArrayList<>());
        map.get(point).add(number);
    }

    @Override
    public Object getResult() {
        return map.values()
                .stream()
                .filter(integers -> integers.size() == 2)
                .map(integers -> integers.get(0) * integers.get(1))
                .mapToInt(Integer::intValue)
                .sum();
    }

    private boolean isGear(char ch) {
        return '*' == ch;
    }

}
