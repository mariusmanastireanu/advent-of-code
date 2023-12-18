package org.advent.day17;

import lombok.Builder;
import lombok.Data;
import org.advent.helper.Direction;

@Data
@Builder
public class State implements Comparable<State> {

    private final int row;
    private final int column;
    private final Direction direction;
    @Builder.Default
    private final int directionCount = 1;
    private final int cost;

    @Override
    public int compareTo(State o) {
        var value = cost - o.cost;
        if (value == 0 && direction == o.direction) {
            value = directionCount - o.directionCount;
        }
        if (value == 0) {
            value = row + column - o.row - o.column;
        }
        return value;
    }

}
