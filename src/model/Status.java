package model;

import java.util.EnumMap;
import java.util.Map;

public class Status {
    private final Map<Atributo, Integer> atributos = new EnumMap<>(Atributo.class);

    public Status(Map<Atributo, Integer> iniciais) {
        atributos.putAll(iniciais);
    }

    public int get(Atributo a) {
        return atributos.getOrDefault(a, 10);
    }

    public void aumentar(Atributo a) {
        atributos.put(a, get(a) + 1);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("=== STATUS ===\n");
        for (Atributo a : Atributo.values()) {
            sb.append(String.format("%-12s: %d\n", a, get(a)));
        }
        return sb.toString();
    }
}