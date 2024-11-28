package org.advent._2023.day4;

import org.advent.helper.AbstractSolver;
import org.advent.helper.InputReader;

import java.util.HashSet;
import java.util.Set;

public abstract class Day4Solver extends AbstractSolver {

    protected Set<Integer> getWinningCards(String line) {
        var cards = line.split(":")[1].trim().split("\\|");
        var winningCards = InputReader.extractSet(cards[0]);
        var ourCards = InputReader.extractSet(cards[1]);
        winningCards.retainAll(ourCards);
        return winningCards;
    }

}
