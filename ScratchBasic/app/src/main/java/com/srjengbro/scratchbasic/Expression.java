package com.srjengbro.scratchbasic;

import android.util.Log;

import com.srjengbro.scratchbasic.operators.Operator;

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

    public Expression(String lhs) {
        this.lhs = lhs;
        operator = null;
        this.rhs = null;
    }

    public Integer evaluate(VariableStore variableStore) throws VariableDoesNotExistException {
        Integer lhsInt;
        Integer rhsInt;
        try {
            lhsInt = Integer.parseInt(lhs);
        } catch (NumberFormatException e) {
            Log.d("LHS",lhs);
            Variable var = variableStore.getVariable(lhs);

            if (var == null) {
                throw new VariableDoesNotExistException("Variable " + lhs + " does not exist");
            }
            lhsInt = var.getValue();
        }
        if (rhs == null || operator == null) {
            return lhsInt;
        }
        try {
            rhsInt = Integer.parseInt(rhs);
        } catch (NumberFormatException e) {
            Variable var = variableStore.getVariable(rhs);

            if (var == null) {
                throw new VariableDoesNotExistException("Variable " + rhs + " does not exist");
            }
            rhsInt = var.getValue();

        }

        return operator.evaluate(rhsInt, lhsInt);


    }

    public void parse() {
        return;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(lhs);
        if (rhs != null && operator != null) {
            s.append(" ");
            s.append(operator.getSymbol());
            s.append(" ");
            s.append(rhs);
        }
        return s.toString();
    }
}
