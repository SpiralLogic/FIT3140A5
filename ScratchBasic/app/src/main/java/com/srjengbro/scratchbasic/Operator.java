package com.srjengbro.scratchbasic;

/**
 * Created by Maidenii on 29/04/16.
 */
public abstract class Operator {
    protected String name;
    protected String symbol;

    public abstract int evaluate(int lhs, int rhs);

}
