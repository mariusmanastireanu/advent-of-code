package org.advent._2023.day19.model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Rule {

    private final List<Condition> conditions = new ArrayList<>();

    public Rule(String rule) {
        rule = rule.replace("\\{", "").replace("}", "");
        for (String condition : rule.split(",")) {
            conditions.add(new Condition(condition));
        }
    }

    public String apply(final Part part) {
        for (int i = 0; i < conditions.size(); i++) {
            var condition = conditions.get(i);
            if (i == conditions.size() - 1) {
                return condition.getDestination();
            } else {
                var destination = condition.apply(part);
                if (destination != null) {
                    return destination;
                }
            }
        }
        return null;
    }

}
