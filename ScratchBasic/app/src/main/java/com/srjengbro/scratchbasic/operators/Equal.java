package com.srjengbro.scratchbasic.operators;

import android.util.Log;


public class Equal extends Operator {

    public Equal() {
        name = "Equal";
        symbol = "==";

    }

    @Override
    public Integer evaluate(Integer lhs, Integer rhs) {
        Log.d("LHS",lhs.toString());
        Log.d("RHS",rhs.toString());

        if (lhs.equals(rhs)) {
            return 1;
        }
        return 0;
    }
}
