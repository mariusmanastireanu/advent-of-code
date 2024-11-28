package org.advent._2023.day5;

import java.util.stream.LongStream;
import java.util.stream.Stream;

public class Part2Solver extends Day5Solver {

    @Override
    protected Stream<Long> extractSeeds(String line) {
        var seeds = line.split(":")[1].trim().split(" ");
        var startingRange = 0L;
        LongStream previousStream = null;
        for (int i = 0; i < seeds.length; i++) {
            if (i % 2 == 0) {
                startingRange = Long.parseLong(seeds[i]);
            } else {
                var currentStream = LongStream.range(startingRange, startingRange + Long.parseLong(seeds[i]));
                previousStream = previousStream == null ? currentStream : LongStream.concat(previousStream, currentStream);
            }
        }
        return previousStream.boxed();
    }

}
