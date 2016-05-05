package com.srjengbro.scratchbasic.operators;

/**
 * @author      Sol Jennings
 * @description tests if 1 thing is less than another
 */
public class LessThan extends Operator {
    /**
     * constructor
     */
    public LessThan() {
        name = "LessThan";
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
