package com.srjengbro.scratchbasic;

import java.io.Serializable;

/**
 * @author      Sol Jennigns & Giles Browne
 * @description Class for storing a single variable. Variables are stored within a
 *              Variable store object.
 */
public class Variable implements Serializable {
    /**
     * name of the current variable
     */
    private String name;
    /**
     * value of variable
     */
    private Integer value;

    /**
     * @param name constructor
     */
    public Variable(String name) {
        this.name = name;
    }

    /**
     * @return get the value of the variable
     */
    public Integer getValue() {
        return this.value;
    }

    /**
     * @param value set the value of the variable
     */
    public void setValue(Integer value) {
        this.value = value;
    }

    /**
     * @return the name of the variable
     */
    public String getName() {
        return name;
    }
}
