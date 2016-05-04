package com.srjengbro.scratchbasic.operators;


public class Plus extends Operator {


    public Plus() {
        name = "Plus";
        symbol = "+";
    }
    @Override
    public Integer evaluate(Integer lhs, Integer rhs) {
        return lhs + rhs;
    }
}
