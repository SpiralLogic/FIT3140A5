package com.srjengbro.scratchbasic.operators;

/**
 * Created by Maidenii on 29/04/16.
 */
public class GreaterThan extends Operator {

    public GreaterThan() {
        name = "GreaterThan";
        symbol = ">";
    }
    @Override
    public Integer evaluate(Integer lhs, Integer rhs) {
        if (lhs > rhs) {
            return 1;
        }
        return 0;
    }
}
