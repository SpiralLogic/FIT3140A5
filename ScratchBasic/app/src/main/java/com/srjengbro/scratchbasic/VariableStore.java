package com.srjengbro.scratchbasic;

import java.util.TreeMap;

/**
 * Created by Maidenii on 29/04/16.
 */
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
    }

    public void delVariable(String name) {
        variables.remove(name);
    }
}
