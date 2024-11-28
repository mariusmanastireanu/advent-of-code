package org.advent._2023.day10.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.advent.helper.Direction;

@Getter
@AllArgsConstructor
public enum Pipe {

    HORIZONTAL('-', Direction.EAST, Direction.WEST),
    VERTICAL('|', Direction.NORTH, Direction.SOUTH),
    NORTH_EAST('L', Direction.NORTH, Direction.EAST),
    NORTH_WEST('J', Direction.NORTH, Direction.WEST),
    SOUTH_WEST('7', Direction.SOUTH, Direction.WEST),
    SOUTH_EAST('F', Direction.SOUTH, Direction.EAST),
    SOURCE('S', null, null),
    ;

    private final char character;
    private final Direction from;
    private final Direction to;

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
