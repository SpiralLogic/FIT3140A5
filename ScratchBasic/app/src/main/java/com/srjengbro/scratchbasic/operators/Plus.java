package com.srjengbro.scratchbasic.operators;

/**
 * @author      Sol Jennings
 * @description adds the lhs to the rhs
 */
public class Plus extends Operator {


    /**
     * constructor
     */
    public Plus() {
        name = "Plus";
        symbol = "+";
    }

    /**
     * @param lhs LHS operand
     * @param rhs RHS operand
     * @return returns the result of the lhs + rhs
     */
    @Override
    public Integer evaluate(Integer lhs, Integer rhs) {
        return lhs + rhs;
    }
}
