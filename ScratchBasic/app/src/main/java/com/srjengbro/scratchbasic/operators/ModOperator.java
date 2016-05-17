package com.srjengbro.scratchbasic.operators;

/**
 * @author: Sol Jennings 26356015
 * @date: 15/05/16
 * @description Modulo operator, performs modulo arithmetic
 */
public class ModOperator extends Operator {
    public ModOperator() {
        name = "Mod";
        symbol = "%";

    }

    /**
     * @param lhs LHS operand
     * @param rhs RHS operand
     * @return evaluate the modulo expression
     */
    @Override
    public Double evaluate(Double lhs, Double rhs) {
        return lhs % rhs;
    }
}



