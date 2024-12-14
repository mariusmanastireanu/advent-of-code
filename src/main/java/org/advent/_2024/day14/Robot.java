package org.advent._2024.day14;

import lombok.Builder;
import lombok.Data;
import org.advent.helper.Point;

@Data
@Builder
public class Robot {

    private Point location;
    private Point velocity;

    public Point move(int times) {
        var newLocation = new Point(
                (location.getX() + velocity.getX() * times) % Day14Solver.WIDTH,
                (location.getY() + velocity.getY() * times) % Day14Solver.HEIGHT
        );
        if (newLocation.getX() < 0) {
            newLocation.setX(newLocation.getX() + Day14Solver.WIDTH);
        }
        if (newLocation.getY() < 0) {
            newLocation.setY(newLocation.getY() + Day14Solver.HEIGHT);
        }
        return newLocation;
    }

    public int getQuadrant(Point location) {
        if (location.getX() < Day14Solver.WIDTH / 2) {
            if (location.getY() < Day14Solver.HEIGHT / 2) {
                return 1;
            } else if (location.getY() > Day14Solver.HEIGHT / 2) {
                return 2;
            }
        } else if (location.getX() > Day14Solver.WIDTH / 2) {
            if (location.getY() < Day14Solver.HEIGHT / 2) {
                return 3;
            } else if (location.getY() > Day14Solver.HEIGHT / 2) {
                return 4;
            }
        }
        return 0;
    }

}
