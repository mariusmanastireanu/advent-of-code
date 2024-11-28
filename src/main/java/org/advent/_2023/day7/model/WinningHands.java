package org.advent._2023.day7.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum WinningHands {

    HIGHEST_CARD(1),
    ONE_PAIR(2),
    TWO_PAIRS(3),
    THREE_OF_A_KIND(4),
    FULL_HOUSE(5),
    FOUR_OF_A_KIND(6),
    FIVE_OF_A_KIND(7);

    private final int value;


}
