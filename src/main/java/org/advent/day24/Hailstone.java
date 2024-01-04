package org.advent.day24;

import lombok.Data;

@Data
public class Hailstone {

    private final long x;
    private final long y;

    private final long velocityX;
    private final long velocityY;

    private final double slope;
    private final double yIntercept;

    public Hailstone(final long x, final long y, final long velocityX, final long velocityY) {
        this.x = x;
        this.y = y;
        this.velocityX = velocityX;
        this.velocityY = velocityY;
        this.slope = (double) velocityY / velocityX;
        this.yIntercept = y - slope * x;
    }

}
