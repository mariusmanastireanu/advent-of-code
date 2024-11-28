package org.advent._2023.day20.model;

public class BroadcasterModule implements Module {

    private Pulse pulseToSend = Pulse.LOW;

    @Override
    public void processPulseFromModule(Module module, Pulse pulse) {
        this.pulseToSend = pulse;
    }

    @Override
    public Pulse sendPulse() {
        return pulseToSend;
    }

    @Override
    public String getName() {
        return "broadcaster";
    }
}
