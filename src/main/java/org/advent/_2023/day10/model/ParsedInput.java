package org.advent._2023.day10.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ParsedInput {

    private final List<List<Character>> matrix = new ArrayList<>();
    private int startingRowIndex;
    private int startingColIndex;

    public ParsedInput(java.util.Collection<String> lines) {
        for (String line : lines) {
            matrix.add(line.chars().mapToObj(c -> (char) c).collect(java.util.stream.Collectors.toList()));
            if (line.contains("S")) {
                startingRowIndex = matrix.size() - 1;
                startingColIndex = line.indexOf("S");
            }
        }
    }

}
