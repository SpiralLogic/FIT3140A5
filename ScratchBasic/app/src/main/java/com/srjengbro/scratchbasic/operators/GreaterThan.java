package com.srjengbro.scratchbasic.operators;

import android.util.Log;


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
