package org.advent.day13.model;

import java.util.ArrayList;
import java.util.List;

public class PatternPart2 extends AbstractPattern {

    private final List<Integer> smudgedIndices = new ArrayList<>();

    public PatternPart2(List<String> lines) {
        super(lines);
    }

    @Override
    protected boolean solveVertical() {
        smudgedIndices.clear();
        return super.solveVertical();
    }

    @Override
    protected int computeReflexionIndex(String line, String anotherLine, int index) {
        if (super.computeReflexionIndex(line, anotherLine, index) != -1) {
            return index;
        } else {
            return comparePotentiallySmudgedLines(line, anotherLine, index, smudgedIndices);
        }
    }

    private Integer comparePotentiallySmudgedLines(String line, String anotherLine, int index, List<Integer> smudgedIndices) {
        var count = 0;
        for (int charIndex = 0; charIndex < line.length(); charIndex++) {
            if (line.charAt(charIndex) != anotherLine.charAt(charIndex)) {
                count++;
                if (count > 1) {
                    return -1;
                }
            }
        }
        if (count == 1) {
            smudgedIndices.add(index);
            return index;
        }
        return -1;
    }

    @Override
    protected boolean isMirror(List<Integer> smudgeCopy, int firstReflectionIndex) {
        return smudgedIndices.contains(firstReflectionIndex) || !smudgeCopy.isEmpty();
    }

    @Override
    protected boolean compareLines(String line, String anotherLine, int low, int up, List<Integer> smudgedIndices, int smudgeIndex) {
        if (line.equals(anotherLine)) {
            return true;
        } else if (this.smudgedIndices.contains(smudgeIndex) && (up-low ==1)) {
            smudgedIndices.addAll(this.smudgedIndices);
            return true;
        } else if (smudgedIndices.isEmpty()) {
            return comparePotentiallySmudgedLines(line, anotherLine, smudgeIndex, smudgedIndices) != -1;
        }
        return false;
    }

}
