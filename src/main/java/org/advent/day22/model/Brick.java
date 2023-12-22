package org.advent.day22.model;

import lombok.Data;

@Data
public class Brick {

    private final Point3d from;
    private final Point3d to;

    public Brick(String line) {
        this.from = new Point3d(line.split("~")[0]);
        this.to = new Point3d(line.split("~")[1]);
    }

    public int getMinX() {
        return Math.min(from.getX(), to.getX());
    }

    public int getMaxX() {
        return Math.max(from.getX(), to.getX());
    }

    public int getMinY() {
        return Math.min(from.getY(), to.getY());
    }

    public int getMaxY() {
        return Math.max(from.getY(), to.getY());
    }

    public int getMinZ() {
        return Math.min(from.getZ(), to.getZ());
    }

    public int getMaxZ() {
        return Math.max(from.getZ(), to.getZ());
    }
}
