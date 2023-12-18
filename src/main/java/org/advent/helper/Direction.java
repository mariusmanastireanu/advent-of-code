package org.advent.helper;

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

    public Direction getLeft() {
        switch (this) {
            case EAST:
                return NORTH;
            case WEST:
                return SOUTH;
            case NORTH:
                return WEST;
            case SOUTH:
                return EAST;
            default:
                throw new IllegalArgumentException("No left direction for " + this);
        }
    }

    public Direction getRight() {
        switch (this) {
            case EAST:
                return SOUTH;
            case WEST:
                return NORTH;
            case NORTH:
                return EAST;
            case SOUTH:
                return WEST;
            default:
                throw new IllegalArgumentException("No right direction for " + this);
        }
    }

}
