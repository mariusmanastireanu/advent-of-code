package org.advent.day20.model;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OutputModule implements Module {

    private final String name;

    @Override
    public void processPulseFromModule(Module module, Pulse pulse) {
        // do nothing
    }

    @Override
    public Pulse sendPulse() {
        return null;
    }

    @Override
    public String getName() {
        return name;
    }
}
