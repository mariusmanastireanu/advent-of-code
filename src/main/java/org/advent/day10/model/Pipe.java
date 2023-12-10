package org.advent.day10.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Pipe {

    HORIZONTAL('-', Direction.EAST, Direction.WEST, true),
    VERTICAL('|', Direction.NORTH, Direction.SOUTH, true),
    NORTH_EAST('L', Direction.NORTH, Direction.EAST, false),
    NORTH_WEST('J', Direction.NORTH, Direction.WEST, false),
    SOUTH_WEST('7', Direction.SOUTH, Direction.WEST, false),
    SOUTH_EAST('F', Direction.SOUTH, Direction.EAST, false),
    SOURCE('S', null, null, false),
    ;

    private final char character;
    private final Direction from;
    private final Direction to;
    private final boolean bidirectional;

    public boolean isConnectedFrom(Direction direction) {
        return from == direction || to == direction;
    }

    public static Pipe getPipe(char character) {
        for (Pipe pipe : Pipe.values()) {
            if (pipe.getCharacter() == character) {
                return pipe;
            }
        }
        throw new IllegalArgumentException("No pipe found for character " + character);
    }

    public static boolean canHaveNeighbor(Pipe pipe, Direction direction) {
        if (Pipe.SOURCE.equals(pipe)) {
            return true;
        }
        switch (direction) {
            case EAST:
                return Pipe.HORIZONTAL.equals(pipe)
                        || Pipe.SOUTH_EAST.equals(pipe)
                        || Pipe.NORTH_EAST.equals(pipe);
            case WEST:
                return Pipe.HORIZONTAL.equals(pipe)
                        || Pipe.SOUTH_WEST.equals(pipe)
                        || Pipe.NORTH_WEST.equals(pipe);
            case NORTH:
                return Pipe.VERTICAL.equals(pipe)
                        || Pipe.NORTH_EAST.equals(pipe)
                        || Pipe.NORTH_WEST.equals(pipe);
            case SOUTH:
                return Pipe.VERTICAL.equals(pipe)
                        || Pipe.SOUTH_EAST.equals(pipe)
                        || Pipe.SOUTH_WEST.equals(pipe);
        }
        return false;
    }

}
