package org.advent._2023.day6;

public class Part2Solver extends Day6Solver {


    @Override
    protected String getNumbersStringFromLine(String line) {
        return line.split(":")[1].trim().replace(" ", "");
    }

}
