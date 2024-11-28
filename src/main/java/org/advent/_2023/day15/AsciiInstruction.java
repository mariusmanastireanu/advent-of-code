package org.advent._2023.day15;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AsciiInstruction {

    private final String instruction;

    @Override
    public int hashCode() {
        var currentValue = 0;
        for (int i = 0; i < instruction.length(); i++) {
            char c = instruction.charAt(i);
            currentValue += c;
            currentValue *= 17;
            currentValue %= 256;
        }
        return currentValue;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof AsciiInstruction) {
            return instruction.equals(((AsciiInstruction) obj).instruction);
        }
        return false;
    }

}
