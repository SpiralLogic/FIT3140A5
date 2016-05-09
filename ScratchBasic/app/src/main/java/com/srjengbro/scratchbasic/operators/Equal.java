package com.srjengbro.scratchbasic.operators;

import android.util.Log;

/**
 * @author      Sol Jennings
 * @description Equal operator, tests if 2 things are equal. Inherits from the Operator abstract
 *              class and handles the evaluation of any equals operations
 */
public class Equal extends Operator {

    /**
     * constructor
     */
    public Equal() {
        name = "Equal";
        symbol = "==";

    }

    /**
     * @param lhs LHS operand
     * @param rhs RHS operand
     * @return 1 if things are equal 0 otherwise
     */
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
