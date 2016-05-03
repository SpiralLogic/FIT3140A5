package com.srjengbro.scratchbasic.operators;

/**
 * @author: Sol Jennings 26356015
 * @date: 2/05/16
 */
public class NoOp extends Operator {


    public NoOp() {
        name = "NoOp";
        symbol = " ";
    }

    @Override
    public Integer evaluate(Integer lhs, Integer rhs) {
        return 0;
    }
}
