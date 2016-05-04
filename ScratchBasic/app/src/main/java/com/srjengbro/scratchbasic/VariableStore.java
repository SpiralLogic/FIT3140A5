package com.srjengbro.scratchbasic;

import java.util.TreeMap;


public class VariableStore {
    private TreeMap<String, Variable> variables = new TreeMap<>();

    public Variable getVariable(String name) {
        return variables.get(name);
    }

    public void setVariable(String name, Integer value) {
        Variable var;
        var = variables.get(name);
        if (var == null) {
            var = new Variable();
        }
        var.setValue(value);
        variables.put(name,var);
    }

    public void delVariable(String name) {
        variables.remove(name);
    }
    public void clear() {
        variables = new TreeMap<>();
    }
}
