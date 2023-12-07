package org.advent.day7.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Card {

    ONE(1, "1"),
    TWO(2, "2"),
    THREE(3, "3"),
    FOUR(4, "4"),
    FIVE(5, "5"),
    SIX(6, "6"),
    SEVEN(7, "7"),
    EIGHT(8, "8"),
    NINE(9, "9"),
    TEN(10, "T"),
    JACK(11, "J"),
    QUEEN(12, "Q"),
    KING(13, "K"),
    ACE(14, "A");

    private final int value;
    private final String symbol;

    public static Card fromSymbol(String symbol) {
        for (Card card : Card.values()) {
            if (card.getSymbol().equals(symbol)) {
                return card;
            }
        }
        throw new IllegalArgumentException("Unknown symbol: " + symbol);
    }

}
