package org.advent.day19.model;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@ToString
@NoArgsConstructor
public class Combination {

    private long minX = 1;
    private long maxX = 4000;

    private long minM = 1;
    private long maxM = 4000;

    private long minA = 1;
    private long maxA = 4000;

    private long minS = 1;
    private long maxS = 4000;

    public Combination(Combination other) {
        this.minX = other.minX;
        this.maxX = other.maxX;
        this.minM = other.minM;
        this.maxM = other.maxM;
        this.minA = other.minA;
        this.maxA = other.maxA;
        this.minS = other.minS;
        this.maxS = other.maxS;
    }

    public long getNumberOfCombinations() {
        return (maxX - minX + 1)
                * (maxM - minM + 1)
                * (maxA - minA + 1)
                * (maxS - minS + 1);
    }

}
