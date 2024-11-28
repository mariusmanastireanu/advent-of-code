package org.advent._2023.day13.model;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractPattern {

    protected List<String> patternMatrix = new ArrayList<>();
    protected int reflectionIndex = -1;

    protected AbstractPattern(List<String> lines) {
        patternMatrix.addAll(lines);
    }

    public long solvePattern() {
        if (solveHorizontal()) {
            return 100L * reflectionIndex;
        }
        solveVertical();
        return reflectionIndex;
    }

    protected boolean solveHorizontal() {
        return isMirror(computeReflexionIndices());
    }

    protected boolean solveVertical() {
        invertMatrix();
        return isMirror(computeReflexionIndices());
    }

    private List<Integer> computeReflexionIndices() {
        var collection = new ArrayList<Integer>();
        for (int i = 0; i < patternMatrix.size() - 1; i++) {
            var index = computeReflexionIndex(patternMatrix.get(i), patternMatrix.get(i + 1), i);
            if (index != -1) {
                collection.add(index);
            }
        }
        return collection;
    }

    protected int computeReflexionIndex(String line, String anotherLine, int index) {
        if (line.equals(anotherLine)) {
            return index;
        }
        return -1;
    }

    private boolean isMirror(List<Integer> reflectionIndices) {
        if (reflectionIndices.isEmpty()) {
            return false;
        }
        for (int currentReflectionIndex : reflectionIndices) {
            if (isMirror(currentReflectionIndex)) {
                this.reflectionIndex = currentReflectionIndex + 1;
                return true;
            }
        }
        return false;
    }

    private boolean isMirror(int firstReflectionIndex) {
        var smudgeCopy = new ArrayList<Integer>();
        var offset = 0;
        var lowerIndex = firstReflectionIndex - offset;
        var upperIndex = firstReflectionIndex + offset + 1;
        while (lowerIndex >= 0 && upperIndex < patternMatrix.size()) {
            if (!compareLines(patternMatrix.get(lowerIndex), patternMatrix.get(upperIndex),
                    lowerIndex, upperIndex,
                    smudgeCopy, firstReflectionIndex)) {
                return false;
            }
            offset++;
            lowerIndex = firstReflectionIndex - offset;
            upperIndex = firstReflectionIndex + offset + 1;
        }
        return isMirror(smudgeCopy, firstReflectionIndex);
    }

    protected boolean isMirror(List<Integer> smudgeCopy, int firstReflectionIndex) {
        return true;
    }

    protected boolean compareLines(String line, String anotherLine, int low, int up, List<Integer> smudgedIndices, int smudgeIndex) {
        return line.equals(anotherLine);
    }

    private void invertMatrix() {
        List<String> invertedMatrix = new ArrayList<>();
        for (String line : patternMatrix) {
            for (int i = 0; i < line.length(); i++) {
                if (invertedMatrix.size() < patternMatrix.get(0).length() && i <= invertedMatrix.size()) {
                    invertedMatrix.add(i, "");
                }
                invertedMatrix.set(i, invertedMatrix.get(i) + line.charAt(i));
            }
        }
        patternMatrix = invertedMatrix;
    }

}
