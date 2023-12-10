package org.advent.day10.model;

public enum Direction {

    EAST, WEST, NORTH, SOUTH;

    public int getRowOffset() {
        switch (this) {
            case NORTH:
                return -1;
            case SOUTH:
                return 1;
            default:
                return 0;
        }
    }

    public int getColOffset() {
        switch (this) {
            case EAST:
                return 1;
            case WEST:
                return -1;
            default:
                return 0;
        }
    }

    public Direction getOpposite() {
        switch (this) {
            case EAST:
                return WEST;
            case WEST:
                return EAST;
            case NORTH:
                return SOUTH;
            case SOUTH:
                return NORTH;
            default:
                throw new IllegalArgumentException("No opposite direction for " + this);
        }
    }

}
