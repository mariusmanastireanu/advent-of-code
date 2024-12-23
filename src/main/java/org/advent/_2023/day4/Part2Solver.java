package org.advent._2023.day4;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Part2Solver extends Day4Solver {

    private final Map<Integer, Integer> map = new HashMap<>();

    @Override
    public void solve(Collection<String> lines) {
        var index = 1;
        for (String line : lines) {
            var winningCards = getWinningCards(line).size();
            map.put(index, map.getOrDefault(index, 0) + 1);
            var copies = map.get(index);
            if (winningCards == 0) {
                index++;
                continue;
            }
            for (int i = 1; i <= winningCards; i++) {
                map.put(index + i, map.getOrDefault(index + i, 0) + copies);
            }
            index++;
        }
    }

    @Override
    public Object getResult() {
        return map.values().stream().mapToInt(Integer::intValue).sum();
    }
}
