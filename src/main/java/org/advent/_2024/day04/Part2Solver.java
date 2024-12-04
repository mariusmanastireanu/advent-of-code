package org.advent._2024.day04;

import org.advent.helper.AbstractSolver;

import java.util.Collection;
import java.util.List;

public class Part2Solver extends AbstractSolver {

    private int result = 0;

    @Override
    public void solve(final Collection<String> lines) {
        var matrix = (List<String>) lines;
        int rows = matrix.size();
        int cols = matrix.getFirst().length();

        for (int i = 0; i < rows - 2; i++) {
            for (int j = 0; j < cols - 2; j++) {
                if (isMAS(buildFirstDiagonal(matrix, i, j))  && isMAS(buildSecondDiagonal(matrix, i, j))) {
                    result++;
                }
            }
        }
    }

    private String buildFirstDiagonal(final List<String> matrix, final int i, final int j) {
        return String.valueOf(matrix.get(i).charAt(j)) +
                matrix.get(i + 1).charAt(j + 1) +
                matrix.get(i + 2).charAt(j + 2);
    }

    private String buildSecondDiagonal(final List<String> matrix, final int i, final int j) {
        return String.valueOf(matrix.get(i).charAt(j + 2)) +
                matrix.get(i + 1).charAt(j + 1) +
                matrix.get(i + 2).charAt(j);
    }

    private boolean isMAS(final String str) {
        return str.equals("MAS") || str.equals("SAM");
    }

    @Override
    public Object getResult() {
        return result;
    }


}
