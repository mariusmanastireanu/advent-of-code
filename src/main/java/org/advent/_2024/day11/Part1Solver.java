package org.advent._2024.day11;

import java.util.ArrayList;
import java.util.List;

public class Part1Solver extends Day11Solver {

    @Override
    protected void processStones(List<Long> stones) {
        for (int i = 0; i < 25; i++) {
            stones = blink(stones);
        }
        result = stones.size();
    }

    private List<Long> blink(List<Long> stones) {
        var result = new ArrayList<Long>();
        for (Long stone : stones) {
            if (stone == 0) {
                result.add(1L);
            } else {
                var numberAsString = String.valueOf(stone);
                if (numberAsString.length() % 2 == 0) {
                    result.add(Long.parseLong(numberAsString.substring(0, numberAsString.length() / 2)));
                    result.add(Long.parseLong(numberAsString.substring(numberAsString.length() / 2)));
                } else {
                    result.add(stone * 2024);
                }
            }
        }
        return result;
    }

}
