package com.srjengbro.scratchbasic.operators;

/**
 * @author      Sol Jennings
 * @description abstract operator class that all operations extend from
 */
public abstract class Operator implements java.io.Serializable {
    /**
     * name of the operator
     */
    protected String name;
    /**
     * symbol of the operator
     */
    protected String symbol;

    /**
     * @param lhs LHS operand
     * @param rhs RHS operand
     * @return result of the operation
     */
    public abstract Integer evaluate(Integer lhs, Integer rhs);

    /**
     * @return the symbol of the operation
     */
    public String getSymbol() {
        return symbol;
    }
}
