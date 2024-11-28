package org.advent._2023.day20.model;

public class StartModule implements Module {

    @Override
    public void processPulseFromModule(Module module, Pulse pulse) {
        // do nothing
    }

    @Override
    public Pulse sendPulse() {
        return Pulse.LOW;
    }

    @Override
    public String getName() {
        return "start";
    }
}
