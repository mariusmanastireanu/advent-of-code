package org.advent._2023.day5;

import lombok.Data;

@Data
public class AlmanacMap {

    private final long destinationStart;
    private final long sourceStart;
    private final long range;

    public AlmanacMap(String line) {
        var tokens = line.split(" ");
        destinationStart = Long.parseLong(tokens[0]);
        sourceStart = Long.parseLong(tokens[1]);
        range = Long.parseLong(tokens[2]);
    }

    public boolean isInRange(long source) {
        return source <= sourceStart + range && source >= sourceStart;
    }

    public long getDestination(long source) {
        return destinationStart + (source - sourceStart);
    }

}
