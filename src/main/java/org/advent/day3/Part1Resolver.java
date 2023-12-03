package org.advent.day3;

import lombok.Getter;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Part1Resolver extends AbstractResolver {

    private final AtomicInteger result = new AtomicInteger(0);
    @Getter private final String filename;

    public Part1Resolver(String filename) {
        this.filename = filename;
    }

    @Override
    public void resolve(List<List<Character>> map, Integer line, Integer colStart, Integer colEnd, Integer number) {
        if (checkNeighboursOfNumber(map, line, colStart, colEnd, this::isSymbol) != null) {
            result.getAndAdd(number);
        }
    }

    @Override
    public int getResult() {
        return result.get();
    }

    private boolean isSymbol(char ch) {
        return !Character.isDigit(ch) && '.' != ch;
    }

}
