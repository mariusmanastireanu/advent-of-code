package org.advent.day11;

import lombok.Getter;
import org.advent.helper.Point;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
public class Universe {

    private final List<List<String>> map = new ArrayList<>();
    private final Map<Integer, Point> galaxyIndexMap = new LinkedHashMap<>();
    private final List<Integer> emptyRows = new ArrayList<>();
    private final List<Integer> emptyCols = new ArrayList<>();

    public Universe(Collection<String> lines) {
        parseInput(lines);
        indexGalaxies();
        countEmptyRowsAndCols();
    }

    private void parseInput(Collection<String> lines) {
        var galaxyIndex = new AtomicInteger(1);
        for (String line : lines) {
            var list = new ArrayList<String>();
            for (char c : line.toCharArray()) {
                if (c == '#') {
                    list.add(String.valueOf(galaxyIndex.getAndIncrement()));
                } else {
                    list.add(".");
                }
            }
            map.add(list);
        }
    }

    public void indexGalaxies() {
        for (int rowIndex = 0; rowIndex < map.size(); rowIndex++) {
            for (int colIndex = 0; colIndex < map.get(rowIndex).size(); colIndex++) {
                if (!".".equals(map.get(rowIndex).get(colIndex))) {
                    galaxyIndexMap.put(Integer.parseInt(String.valueOf(map.get(rowIndex).get(colIndex))),
                            Point.builder().x(rowIndex).y(colIndex).build());
                }
            }
        }
    }

    private void countEmptyRowsAndCols() {
        for (int i = 0; i < map.size(); i++) {
            if (new HashSet<>(map.get(i)).size() == 1) {
                emptyRows.add(i);
            }
        }

        for (int columnIndex = 0; columnIndex < map.get(0).size(); columnIndex++) {
            var allEmpty = true;
            for (int rowIndex = 0; rowIndex < map.size(); rowIndex++) {
                if (!".".equals(map.get(rowIndex).get(columnIndex))) {
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
