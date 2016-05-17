package com.srjengbro.scratchbasic;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.srjengbro.scratchbasic.instructions.Instruction;
import com.srjengbro.scratchbasic.instructions.LetInstruction;
import com.srjengbro.scratchbasic.operators.NoOpOperator;
import com.srjengbro.scratchbasic.operators.Operator;

import java.util.ArrayList;

/**
 * @author Sol Jennigns & Giles Browne
 * @description Expression class Stores an expression that can be evaluated. An object holds the
 * LHS, RHS an operator of an expression. The lhs and rhs can be variables or
 * integer values. Handles the evaluation of an expression at run time
 */
public class Expression implements java.io.Serializable {

    /**
     * @return get LHS of the expression
     */
    public String getLhs() {
        return lhs;
    }

    /**
     * lhs of the expression
     */
    private String lhs;

    /**
     * @return get the rhs of the expression
     */
    public String getRhs() {
        return rhs;
    }

    /**
     * @return get the expressions operator
     */
    public Operator getOperator() {
        return operator;
    }

    /**
     * rhs of the instruction
     */
    private String rhs;
    /**
     * operator of the instruction defaults to no operation
     */
    private Operator operator = new NoOpOperator();
    /**
     * lhs textbox
     */
    public transient EditText lhsText;
    /**
     * operator spinner
     */
    public transient Spinner opSpinner;

    /**
     * lhs variable spinner
     */
    private transient Spinner lhsSpinner;

    /**
     * rhs variable spinner
     */

    private transient Spinner rhsSpinner;

    /**
     * rhs textbox
     */
    public transient EditText rhsText;
    /**
     * rhs label
     */
    public transient TextView rhsLabel;

    /**
     * @param op
     * @param lhs
     * @param rhs
     */
    public Expression(Operator op, String lhs, String rhs) {

        if (rhs.length() > 0) {
            operator = op;
            this.rhs = rhs;
        }
        this.lhs = lhs;

    }

    /**
     * @param lhs
     */
    public Expression(String lhs) {
        this.lhs = lhs;
        operator = new NoOpOperator();
        this.rhs = null;
    }

    /**
     *
     */
    public Expression() {
        operator = new NoOpOperator();
        this.lhs = null;
        this.rhs = null;
    }

    /**
     * @param variableStore variables
     * @return evaluates the expression
     * @throws VariableDoesNotExistException
     * @throws ExpressionParseException
     */
    public Double evaluate(VariableStore variableStore) throws VariableDoesNotExistException, ExpressionParseException {
        Double lhsInt;
        Double rhsInt;
        if (lhs == null || lhs.length() == 0) {
            throw new ExpressionParseException("Expression missing left hand side");
        }
        try {
            lhsInt = Double.parseDouble(lhs);
        } catch (NumberFormatException e) {
            DoubleVariable var = (DoubleVariable) variableStore.getVariable(lhs);
            if (var == null) {
                throw new VariableDoesNotExistException("DoubleVariable " + lhs + " does not exist");
            }
            lhsInt = var.getValue();
        }
        if (rhs == null || operator instanceof NoOpOperator) {
            return lhsInt;
        }
        try {
            rhsInt = Double.parseDouble(rhs);
        } catch (NumberFormatException e) {
            DoubleVariable var = (DoubleVariable) variableStore.getVariable(rhs);

            if (var == null) {
                throw new VariableDoesNotExistException("DoubleVariable " + rhs + " does not exist");
            }
            rhsInt = var.getValue();

        }
        return operator.evaluate(lhsInt, rhsInt);


    }


    /**
     * @return represents expression as a string
     */
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

    /**
     * @param layout          the layout to add the expression to
     * @param instructionList
     */
    public void layout(View layout, ArrayList instructionList) {

        ArrayList<String> variablelist = new ArrayList<>();
        variablelist.add("Number");
        Instruction inst;
        LetInstruction li;
        int lhspos = 0;
        int rhspos = 0;
        for (int i = 0; i < instructionList.size(); i++) {
            inst = (Instruction) instructionList.get(i);
            if (inst instanceof LetInstruction) {
                li = (LetInstruction) inst;
                String varName = li.getVariable();
                if (varName != null && variablelist.indexOf(varName) < 0) {
                    variablelist.add(li.getVariable());
                    if (varName.equals(lhs)) {
                        lhspos = variablelist.size() - 1;
                    }
                    if (varName.equals(rhs)) {
                        rhspos = variablelist.size() - 1;
                    }
                }
            }
        }


        ArrayAdapter<String> variablesAdapter = new ArrayAdapter<>(layout.getContext(), android.R.layout.simple_spinner_dropdown_item, variablelist);

        lhsSpinner = (Spinner) layout.findViewById(R.id.lhs_spinner);
        lhsSpinner.setAdapter(variablesAdapter);
        lhsSpinner.setSelection(lhspos);
        lhsSpinner.setOnItemSelectedListener(onLHSSelect());

        rhsSpinner = (Spinner) layout.findViewById(R.id.rhs_spinner);
        rhsSpinner.setAdapter(variablesAdapter);
        rhsSpinner.setSelection(rhspos);
        rhsSpinner.setOnItemSelectedListener(onRHSSelect());

        lhsText = (EditText) layout.findViewById(R.id.lhs_text);
        rhsText = (EditText) layout.findViewById(R.id.rhs_text);
        rhsLabel = (TextView) layout.findViewById(R.id.rhs_label);
        lhsText.setText(lhs);
        rhsText.setText(rhs);
        rhsText.setVisibility(View.INVISIBLE);
        opSpinner = (Spinner) layout.findViewById(R.id.operator_spinner);
        opSpinner.setOnItemSelectedListener(onOperatorSelect());
        Integer oppos = ((ArrayAdapter<String>) opSpinner.getAdapter()).getPosition(operator.getSymbol());
        opSpinner.setSelection(oppos);
        rhsText.setVisibility(View.INVISIBLE);

    }

    /**
     * @return event for when a user chooses an operator
     */
    private AdapterView.OnItemSelectedListener onOperatorSelect() {
        return new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View v, int pos, long id) {
                Spinner spinner = (Spinner) parent;
                String operatorText = spinner.getSelectedItem().toString();
                if (operatorText.length() == 0) {
                    rhsText.setVisibility(View.INVISIBLE);
                    rhsLabel.setVisibility(View.INVISIBLE);
                    rhsSpinner.setVisibility(View.INVISIBLE);
                } else {
                    rhsText.setVisibility(View.VISIBLE);
                    rhsLabel.setVisibility(View.VISIBLE);
                    rhsSpinner.setVisibility(View.VISIBLE);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };
    }

    /**
     * @return event for when a user chooses a LHS variable
     */
    private AdapterView.OnItemSelectedListener onLHSSelect() {
        return new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View v, int pos, long id) {
                Spinner spinner = (Spinner) parent;
                String lhstext = spinner.getSelectedItem().toString();
                if (lhstext.equals("Number")) {
                    lhsText.setText("");
                    lhsText.setVisibility(View.VISIBLE);

                } else {
                    lhsText.setText(lhstext);
                    lhsText.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };
    }

    /**
     * @return event for when a user chooses RHS variable
     */
    private AdapterView.OnItemSelectedListener onRHSSelect() {
        return new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View v, int pos, long id) {
                Spinner spinner = (Spinner) parent;
                String rhstext = spinner.getSelectedItem().toString();
                if (rhstext.equals("Number")) {
                    rhsText.setText("");
                    rhsText.setVisibility(View.VISIBLE);
                } else {
                    rhsText.setText(rhstext);
                    rhsText.setVisibility(View.INVISIBLE);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };
    }
}
