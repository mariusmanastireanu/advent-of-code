package org.advent.day17;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class State implements Comparable<State> {

    private final Node node;
    private final int cost;

    @Override
    public int compareTo(State o) {
        var value = cost - o.cost;
        if (value == 0 && node.getDirection() == o.getNode().getDirection()) {
            value = node.getDirectionCount() - o.getNode().getDirectionCount();
        }
        if (value == 0) {
            value = getNode().getRow() + getNode().getColumn() - o.getNode().getRow() - o.getNode().getColumn();
        }
        return value;
    }

}
