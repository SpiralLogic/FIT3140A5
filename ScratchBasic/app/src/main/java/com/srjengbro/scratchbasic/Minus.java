package com.srjengbro.scratchbasic;

/**
 * Created by Maidenii on 29/04/16.
 */
public class Minus extends Operator {
    @Override
    public int evaluate(int lhs, int rhs) {
        return lhs - rhs;
    }
}