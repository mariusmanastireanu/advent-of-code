package org.advent._2023.day2;

import org.advent.helper.InputReader;

public class Day2 {

    private void part1() {
        var bag = Bag.builder().red(12).green(13).blue(14).build();
        var result = (Integer) InputReader.readFile("_2023/day2.txt")
                .stream()
                .filter(line -> canPlay(line, bag))
                .map(this::extractId)
                .mapToInt(Integer::intValue)
                .sum();
        System.out.println(result);
    }

    private boolean canPlay(String line, Bag inputBag) {
        var groups = line.split(":")[1].split(";");
        for (String group : groups) {
            var colors = group.trim().split(",");
            for (String colorEntry : colors) {
                var number = Integer.parseInt(colorEntry.trim().split(" ")[0].trim());
                var color = colorEntry.trim().split(" ")[1].trim();
                if (!isColorValid(inputBag, color, number)) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean isColorValid(Bag inputBag, String color, int number) {
        if ("red".equals(color) && number > inputBag.getRed()
            || "blue".equals(color) && number > inputBag.getBlue()
            || "green".equals(color) && number > inputBag.getGreen()) {
            return false;
        }
        return true;
    }

    private int extractId(String line) {
        return Integer.parseInt(line.split(":")[0].split(" ")[1]);
    }

    private void part2() {
        var result = (Integer) InputReader.readFile("_2023/day2.txt")
                .stream()
                .map(this::extractMinimumBag)
                .mapToInt(this::computePower)
                .sum();
        System.out.println(result);
    }

    private Bag extractMinimumBag(String line) {
        var bag = new Bag(0,0,0);
        var groups = line.split(":")[1].split(";");
        for (String group : groups) {
            var colors = group.trim().split(",");
            for (String colorEntry : colors) {
                var number = Integer.parseInt(colorEntry.trim().split(" ")[0].trim());
                var color = colorEntry.trim().split(" ")[1].trim();
                adjustMinimumBag(color, number, bag);
            }
        }
        return bag;
    }

    private static void adjustMinimumBag(String color, int number, Bag bag) {
        if ("red".equals(color) && number > bag.getRed()) {
            bag.setRed(number);
        } else if ("blue".equals(color) && number > bag.getBlue()) {
            bag.setBlue(number);
        } else if ("green".equals(color) && number > bag.getGreen()) {
            bag.setGreen(number);
        }
    }

    private int computePower(Bag bag) {
        return bag.getBlue() * bag.getGreen() * bag.getRed();
    }

    public static void main(String[] args) {
        var problemSolver = new Day2();
        problemSolver.part1();
        problemSolver.part2();
    }
}
