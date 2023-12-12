package org.advent.day12;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class ConditionRecord {

    private String condition;
    private List<Integer> brokenRecordGroups;

    public ConditionRecord(String line) {
        this.condition = line.split(" ")[0];
        this.brokenRecordGroups = Arrays.stream(line.split(" ")[1].trim().split(","))
                .filter(s -> !s.isEmpty())
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    public void expandBrokenRecordGroups() {
        var conditionBuilder = new StringBuilder();
        List<Integer> expandedBrokenRecordGroups = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            conditionBuilder.append(condition).append("?");
            expandedBrokenRecordGroups.addAll(brokenRecordGroups);
        }
        condition = conditionBuilder.substring(0, conditionBuilder.length() - 1);
        brokenRecordGroups = expandedBrokenRecordGroups;
    }

}
