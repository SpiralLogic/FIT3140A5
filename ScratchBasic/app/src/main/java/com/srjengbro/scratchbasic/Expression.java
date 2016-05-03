package com.srjengbro.scratchbasic;

import android.util.Log;

import com.srjengbro.scratchbasic.operators.NoOp;
import com.srjengbro.scratchbasic.operators.Operator;

/**
 * Created by Maidenii on 29/04/16.
 */
public class Expression implements java.io.Serializable {

    public String getLhs() {
        return lhs;
    }

    private String lhs;

    public String getRhs() {
        return rhs;
    }

    public Operator getOperator() {
        return operator;
    }

    private String rhs;
    private Operator operator = new NoOp();

    public Expression(Operator op, String lhs, String rhs) {

        if (rhs.length() > 0) {
            operator = op;
            this.rhs = rhs;
        }
        this.lhs = lhs;

    }

    public Expression(String lhs) {
        this.lhs = lhs;
        operator = new NoOp();
        this.rhs = null;
    }

    public Integer evaluate(VariableStore variableStore) throws VariableDoesNotExistException {
        Integer lhsInt;
        Integer rhsInt;
        try {
            lhsInt = Integer.parseInt(lhs);
        } catch (NumberFormatException e) {
            Variable var = variableStore.getVariable(lhs);
            if (var == null) {
                throw new VariableDoesNotExistException("Variable " + lhs + " does not exist");
            }
            lhsInt = var.getValue();
        }
        if (rhs == null || operator instanceof NoOp) {
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

        return operator.evaluate(lhsInt, rhsInt);


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
