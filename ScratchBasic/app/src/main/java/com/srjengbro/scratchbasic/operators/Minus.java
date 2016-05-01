package com.srjengbro.scratchbasic.operators;

/**
 * Created by Maidenii on 29/04/16.
 */
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
