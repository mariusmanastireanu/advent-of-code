package org.advent.day17;

import lombok.Builder;
import lombok.Data;
import org.advent.helper.Direction;

import java.util.ArrayList;
import java.util.Collection;

@Data
@Builder
public class State {

    private final int row;
    private final int column;
    private final Direction direction;
    @Builder.Default
    private final int directionCount = 1;
    private final int cost;

    private final int minimumNumberOfSteps;
    private final int maximumNumberOfSteps;

    public Collection<Direction> getPossibleDirections() {
        var directions = new ArrayList<Direction>();
        if (directionCount < maximumNumberOfSteps) {
            directions.add(direction);
        }
        if (directionCount >= minimumNumberOfSteps) {
            directions.add(direction.getLeft());
            directions.add(direction.getRight());
        }
        return directions;
    }

}
