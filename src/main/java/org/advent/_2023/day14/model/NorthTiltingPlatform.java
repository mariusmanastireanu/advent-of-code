package org.advent._2023.day14.model;

import java.util.List;

public class NorthTiltingPlatform extends Platform {

    public NorthTiltingPlatform(List<String> rows) {
        super(rows);
    }

    @Override
    public long tiltAndComputeWeight() {
        var total = 0L;
        for (int column = 0; column < rows.get(0).length(); column++) {
            var row = findNextStartingRowIndex(0, column);
            while (row < rows.size()) {
                var firstHeavyRockIndex = findFirstHeavyRock(row, column);
                var numberOfRocksUntilHeavyOne = findNumberOfRocksBetween(row, column, firstHeavyRockIndex);

                while (numberOfRocksUntilHeavyOne > 0) {
                    total += rows.size() - row;
                    row++;
                    numberOfRocksUntilHeavyOne--;
                }

                row = findNextStartingRowIndex(firstHeavyRockIndex + 1, column);
            }
        }
        return total;
    }

    private int findFirstHeavyRock(int row, int column) {
        for (int i = row + 1; i < rows.size(); i++) {
            char c = rows.get(i).charAt(column);
            if (c == '#') {
                return i;
            }
        }
        return rows.size();
    }

    private int findNumberOfRocksBetween(int row, int column, int firstHeavyRockIndex) {
        var count = 0;
        for (int i = row; i < firstHeavyRockIndex; i++) {
            char c = rows.get(i).charAt(column);
            if (c == 'O') {
                count++;
            }
        }
        return count;
    }

    private int findNextStartingRowIndex(int row, int column) {
        for (int i = row; i < rows.size(); i++) {
            char c = rows.get(i).charAt(column);
            if (c != '#') {
                return i;
            }
        }
        return rows.size();
    }

}
