package com.srjengbro.scratchbasic;

/**
 * Created by Maidenii on 29/04/16.
 */
public class Expression {

    private String lhs;
    private String rhs;
    private Operator operator;
    private VariableStore variableStore;

    public Expression(Operator op, String lhs, String rhs) {
        operator = op;
        this.lhs = lhs;
        this.rhs = rhs;
    }

    public void setVariableStore(VariableStore variableStore) {
        this.variableStore = variableStore;
    }

    public int evaluate() throws VariableDoesNotExistException {
        Integer lhsInt;
        Integer rhsInt;
        try {
            lhsInt = Integer.parseInt(lhs);
        } catch (NumberFormatException e) {
            lhsInt = variableStore.getVariable(lhs).getValue();
            if (lhsInt == null) {
                throw new VariableDoesNotExistException();
            }
        }
        try {
            rhsInt = Integer.parseInt(rhs);
        } catch (NumberFormatException e) {
            rhsInt = variableStore.getVariable(rhs).getValue();
            if (lhsInt == null) {
                throw new VariableDoesNotExistException();
            }
        }

        return operator.evaluate(rhsInt, lhsInt);


    }

    public void parse() {
        return;
    }
}
