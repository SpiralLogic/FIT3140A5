package com.srjengbro.scratchbasic.operators;

/**
 * @author      Sol Jennigns & Giles Browne
 * @description Adds the lhs to the rhs. Inherits from the Operator abstract
 *              class and handles the evaluation of any plus operation
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
