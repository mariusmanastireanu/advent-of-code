package org.advent.day3;

import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Part2Resolver extends AbstractResolver {

    private final Map<Point, List<Integer>> map = new HashMap<>();
    @Getter private final String filename;

    public Part2Resolver(String filename) {
        this.filename = filename;
    }

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
    public int getResult() {
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
