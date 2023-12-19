package org.advent.day19.model;

import lombok.Data;

import java.util.function.BiConsumer;
import java.util.function.Function;

@Data
public class Condition {

    private final Function<Part, Integer> getter;
    private final BiConsumer<Combination, Long> setter;
    private final BiConsumer<Combination, Long> oppositeSetter;
    private final Operator operator;
    private final int number;
    private final String destination;

    public Condition(String condition) {
        if (!condition.contains(":")) {
            getter = null;
            setter = null;
            oppositeSetter = null;
            operator = null;
            number = 0;
            destination = condition;
        } else {
            if (condition.contains("<")) {
                operator = Operator.LESS_THAN;
                number = Integer.parseInt(condition.substring(condition.indexOf("<") + 1, condition.indexOf(":")));
            } else if (condition.contains(">")) {
                operator = Operator.GRATER_THAN;
                number = Integer.parseInt(condition.substring(condition.indexOf(">") + 1, condition.indexOf(":")));
            } else {
                throw new RuntimeException("Unknown operator: " + condition);
            }

            if (condition.startsWith("x")) {
                getter = Part::getX;
                setter = Operator.LESS_THAN == operator ? Combination::setMaxX : Combination::setMinX;
                oppositeSetter = Operator.LESS_THAN == operator ? Combination::setMinX : Combination::setMaxX;
            } else if (condition.startsWith("m")) {
                getter = Part::getM;
                setter = Operator.LESS_THAN == operator ? Combination::setMaxM : Combination::setMinM;
                oppositeSetter = Operator.LESS_THAN == operator ? Combination::setMinM : Combination::setMaxM;
            } else if (condition.startsWith("a")) {
                getter = Part::getA;
                setter = Operator.LESS_THAN == operator ? Combination::setMaxA : Combination::setMinA;
                oppositeSetter = Operator.LESS_THAN == operator ? Combination::setMinA : Combination::setMaxA;
            } else if (condition.startsWith("s")) {
                getter = Part::getS;
                setter = Operator.LESS_THAN == operator ? Combination::setMaxS : Combination::setMinS;
                oppositeSetter = Operator.LESS_THAN == operator ? Combination::setMinS : Combination::setMaxS;
            } else {
                throw new RuntimeException("Unknown condition: " + condition);
            }

            destination = condition.split(":")[1];
        }
    }

    public String apply(Part part) {
        if (operator == null) {
            return destination;
        }
        var partNumber = getter.apply(part);
        switch (operator) {
            case LESS_THAN -> {
                if (partNumber < number) {
                    return destination;
                }
            }
            case GRATER_THAN -> {
                if (partNumber > number) {
                    return destination;
                }
            }
            default -> throw new RuntimeException("Unknown operator: " + operator);
        }
        return null;
    }

    public Combination alter(Combination combination, boolean opposite) {
        var result = new Combination(combination);
        if (setter != null && oppositeSetter != null) {
            if (opposite) {
                oppositeSetter.accept(result, (long) number);
            } else {
                setter.accept(result, operator.equals(Operator.LESS_THAN) ? (long) number - 1 : (long) number + 1);
            }
        }
        return result;
    }
}
