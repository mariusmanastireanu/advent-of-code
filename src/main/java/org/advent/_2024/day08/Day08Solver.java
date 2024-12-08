package org.advent._2024.day08;

import org.advent.helper.AbstractSolver;
import org.advent.helper.Point;

import java.util.*;

public abstract class Day08Solver extends AbstractSolver {

    protected final Set<Point> antinodes = new HashSet<>();
    private int rowSize;
    private int colSize;

    @Override
    public void solve(Collection<String> lines) {
        rowSize = lines.size();
        var antennaLocations = new HashMap<Character, List<Point>>();
        for (int row = 0; row < lines.size(); row++) {
            var line = ((List<String>) lines).get(row);
            colSize = line.length();
            for (int col = 0; col < line.length(); col++) {
                char c = line.charAt(col);
                if (c != '.') {
                    antennaLocations.computeIfAbsent(c, k -> new ArrayList<>()).add(new Point(row, col));
                }
            }
        }
        antennaLocations.forEach((antenna, locations) -> placeAntinodes(locations));
    }

    private void placeAntinodes(List<Point> locations) {
        for (int i = 0; i < locations.size(); i++) {
            for (int j = i + 1; j < locations.size(); j++) {
                processNodes(locations.get(i), locations.get(j));
            }
        }
    }

    protected void processNodes(Point startPoint, Point endPoint) {
        var rowDif = (int) Math.abs(startPoint.getX() - endPoint.getX());
        var colDif = (int) Math.abs(startPoint.getY() - endPoint.getY());

        var upperPoint = startPoint.getX() < endPoint.getX() ? startPoint : endPoint;
        var lowerPoint = startPoint.equals(upperPoint) ? endPoint : startPoint;

        if (upperPoint.getY() <= lowerPoint.getY()) {
            addAntinodes(upperPoint, -rowDif, -colDif);
            addAntinodes(lowerPoint, rowDif, colDif);
        } else {
            addAntinodes(upperPoint, -rowDif, colDif);
            addAntinodes(lowerPoint, rowDif, -colDif);
        }
    }

    private void addAntinodes(Point startPoint, int rowDif, int colDif) {
        for (int iteration = 1; iteration <= getNumberOfIterations(); iteration++) {
            int newRow = (int) startPoint.getX() + iteration * rowDif;
            int newCol = (int) startPoint.getY() + iteration * colDif;
            if (fitsMap(newRow, newCol)) {
                antinodes.add(new Point(newRow, newCol));
            } else {
                break;
            }
        }
    }

    protected abstract int getNumberOfIterations();

    private boolean fitsMap(int row, int col) {
        return row >= 0 && row < rowSize && col >= 0 && col < colSize;
    }

    @Override
    public Object getResult() {
        return antinodes.size();
    }
}
