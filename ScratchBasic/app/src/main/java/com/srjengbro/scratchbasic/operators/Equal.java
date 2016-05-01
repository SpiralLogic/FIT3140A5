package com.srjengbro.scratchbasic.operators;

/**
 * Created by Maidenii on 29/04/16.
 */
public class Equal extends Operator {

    public Equal() {
        name = "Equal";
        symbol = "==";

    }

    @Override
    public Integer evaluate(Integer lhs, Integer rhs) {
        if (lhs.equals(rhs)) {
            return 1;
        }
        return 0;
    }
}
