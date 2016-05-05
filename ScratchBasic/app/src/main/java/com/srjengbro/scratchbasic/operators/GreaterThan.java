package com.srjengbro.scratchbasic.operators;


/**
 * @author      Sol Jennings
 * @description tests if 1 things is bigger than the other
 */
public class GreaterThan extends Operator {

    /**
     * constructor
     */
    public GreaterThan() {
        name = "GreaterThan";
        symbol = ">";
    }

    /**
     * @param lhs LHS operand
     * @param rhs RHS operand
     * @return 1 if lhs is bigger otherwise 0
     */
    @Override
    public Integer evaluate(Integer lhs, Integer rhs) {
        if (lhs > rhs) {
            return 1;
        }
        return 0;
    }
}
