package com.srjengbro.scratchbasic;

import java.util.TreeMap;

/**
 * @author      Sol Jennigns & Giles Browne
 * @description Object class for storing and retrieving variables
 *              only integer variables are currently allowed
 */
public class VariableStore {
    /**
     * treemap for storing the variables by name
     */
    private TreeMap<String, Variable> variables = new TreeMap<>();

    /**
     * @param name get a variable by name
     * @return the variable value
     */
    public Variable getVariable(String name) {
        return variables.get(name);
    }

    /**
     * @param name set a variable value by name
     * @param value the value of the variable
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
     * @param name variable to delete
     */
    public void delVariable(String name) {
        variables.remove(name);
    }

    /**
     * clear all of the variables that are being stored
     */
    public void clear() {
        variables = new TreeMap<>();
    }
}
