package com.srjengbro.scratchbasic;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.srjengbro.scratchbasic.operators.NoOp;
import com.srjengbro.scratchbasic.operators.Operator;


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
    public transient EditText lhsText;
    public transient Spinner opSpinner;
    public transient EditText rhsText;
    public transient TextView rhsLabel;

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

    public Expression() {
        operator = new NoOp();
        this.lhs = null;
        this.rhs = null;
    }

    public Integer evaluate(VariableStore variableStore) throws VariableDoesNotExistException, ExpressionParseException {
        Integer lhsInt;
        Integer rhsInt;
        if (lhs == null) {
            throw new ExpressionParseException("Expression missing left hand side");
        }
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

    public void layout(LayoutInflater inflator, View layout) {

        lhsText = (EditText) layout.findViewById(R.id.lhs_text);
        rhsText = (EditText) layout.findViewById(R.id.rhs_text);
        opSpinner = (Spinner) layout.findViewById(R.id.operator_spinner);
        rhsLabel = (TextView) layout.findViewById(R.id.rhs_label);
        opSpinner.setOnItemSelectedListener(onOperatorSelect());
        lhsText.setText(lhs);
        rhsText.setText(rhs);
        Integer oppos = ((ArrayAdapter<String>) opSpinner.getAdapter()).getPosition(operator.getSymbol());
        opSpinner.setSelection(oppos);
    }

    private AdapterView.OnItemSelectedListener onOperatorSelect() {
        return new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View v, int pos, long id) {
                Spinner spinner = (Spinner) parent;
                String instructionText = spinner.getSelectedItem().toString();
                if (instructionText.length() == 0) {
                    rhsText.setVisibility(View.INVISIBLE);
                    rhsLabel.setVisibility(View.INVISIBLE);
                } else {
                    rhsText.setVisibility(View.VISIBLE);
                    rhsLabel.setVisibility(View.VISIBLE);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };
    }
}
