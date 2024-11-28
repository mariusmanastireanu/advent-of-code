package org.advent._2023.day20.model;

public interface Module {

    void processPulseFromModule(Module module, Pulse pulse);
    Pulse sendPulse();
    String getName();

}
