package org.advent.day3;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Part1Solver extends Day3Solver {

    private final AtomicInteger result = new AtomicInteger(0);

    @Override
    public void resolve(List<List<Character>> map, Integer line, Integer colStart, Integer colEnd, Integer number) {
        if (checkNeighboursOfNumber(map, line, colStart, colEnd, this::isSymbol) != null) {
            result.getAndAdd(number);
        }
    }

    @Override
    public Object getResult() {
        return result.get();
    }

    private boolean isSymbol(char ch) {
        return !Character.isDigit(ch) && '.' != ch;
    }

}
