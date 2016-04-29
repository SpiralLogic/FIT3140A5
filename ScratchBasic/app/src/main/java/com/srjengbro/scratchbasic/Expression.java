package com.srjengbro.scratchbasic;

/**
 * Created by Maidenii on 29/04/16.
 */
public class Expression {

    private String lhs;
    private String rhs;
    private Operator operator;

    public Expression(Operator op, String lhs, String rhs) {
        operator = op;
        this.lhs = lhs;
        this.rhs = rhs;
    }

    public int evaluate() {
        return 0;
    }

    public void parse() {
        return;
    }
}
