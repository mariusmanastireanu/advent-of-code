package org.advent.day19;

import org.advent.day19.model.Part;

public class Part1Solver extends Day19Solver {
    @Override
    protected void processPart(String line) {
        var part = new Part(line);
        var ruleKey = "in";
        while (true) {
            ruleKey = rulesMap.get(ruleKey).apply(part);
            if ("A".equals(ruleKey)) {
                result += part.getValue();
                break;
            } else if ("R".equals(ruleKey)) {
                break;
            }
        }
    }
}
