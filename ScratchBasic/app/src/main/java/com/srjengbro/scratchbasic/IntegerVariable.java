package com.srjengbro.scratchbasic;

/**
 * @author      Sol Jennigns & Giles Browne
 * @description Class for storing a single variable. Variables are stored within a
 *              IntegerVariable store object.
 */
public class IntegerVariable extends Variable {
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
    public IntegerVariable(String name) {
        super(name);
        this.name = name;
    }

    /**
     * @return get the value of the variable
     */
    public Integer getValue() {
        return this.value;
    }

    @Override
    public void setValue(Object value) {
        setValue((Integer) value);
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
