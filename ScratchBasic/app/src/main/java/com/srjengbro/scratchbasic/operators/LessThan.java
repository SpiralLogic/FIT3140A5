package com.srjengbro.scratchbasic.operators;


public class LessThan extends Operator {
    public LessThan() {
        name = "LessThan";
        symbol = "<";
    }
    @Override
    public Integer evaluate(Integer lhs, Integer rhs) {
        if (lhs < rhs) {
            return 1;
        }
        return 0;
    }
}
