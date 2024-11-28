package org.advent._2023.day7.model;

import java.util.Comparator;

public class HandComparator implements Comparator<Hand> {
    @Override
    public int compare(Hand o1, Hand o2) {
        var dif = Integer.compare(o1.getValue(), o2.getValue());
        if (dif == 0) {
            for (int i = 0; i < 5; i++) {
                var o1card = Card.fromSymbol("" + o1.getCards().charAt(i));
                var o2card = Card.fromSymbol("" + o2.getCards().charAt(i));
                var cardDif = -1 * Integer.compare(o1.getCardValue(o1card), o2.getCardValue(o2card));
                if (cardDif != 0) {
                    return cardDif;
                }
            }
            return dif;
        } else {
            return -1 * dif;
        }
    }

}
