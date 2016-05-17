package com.srjengbro.scratchbasic.operators;

/**
 * @author      Sol Jennigns & Giles Browne
 * @description subtracts rhs from lhs
 */
public class MinusOperator extends Operator {
    /**
     * constructor
     */
    public MinusOperator() {
        name = "Minus";
        symbol = "-";
    }

    /**
     * @param lhs LHS operand
     * @param rhs RHS operand
     * @return the result of subtracting rhs from lhs
     */
    @Override
    public Double evaluate(Double lhs, Double rhs) {

        return lhs - rhs;
    }

}
