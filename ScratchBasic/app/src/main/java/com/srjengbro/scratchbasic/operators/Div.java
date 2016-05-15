package com.srjengbro.scratchbasic.operators;

/**
 * @author: Sol Jennings 26356015
 * @date: 15/05/16
 * @description Divison operator, divides the lhs by the rhs
 */
public class Div extends Operator {
    @Override
    public Integer evaluate(Integer lhs, Integer rhs) {
        return lhs / rhs;
    }
}
