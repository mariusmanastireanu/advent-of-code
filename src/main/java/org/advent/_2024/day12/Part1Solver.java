package org.advent._2024.day12;

import org.advent.helper.Direction;

public class Part1Solver extends Day12Solver {

    @Override
    protected int computeValueForLocation(final int row, final int col) {
        var current = matrix[row][col];
        var perimeter = 0;
        for (Direction d : Direction.values()) {
            if (row + d.getRowOffset() < 0
                    || row + d.getRowOffset() >= matrix.length
                    || col + d.getColOffset() < 0
                    || col + d.getColOffset() >= matrix[0].length
                    || matrix[row + d.getRowOffset()][col + d.getColOffset()] != current) {
                perimeter++;
            }
        }
        return perimeter;
    }

}
