package com.srjengbro.scratchbasic;

/**
 * Created by Maidenii on 29/04/16.
 */
public class GreaterThan extends Operator {
    @Override
    public int evaluate(int lhs, int rhs) {
        if (lhs > rhs) {
            return 1;
        }
        return 0;
    }
}
