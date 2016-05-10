package com.srjengbro.scratchbasic.operators;

/**
 * @author      Sol Jennigns & Giles Browne
 * @description subtracts rhs from lhs
 */
public class Minus extends Operator {
    /**
     * constructor
     */
    public Minus() {
        name = "Minus";
        symbol = "-";
    }

    /**
     * @param lhs LHS operand
     * @param rhs RHS operand
     * @return the result of subtracting rhs from lhs
     */
    @Override
    public Integer evaluate(Integer lhs, Integer rhs) {

        return lhs - rhs;
    }

}
