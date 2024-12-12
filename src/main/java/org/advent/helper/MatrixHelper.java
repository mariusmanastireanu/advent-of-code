package org.advent.helper;

import lombok.experimental.UtilityClass;

import java.util.Collection;

@UtilityClass
public class MatrixHelper {

    public static char[][] readMatrix(Collection<String> lines) {
        var matrix = new char[lines.size()][];
        int row = 0;
        for (String line : lines) {
            matrix[row++] = line.toCharArray();
        }
        return matrix;
    }

}
