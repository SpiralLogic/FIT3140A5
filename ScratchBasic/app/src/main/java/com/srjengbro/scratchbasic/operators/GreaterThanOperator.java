package com.srjengbro.scratchbasic.operators;


/**
 * @author      Sol Jennigns & Giles Browne
 * @description tests if 1 things is bigger than the other. Inherits from the Operator abstract
 *              class and handles the evaluation of any greater than operations
 */
public class GreaterThanOperator extends Operator {

    /**
     * constructor
     */
    public GreaterThanOperator() {
        name = "Greater Than";
        symbol = ">";
    }

    /**
     * @param lhs LHS operand
     * @param rhs RHS operand
     * @return 1 if lhs is bigger otherwise 0
     */
    @Override
    public Double evaluate(Double lhs, Double rhs) {
        if (lhs > rhs) {
            return 1.0;
        }
        return 0.0;
    }
}