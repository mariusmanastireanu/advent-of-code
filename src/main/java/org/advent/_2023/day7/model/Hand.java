package org.advent._2023.day7.model;

import lombok.Data;

import java.util.*;
import java.util.stream.Collectors;

@Data
public class Hand {

    protected final Map<Character, Integer> cardToNumberOfApp = new HashMap<>();
    protected final String cards;

    private final int value;
    private final long bet;

    public Hand(final String cards, final long bet) {
        this.cards = cards;
        this.value = computeValue();
        this.bet = bet;
    }

    private int computeValue() {
        var sorted = sortCards();
        var has3 = false;
        var has2 = false;
        for (Map.Entry<Character, Integer> entry : sorted) {
            if (entry.getValue() == 5) {
                return WinningHands.FIVE_OF_A_KIND.getValue();
            } else if (entry.getValue() == 4) {
                return WinningHands.FOUR_OF_A_KIND.getValue();
            } else if (entry.getValue() == 3) {
                has3 = true;
            } else if (entry.getValue() == 2) {
                if (has2) {
                    return WinningHands.TWO_PAIRS.getValue();
                } else if (has3) {
                    return WinningHands.FULL_HOUSE.getValue();
                } else {
                    has2 = true;
                }
            } else if (entry.getValue() == 1) {
                break;
            }
        }
        if (has3) {
            return WinningHands.THREE_OF_A_KIND.getValue();
        } else if (has2) {
            return WinningHands.ONE_PAIR.getValue();
        }
        return WinningHands.HIGHEST_CARD.getValue();
    }

    protected List<Map.Entry<Character, Integer>> sortCards() {
        for (Character c : cards.toCharArray()) {
            cardToNumberOfApp.putIfAbsent(c, 0);
            cardToNumberOfApp.put(c, cardToNumberOfApp.get(c) + 1);
        }
        return cardToNumberOfApp.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toList());
    }

    protected int getCardValue(Card card) {
        return card.getValue();
    }

}
