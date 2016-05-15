package com.srjengbro.scratchbasic.operators;

/**
 * @author: Sol Jennings 26356015
 * @date: 15/05/16
 * @description Modulo operator, performs modulo arithmatic
 */
public class Mod extends Operator {
    @Override
    public Integer evaluate(Integer lhs, Integer rhs) {
        return lhs % rhs;
    }
}
