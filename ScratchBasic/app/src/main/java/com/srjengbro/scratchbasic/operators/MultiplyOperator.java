package com.srjengbro.scratchbasic.operators;

/**
 * @author: Sol Jennings 26356015
 * @date: 16/05/16
 * @description multiplies the lhs with the rhs
 */
public class MultiplyOperator extends Operator {
    /**
     * constructor
     */
    public MultiplyOperator() {
        name = "Multiply";
        symbol = "*";
    }

    /**
     * @param lhs LHS operand
     * @param rhs RHS operand
     * @return the result of multiplying rhs to lhs
     */
    @Override
    public Double evaluate(Double lhs, Double rhs) {

        return lhs * rhs;
    }

}