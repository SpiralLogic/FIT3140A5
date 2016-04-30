package com.srjengbro.scratchbasic;

/**
 * Created by Maidenii on 29/04/16.
 */
public class LessThan extends Operator {
    @Override
    public Integer evaluate(Integer lhs, Integer rhs) {
        if (lhs < rhs) {
            return 1;
        }
        return 0;
    }
}
