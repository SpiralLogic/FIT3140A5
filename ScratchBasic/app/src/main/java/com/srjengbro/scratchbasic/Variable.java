package com.srjengbro.scratchbasic;

import java.io.Serializable;

/**
 * @author Sol Jenings on 15/05/2016.
 * @description abstract class for defining variable types
 */
public abstract class Variable implements Serializable {

    /**
     * value of variable
     */
    private Object value;
    /**
     * name of the current variable
     */
    private String name;

    /**
     * @param name constructor
     */
    public Variable(String name) {
        this.name = name;
    }

    /**
     * @return get the value of the variable
     */
    public abstract Object getValue();

    /**
     * @param value set the value of the variable
     */
    public abstract void setValue(Object value);

    /**
     * @return the name of the variable
     */
    public String getName() {
        return name;
    }

    /**
     * @return Convert variable to string
     */
    public String toString() {
        return this.value.toString();
    }

}
