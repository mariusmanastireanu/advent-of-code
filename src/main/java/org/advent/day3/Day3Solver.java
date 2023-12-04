package org.advent.day3;

import org.advent.helper.AbstractSolver;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public abstract class Day3Solver extends AbstractSolver {

    public Day3Solver(String filename) {
        super(filename);
    }

    protected abstract void resolve(List<List<Character>> map, Integer line, Integer colStart, Integer colEnd, Integer number);

    @Override
    public void solve(Collection<String> lines) {
        final List<List<Character>> charMatrix = new ArrayList<>();
        for (String line : lines) {
            charMatrix.add(new ArrayList<>(line.chars().mapToObj(c -> (char) c).collect(Collectors.toList())));
        }
        for (int line = 0; line < charMatrix.size(); line++) {
            var columnStart = -1;
            var number = -1;
            for (int column = 0; column < charMatrix.get(line).size(); column++) {
                var currentChar = charMatrix.get(line).get(column);
                if (Character.isDigit(currentChar)) {
                    if (columnStart == -1) {
                        columnStart = column;
                    }
                    if (number == -1) {
                        number = Character.getNumericValue(currentChar);
                    } else {
                        if (number == 0) {
                            number = 1;
                        }
                        number = number * 10 + Character.getNumericValue(currentChar);
                    }
                } else {
                    if (number != -1) {
                        resolve(charMatrix, line, columnStart, column - 1, number);
                    }
                    columnStart = -1;
                    number = -1;
                }
            }
            if (number != -1) {
                resolve(charMatrix, line, columnStart, charMatrix.get(line).size() - 1, number);
            }
        }
    }

    public Point checkNeighboursOfNumber(List<List<Character>> charMatrix, int line, int columnStart, int columnEnd,
                                         Predicate<Character> checker) {
        Point point = null;
        if (columnStart > 0) columnStart--;
        if (columnEnd < charMatrix.get(line).size() - 1) columnEnd++;
        if (line > 0) {
            line--;
            point = checkNeighbour(charMatrix, line, columnStart, columnEnd, checker);
            if (point != null) return point;
            line++;
            point = checkLeftRightNeighbour(charMatrix, line, columnStart, columnEnd, checker);
            if (point != null) return point;
        } else {
            point = checkLeftRightNeighbour(charMatrix, line, columnStart, columnEnd, checker);
            if (point != null) return point;
        }
        if (line < charMatrix.size() - 1) {
            line++;
            return checkNeighbour(charMatrix, line, columnStart, columnEnd, checker);
        }
        return null;
    }

    private Point checkNeighbour(List<List<Character>> charMatrix, int line, int columnStart, int columnEnd, Predicate<Character> checker) {
        for (int i = columnStart; i <= columnEnd; i++) {
            var ch = charMatrix.get(line).get(i);
            if (checker.test(ch)) {
                return Point.builder().x(line).y(i).build();
            }
        }
        return null;
    }

    private Point checkLeftRightNeighbour(List<List<Character>> charMatrix, int line, int columnStart, int columnEnd, Predicate<Character> checker) {
        if (checker.test(charMatrix.get(line).get(columnStart))) {
            return Point.builder().x(line).y(columnStart).build();
        } else if (checker.test(charMatrix.get(line).get(columnEnd))) {
            return Point.builder().x(line).y(columnEnd).build();
        }
        return null;
    }

}
