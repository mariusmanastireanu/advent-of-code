package org.advent.day20.model;

public interface Module {

    void processPulseFromModule(Module module, Pulse pulse);
    Pulse sendPulse();
    String getName();

}
