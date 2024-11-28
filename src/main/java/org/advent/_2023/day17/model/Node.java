package org.advent._2023.day17.model;

import lombok.Builder;
import lombok.Data;
import org.advent.helper.Direction;

@Data
@Builder
public class Node {

    private final int row;
    private final int column;
    private final Direction direction;
    @Builder.Default
    private final int directionCount = 1;

}
