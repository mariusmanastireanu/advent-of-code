package org.advent._2023.day19.model;

import lombok.Data;

@Data
public class Part {

    private final int x;
    private final int m;
    private final int a;
    private final int s;

    public Part(String part) {
        part = part.replace("\\{", "").replace("}", "");
        x = Integer.parseInt(part.split("x=")[1].substring(0, part.split("x=")[1].indexOf(",")));
        m = Integer.parseInt(part.split("m=")[1].substring(0, part.split("m=")[1].indexOf(",")));
        a = Integer.parseInt(part.split("a=")[1].substring(0, part.split("a=")[1].indexOf(",")));
        s = Integer.parseInt(part.split("s=")[1]);
    }

    public int getValue() {
        return x + m + a + s;
    }

}
