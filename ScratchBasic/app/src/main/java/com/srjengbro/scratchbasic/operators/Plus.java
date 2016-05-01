package com.srjengbro.scratchbasic.operators;

/**
 * Created by Maidenii on 29/04/16.
 */
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
