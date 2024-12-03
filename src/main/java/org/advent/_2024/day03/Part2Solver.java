package org.advent._2024.day03;

import java.util.Collection;

public class Part2Solver extends Day03Solver {

    @Override
    public void solve(final Collection<String> lines) {
        var newLine = new StringBuilder();
        lines.forEach(newLine::append);
        processString(newLine.toString());
    }

    @Override
    protected void processString(String line) {
        var processing = true;
        while (!line.isEmpty()) {
            if (processing) {
                var indexOfDont = line.contains("don't()") ? line.indexOf("don't()") : line.length();
                super.processString(line.substring(0, indexOfDont));
                line = line.substring(indexOfDont);
                processing = false;
            } else {
                var indexOfDo = line.contains("do()") ? line.indexOf("do()") : line.length();
                line = line.substring(indexOfDo);
                processing = true;
            }
        }
    }
}
