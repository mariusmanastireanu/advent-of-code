package org.advent._2024.day16;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.advent.helper.Direction;
import org.advent.helper.Point;

@Data
@AllArgsConstructor
public class Node {

    private Point point;
    private Direction direction;
    private int cost;

}
