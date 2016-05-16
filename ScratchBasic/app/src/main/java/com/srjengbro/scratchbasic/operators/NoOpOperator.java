package com.srjengbro.scratchbasic.operators;

/**
 * @author      Sol Jennigns & Giles Browne
 * @description represents no operation so an expression can have just a lhs
 *              Inherits from the Operator abstract
 *              class and handles the evaluation of any NoOpOperator operations
 */
public class NoOpOperator extends Operator {


    /**
     * constructor
     */
    public NoOpOperator() {
        name = "NoOpOperator";
        symbol = " ";
    }

    /**
     * @param lhs LHS operand
     * @param rhs RHS operand
     * @return just the lhs
     */
    @Override
    public Double evaluate(Double lhs, Double rhs) {
        return lhs;
    }
}
