package com.srjengbro.scratchbasic.operators;

/**
 * @author      Sol Jennigns & Giles Browne
 * @description represents no operation so an expression can have just a lhs
 *              Inherits from the Operator abstract
 *              class and handles the evaluation of any NoOp operations
 */
public class NoOp extends Operator {


    /**
     * constructor
     */
    public NoOp() {
        name = "NoOp";
        symbol = " ";
    }

    /**
     * @param lhs LHS operand
     * @param rhs RHS operand
     * @return just the lhs
     */
    @Override
    public Integer evaluate(Integer lhs, Integer rhs) {
        return lhs;
    }
}
