package org.advent._2023.day7.model;

import java.util.List;
import java.util.Map;

public class HandWithJoker extends Hand {
    public HandWithJoker(String cards, long bet) {
        super(cards, bet);
    }

    @Override
    protected List<Map.Entry<Character, Integer>> sortCards() {
        var sorted = super.sortCards();
        var value = cardToNumberOfApp.get('J');
        if (value != null && sorted.size() != 1) {
            sorted.remove(Map.entry('J', value));
            sorted.get(0).setValue(sorted.get(0).getValue() + value);
        }
        return sorted;
    }

    @Override
    protected int getCardValue(Card card) {
        if (Card.JACK.equals(card)) {
            return 0;
        }
        return super.getCardValue(card);
    }

}
