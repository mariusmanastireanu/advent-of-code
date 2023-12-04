package org.advent.day4;

import java.util.Collection;

public class Part1Solver extends Day4Solver {

    private int result = 0;

    public Part1Solver(String filename) {
        super(filename);
    }

    @Override
    public void solve(Collection<String> lines) {
        result = lines.stream()
                .map(this::processLine)
                .mapToInt(Integer::intValue)
                .sum();
    }

    private int processLine(String line) {
        var winningCards = getWinningCards(line);
        if (winningCards.isEmpty()) {
            return 0;
        } else {
            return (int) Math.pow(2, winningCards.size() - 1);
        }
    }

    @Override
    public Object getResult() {
        return result;
    }

}
