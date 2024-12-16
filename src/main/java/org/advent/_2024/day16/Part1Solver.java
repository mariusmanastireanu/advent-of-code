package org.advent._2024.day16;

import org.advent.helper.Direction;
import org.advent.helper.Point;

import java.util.*;

public class Part1Solver extends Day16Solver {

    @Override
    protected int findPath()  {
        Queue<Node> queue = new PriorityQueue<>(Comparator.comparing(Node::getCost));
        Map<Point, Integer> visited = new HashMap<>();
        queue.add(new Node(startingLocation, Direction.EAST, 0));
        visited.put(startingLocation, 0);

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            if (current.getPoint().equals(finishLocation)) {
                return current.getCost();
            }

            for (Direction direction : Direction.values()) {
                Point nextPoint = getNextPoint(current.getPoint(), direction);
                if (isValid(nextPoint)) {
                    int newCost = current.getCost() + 1;
                    if (current.getDirection() != direction) {
                        newCost += 1000;
                    }
                    if (!visited.containsKey(nextPoint) || newCost < visited.get(nextPoint)) {
                        queue.add(new Node(nextPoint, direction, newCost));
                        visited.put(nextPoint, newCost);
                    }
                }
            }
        }
        return -1;
    }
}
