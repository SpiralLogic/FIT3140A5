package com.srjengbro.scratchbasic.operators;


public abstract class Operator implements java.io.Serializable {
    protected String name;
    protected String symbol;

    public abstract Integer evaluate(Integer lhs, Integer rhs);
    public String getSymbol() {
        return symbol;
    }
}
