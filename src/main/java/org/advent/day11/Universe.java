package org.advent.day11;

import lombok.Getter;
import org.advent.helper.Point;

import java.util.*;

@Getter
public class Universe {

    private final List<List<String>> map = new ArrayList<>();
    private final Map<Integer, Point> galaxyIndexMap = new LinkedHashMap<>();
    private final List<Integer> emptyRows = new ArrayList<>();
    private final List<Integer> emptyCols = new ArrayList<>();

    public Universe(Collection<String> lines) {
        parseInput(lines);
        countEmptyColumns();
    }

    private void parseInput(Collection<String> lines) {
        var galaxyIndex = 1;
        var rowIndex = 0;
        for (String line : lines) {
            var lineAsList = new ArrayList<String>();
            var colIndex = 0;
            var hasGalaxy = false;
            for (char c : line.toCharArray()) {
                if (c == '#') {
                    galaxyIndexMap.put(galaxyIndex++, Point.builder().x(rowIndex).y(colIndex).build());
                    hasGalaxy = true;
                }
                lineAsList.add(""+c);
                colIndex++;
            }
            if (!hasGalaxy) {
                emptyRows.add(rowIndex);
            }
            map.add(lineAsList);
            rowIndex++;
        }
    }

    private void countEmptyColumns() {
        for (int columnIndex = 0; columnIndex < map.get(0).size(); columnIndex++) {
            var allEmpty = true;
            for (var line : map) {
                if (!".".equals(line.get(columnIndex))) {
                    allEmpty = false;
                    break;
                }
            }
            if (allEmpty) {
                emptyCols.add(columnIndex);
            }
        }
    }

    public long computeDistance(Point pointA, Point pointB, long expandRate) {
        return Math.abs(pointA.getX() - pointB.getX())
                + ((expandRate - 1) * (emptiesBetween(pointA.getX(), pointB.getX(), emptyRows)))
                + Math.abs(pointA.getY() - pointB.getY())
                + ((expandRate - 1) * (emptiesBetween(pointA.getY(), pointB.getY(), emptyCols)));
    }

    private int emptiesBetween(int a, int b, Collection<Integer> emptyCells) {
        int emptyCount = 0;
        for (int i = Math.min(a, b); i <= Math.max(a, b); i++) {
            if (emptyCells.contains(i)) {
                emptyCount++;
            }
        }
        return emptyCount;
    }

}
