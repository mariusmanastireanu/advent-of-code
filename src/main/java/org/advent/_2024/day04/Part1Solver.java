package org.advent._2024.day04;

import org.advent.helper.AbstractSolver;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Pattern;

public class Part1Solver extends AbstractSolver {

    private int result = 0;

    @Override
    public void solve(Collection<String> lines) {
        var allLines = new ArrayList<>(lines);
        allLines.addAll(getDiagonals((List<String>) lines));
        allLines.addAll(getVerticals((List<String>) lines));
        allLines.addAll(allLines.stream().map(StringBuilder::new).map(StringBuilder::reverse).map(StringBuilder::toString).toList());

        result = allLines.stream().map(line -> {
            var pattern = Pattern.compile("XMAS");
            var matcher = pattern.matcher(line);
            int count = 0;
            while (matcher.find()) {
                count++;
            }
            return count;
        }).reduce(0, Integer::sum);
    }

    private List<String> getDiagonals(List<String> matrix) {
        var diagonals = new ArrayList<String>();

        int rows = matrix.size();
        int cols = matrix.get(0).length();

        // Forward diagonals
        for (int i = 0; i < rows; i++) {
            var diagonal = new StringBuilder();
            int row = i, col = 0;
            while (row < rows && col < cols) {
                diagonal.append(matrix.get(row).charAt(col));
                row++;
                col++;
            }
            diagonals.add(diagonal.toString());
        }
        for (int j = 1; j < cols; j++) {
            var diagonal = new StringBuilder();
            int row = 0, col = j;
            while (row < rows && col < cols) {
                diagonal.append(matrix.get(row).charAt(col));
                row++;
                col++;
            }
            diagonals.add(diagonal.toString());
        }

        // Backward diagonals
        for (int i = 0; i < rows; i++) {
            var diagonal = new StringBuilder();
            int row = i, col = 0;
            while (row >= 0 && col < cols) {
                diagonal.append(matrix.get(row).charAt(col));
                row--;
                col++;
            }
            diagonals.add(diagonal.toString());
        }
        for (int j = 1; j < cols; j++) {
            var diagonal = new StringBuilder();
            int row = rows - 1, col = j;
            while (row >= 0 && col < cols) {
                diagonal.append(matrix.get(row).charAt(col));
                row--;
                col++;
            }
            diagonals.add(diagonal.toString());
        }

        return diagonals;
    }

    private List<String> getVerticals(List<String> matrix) {
        var verticals = new ArrayList<String>();

        int rows = matrix.size();
        int cols = matrix.getFirst().length();

        for (int i = 0; i < cols; i++) {
            var vertical = new StringBuilder();
            for (int j = 0; j < rows; j++) {
                vertical.append(matrix.get(j).charAt(i));
            }
            verticals.add(vertical.toString());
        }

        return verticals;
    }

    @Override
    public Object getResult() {
        return result;
    }

}
