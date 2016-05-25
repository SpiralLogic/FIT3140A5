package com.srjengbro.scratchbasic;

import android.util.Log;

import java.io.Serializable;
import java.util.TreeMap;

/**
 * @author      Sol Jennigns & Giles Browne
 * @description Object class for storing and retrieving variables
 *              only integer variables are currently allowed
 */
public class VariableStore implements Serializable {
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
    public void setVariable(String name, Object value) throws VariableTypeException {
        Variable var;

        if (value instanceof Double) {
            var = new DoubleVariable(name);
            var.setValue(value);
        } else{
            throw new VariableTypeException(name + " is not a valid variable");
        }
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
