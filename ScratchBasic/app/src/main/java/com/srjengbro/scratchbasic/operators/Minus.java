package com.srjengbro.scratchbasic.operators;


public class Minus extends Operator {
    public Minus() {
        name = "Minus";
        symbol = "-";
    }

    @Override
    public Integer evaluate(Integer lhs, Integer rhs) {

        return lhs - rhs;
    }

}
