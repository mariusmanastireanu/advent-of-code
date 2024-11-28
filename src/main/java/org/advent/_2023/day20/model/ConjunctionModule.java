package org.advent._2023.day20.model;

import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public class ConjunctionModule implements Module {

    private final String name;
    private final Map<String, Pulse> inputs = new HashMap<>();

    @Override
    public void processPulseFromModule(Module module, Pulse pulse) {
        inputs.put(module.getName(), pulse);
    }

    @Override
    public Pulse sendPulse() {
        if (inputs.values().stream().anyMatch(pulse -> pulse == Pulse.LOW)) {
            return Pulse.HIGH;
        } else {
            return Pulse.LOW;
        }
    }

    @Override
    public String getName() {
        return name;
    }

}
