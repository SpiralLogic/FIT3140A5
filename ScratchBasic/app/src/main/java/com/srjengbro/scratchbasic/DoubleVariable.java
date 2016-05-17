package com.srjengbro.scratchbasic;

/**
 * @author      Sol Jennigns & Giles Browne
 * @description Class for storing a single variable. Variables are stored within a
 *              DoubleVariable store object.
 */
public class DoubleVariable extends Variable {
    /**
     * name of the current variable
     */
    private String name;
    /**
     * value of variable
     */
    private Double value;

    /**
     * @param name constructor
     */
    public DoubleVariable(String name) {
        super(name);
        this.name = name;
    }

    /**
     * @return get the value of the variable
     */
    public Double getValue() {
        return this.value;
    }

    /**
     * @param value set the value of the variable
     */
    @Override
    public void setValue(Object value) {
        setValue((Double) value);
    }

    /**
     * @return the name of the variable
     */
    public String getName() {
        return name;
    }

    /**
     * @param value set the value of the variable
     */
    public void setValue(Double value) {
        this.value = value;
    }
}
