package com.srjengbro.scratchbasic;

/**
 * @author      Sol Jennings
 * @description object for storing a single variable
 */
public class Variable {
    /**
     * name of the current variable
     */
    private String name;
    /**
     * value of variable
     */
    private Integer value;

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
