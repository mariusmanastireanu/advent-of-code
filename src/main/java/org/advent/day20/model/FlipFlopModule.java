package org.advent.day20.model;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FlipFlopModule implements Module {

    private final String name;
    private State state = State.OFF;
    private Pulse pulseToSend = null;
    private Pulse lastReceived = null;

    @Override
    public void processPulseFromModule(Module module, Pulse pulse) {
        lastReceived = pulse;
        if (Pulse.LOW.equals(pulse)) {
            if (state == State.OFF) {
                state = State.ON;
                pulseToSend = Pulse.HIGH;
            } else {
                state = State.OFF;
                pulseToSend = Pulse.LOW;
            }
        }
    }

    @Override
    public Pulse sendPulse() {
        return Pulse.HIGH.equals(lastReceived) ? null : pulseToSend;
    }

    @Override
    public String getName() {
        return name;
    }

    private enum State {ON,OFF}
}
