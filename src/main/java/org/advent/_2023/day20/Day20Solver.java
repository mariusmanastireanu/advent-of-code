package org.advent._2023.day20;

import org.advent._2023.day20.model.*;
import org.advent._2023.day20.model.Module;
import org.advent.helper.AbstractSolver;

import java.util.*;

public abstract class Day20Solver extends AbstractSolver {

    private static final String BROADCASTER = "broadcaster";

    protected long result = 0L;

    protected final Map<Module, List<String>> modules = new HashMap<>();
    protected final Map<String, Module> namesToModulesMap = new HashMap<>();
    protected final Queue<Instruction> processingQueue = new LinkedList<>();

    protected long lowSignals = 0L;
    protected long highSignals = 0L;

    @Override
    public Object getResult() {
        return result;
    }

    protected void processInput(Collection<String> lines) {
        for (String line : lines) {
            var name = line.startsWith(BROADCASTER)
                    ? BROADCASTER
                    : line.split("->")[0].trim().substring(1);
            var module = createModule(line, name);
            modules.put(module, extractDestinations(line));
            namesToModulesMap.put(name, module);
        }
        var startModule = new StartModule();
        modules.put(startModule, List.of(BROADCASTER));
        namesToModulesMap.put("start", startModule);

        for (Module module : modules.keySet()) {
            if (module instanceof ConjunctionModule) {
                for (Map.Entry<Module, List<String>> entry : modules.entrySet()) {
                    if (entry.getValue().contains(module.getName())) {
                        module.processPulseFromModule(entry.getKey(), Pulse.LOW);
                    }
                }
            }
        }
    }

    private Module createModule(String line, String name) {
        final Module module;
        if (line.startsWith(BROADCASTER)) {
            module = new BroadcasterModule();
        } else if (line.startsWith("%")) {
            module = new FlipFlopModule(name);
        } else if (line.startsWith("&")){
            module = new ConjunctionModule(name);
        } else {
            module = null;
        }
        return module;
    }

    private List<String> extractDestinations(String line) {
        var tokens = line.split("->")[1].split(",");
        var modulesList = new ArrayList<String>();
        for (String token : tokens) {
            modulesList.add(token.trim());
        }
        return modulesList;
    }

    protected void performIteration() {
        processingQueue.add(new Instruction("start", Pulse.LOW));
        while (!processingQueue.isEmpty()) {
            var instruction = processingQueue.poll();
            var pulseToSend = instruction.getPulse();
            if (pulseToSend == null) {
                continue;
            }
            var source = namesToModulesMap.get(instruction.getName());
            for (String destination : modules.get(source)) {
                var module = namesToModulesMap.get(destination);
                if (module != null) {
                    module.processPulseFromModule(source, pulseToSend);
                    processingQueue.add(new Instruction(destination, module.sendPulse()));
                }
                notifyPulseSent(destination, pulseToSend);
            }
        }
    }

    protected abstract void notifyPulseSent(String destination, Pulse pulse);

}
