package org.advent._2023.day14.model;

import java.util.*;

public class CycleTiltingPlatform extends Platform {

    private static final int NUMBER_OR_CYCLES = 1_000_000_000;
    private final Map<List<String>, Integer> stateToIteration = new HashMap<>();

    public CycleTiltingPlatform(List<String> rows) {
        super(rows);
    }

    @Override
    public long tiltAndComputeWeight() {
        for (int i = 1; i <= NUMBER_OR_CYCLES; i++) {
            tilt();
            var state = new ArrayList<>(rows);
            if (stateToIteration.containsKey(state)) {
                return computeWeight(state, i);
            } else {
                stateToIteration.put(state, i);
            }
        }
        return computeWeight();
    }

    private Long computeWeight(ArrayList<String> state, int i) {
        Integer firstCycleAppearance = stateToIteration.get(state);
        for (Map.Entry<List<String>, Integer> mapEntry : stateToIteration.entrySet()) {
            if (Objects.equals(mapEntry.getValue(), firstCycleAppearance)) {
                var platform = new CycleTiltingPlatform(mapEntry.getKey());
                var iterationsAfterCycle = (NUMBER_OR_CYCLES - firstCycleAppearance) % (i - firstCycleAppearance);
                for (int j = 0; j < iterationsAfterCycle; j++) {
                    platform.tilt();
                }
                return platform.computeWeight();
            }
        }
        return 0L;
    }

    private void tilt() {
        tiltNorth();
        tiltWest();
        tiltSouth();
        tiltEast();
    }

    private void tiltNorth() {
        for (int column = 0; column < rows.get(0).length(); column++) {
            for (int row = 0; row < rows.size(); row++) {
                if (rows.get(row).charAt(column) == '.') {
                    var rowIndexCopy = row + 1;
                    while (rowIndexCopy < rows.size()) {
                        if (rows.get(rowIndexCopy).charAt(column) == '#') {
                            break;
                        } else if (rows.get(rowIndexCopy).charAt(column) == '.') {
                            rowIndexCopy++;
                        } else if (rows.get(rowIndexCopy).charAt(column) == 'O') {
                            var targetRow = rows.get(rowIndexCopy);
                            targetRow = targetRow.substring(0, column) + '.' +
                                    (column + 1 < targetRow.length() ? targetRow.substring(column + 1) : "");
                            rows.set(rowIndexCopy, targetRow);

                            targetRow = rows.get(row);
                            targetRow = targetRow.substring(0, column) + 'O' +
                                    (column + 1 < targetRow.length() ? targetRow.substring(column + 1) : "");
                            rows.set(row, targetRow);
                            break;
                        }
                    }
                }
            }
        }
    }

    private void tiltWest() {
        for (int row = 0; row < rows.size(); row++) {
            if (!rows.get(row).contains("O")) {
                continue;
            }
            for (int column = 0; column < rows.get(row).length(); column++) {
                if (rows.get(row).charAt(column) == '.') {
                    var columnIndexCopy = column + 1;
                    while (columnIndexCopy < rows.get(row).length()) {
                        if (rows.get(row).charAt(columnIndexCopy) == '#') {
                            break;
                        } else if (rows.get(row).charAt(columnIndexCopy) == '.') {
                            columnIndexCopy++;
                        } else if (rows.get(row).charAt(columnIndexCopy) == 'O') {
                            var targetRow = rows.get(row);
                            targetRow = targetRow.substring(0, columnIndexCopy) + '.' +
                                    (columnIndexCopy + 1 < targetRow.length() ? targetRow.substring(columnIndexCopy + 1) : "");
                            rows.set(row, targetRow);

                            targetRow = rows.get(row);
                            targetRow = targetRow.substring(0, column) + 'O' +
                                    (column + 1 < targetRow.length() ? targetRow.substring(column + 1) : "");
                            rows.set(row, targetRow);
                            break;
                        }
                    }
                }
            }
        }
    }

    private void tiltSouth() {
        for (int column = 0; column < rows.get(0).length(); column++) {
            for (int row = rows.size() - 1; row >= 0; row--) {
                if (rows.get(row).charAt(column) == '.') {
                    var rowIndexCopy = row - 1;
                    while (rowIndexCopy >= 0) {
                        if (rows.get(rowIndexCopy).charAt(column) == '#') {
                            break;
                        } else if (rows.get(rowIndexCopy).charAt(column) == '.') {
                            rowIndexCopy--;
                        } else if (rows.get(rowIndexCopy).charAt(column) == 'O') {
                            var targetRow = rows.get(rowIndexCopy);
                            targetRow = targetRow.substring(0, column) + '.' +
                                    (column + 1 < targetRow.length() ? targetRow.substring(column + 1) : "");
                            rows.set(rowIndexCopy, targetRow);

                            targetRow = rows.get(row);
                            targetRow = targetRow.substring(0, column) + 'O' +
                                    (column + 1 < targetRow.length() ? targetRow.substring(column + 1) : "");
                            rows.set(row, targetRow);
                            break;
                        }
                    }
                }
            }
        }
    }

    private void tiltEast() {
        for (int row = 0; row < rows.size(); row++) {
            if (!rows.get(row).contains("O")) {
                continue;
            }
            for (int column = rows.get(row).length() - 1; column >= 0; column--) {
                if (rows.get(row).charAt(column) == '.') {
                    var columnIndexCopy = column - 1;
                    while (columnIndexCopy >= 0) {
                        if (rows.get(row).charAt(columnIndexCopy) == '#') {
                            break;
                        } else if (rows.get(row).charAt(columnIndexCopy) == '.') {
                            columnIndexCopy--;
                        } else if (rows.get(row).charAt(columnIndexCopy) == 'O') {
                            var targetRow = rows.get(row);
                            targetRow = targetRow.substring(0, columnIndexCopy) + '.' +
                                    (columnIndexCopy + 1 < targetRow.length() ? targetRow.substring(columnIndexCopy + 1) : "");
                            rows.set(row, targetRow);

                            targetRow = rows.get(row);
                            targetRow = targetRow.substring(0, column) + 'O' +
                                    (column + 1 < targetRow.length() ? targetRow.substring(column + 1) : "");
                            rows.set(row, targetRow);
                            break;
                        }
                    }
                }
            }
        }
    }

    private long computeWeight() {
        var total = 0L;
        for (int column = 0; column < rows.get(0).length(); column++) {
            for (int row = 0; row < rows.size(); row++) {
                char c = rows.get(row).charAt(column);
                if (c == 'O') {
                    total += rows.size() - row;
                }
            }
        }
        return total;
    }

}
