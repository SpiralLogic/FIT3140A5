package com.srjengbro.scratchbasic;

import java.util.TreeMap;

/**
 * @author      Sol Jennings
 * @description
 */
public class VariableStore {
    /**
     *
     */
    private TreeMap<String, Variable> variables = new TreeMap<>();

    /**
     * @param name
     * @return
     */
    public Variable getVariable(String name) {
        return variables.get(name);
    }

    /**
     * @param name
     * @param value
     */
    public void setVariable(String name, Integer value) {
        Variable var;
        var = variables.get(name);
        if (var == null) {
            var = new Variable();
        }
        var.setValue(value);
        variables.put(name,var);
    }

    /**
     * @param name
     */
    public void delVariable(String name) {
        variables.remove(name);
    }

    /**
     *
     */
    public void clear() {
        variables = new TreeMap<>();
    }
}
