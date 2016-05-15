package com.srjengbro.scratchbasic.operators;

/**
 * @author      Sol Jennigns & Giles Browne
 * @description tests if 1 thing is less than another. Inherits from the Operator abstract
 *              class and handles the evaluation of any less than operations
 */
public class LessThanOperator extends Operator {
    /**
     * constructor
     */
    public LessThanOperator() {
        name = "LessThanOperator";
        symbol = "<";
    }

    /**
     * @param lhs LHS operand
     * @param rhs RHS operand
     * @return 1 if lhs < rhs otherwise 0
     */
    @Override
    public Integer evaluate(Integer lhs, Integer rhs) {
        if (lhs < rhs) {
            return 1;
        }
        return 0;
    }
}
