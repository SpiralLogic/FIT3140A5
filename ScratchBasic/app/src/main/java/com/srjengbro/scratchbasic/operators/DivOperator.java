package com.srjengbro.scratchbasic.operators;

/**
 * @author: Sol Jennings 26356015
 * @date: 15/05/16
 * @description Divison operator, divides the lhs by the rhs
 */
public class DivOperator extends Operator {
    public DivOperator() {
        name = "Div";
        symbol = "/";

    }

    /**
     * @param lhs LHS operand
     * @param rhs RHS operand
     * @return evaluate the expression
     */
    @Override
    public Double evaluate(Double lhs, Double rhs) {
        return lhs / rhs;
    }
}
