package org.advent._2024.day11;

import org.advent.helper.Pair;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Part2Solver extends Day11Solver {

    private final Map<Pair<Long, Integer>, Long> cache = new HashMap<>();

    @Override
    protected void processStones(List<Long> stones) {
        for (Long stone : stones) {
            result += blink(stone, 75);
        }
    }

    private long blink(long stone, int step) {
        var key = new Pair<>(stone, step);
        if (cache.containsKey(key)) {
            return cache.get(key);
        }

        if (step == 0) {
            cache.put(key, 1L);
            return 1;
        }

        if (stone == 0L) {
            var count = blink(1, step - 1);
            cache.put(key, count);
            return count;
        }

        var numberAsString = String.valueOf(stone);
        if (numberAsString.length() % 2 == 0) {
            var numberLeft = Long.parseLong(numberAsString.substring(0, numberAsString.length() / 2));
            var numberRight = Long.parseLong(numberAsString.substring(numberAsString.length() / 2));
            var count = blink(numberLeft, step - 1) + blink(numberRight, step - 1);
            cache.put(key, count);
            return count;
        }

        var count = blink(stone * 2024, step - 1);
        cache.put(key, count);
        return count;
    }

}
