package org.advent.day4;

import org.advent.helper.AbstractSolver;

import java.util.HashSet;
import java.util.Set;

public abstract class Day4Solver extends AbstractSolver {

    public Day4Solver(String filename) {
        super(filename);
    }

    protected Set<Integer> getWinningCards(String line) {
        var cards = line.split(":")[1].trim().split("\\|");
        var winningCards = extractSet(cards[0]);
        var ourCards = extractSet(cards[1]);
        winningCards.retainAll(ourCards);
        return winningCards;
    }

    private Set<Integer> extractSet(String numbers) {
        var set = new HashSet<Integer>();
        for (String number : numbers.trim().split(" ")) {
            if (number.isEmpty()) {
                continue;
            }
            set.add(Integer.parseInt(number));
        }
        return set;
    }

}
