package com.srjengbro.scratchbasic.operators;

/**
 * @author      Sol Jennigns & Giles Browne
 * @description Abstract operator class that all operations extend from and defines the methods
 *              than any operator must implement.
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
    public abstract Double evaluate(Double lhs, Double rhs);

    /**
     * @return the symbol of the operation
     */
    public String getSymbol() {
        return symbol;
    }
}
