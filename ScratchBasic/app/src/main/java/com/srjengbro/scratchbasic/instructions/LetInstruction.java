package com.srjengbro.scratchbasic.instructions;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.srjengbro.scratchbasic.Expression;
import com.srjengbro.scratchbasic.ExpressionMaker;
import com.srjengbro.scratchbasic.ExpressionParseException;
import com.srjengbro.scratchbasic.R;
import com.srjengbro.scratchbasic.VariableDoesNotExistException;
import com.srjengbro.scratchbasic.VariableStore;

public class LetInstruction extends Instruction {

    private EditText letVariableText;
    private EditText letExpressionText;

    private String variable;
    private Expression expression;
    protected EditText lhsText;
    protected Spinner opSpinner;
    protected EditText rhsText;

    public LetInstruction() {
        name = "LET";
    }


    @Override
    public View getLayout(LayoutInflater inflater) {
        View layout = inflater.inflate(R.layout.inst_let, null);
        letVariableText = (EditText) layout.findViewById(R.id.let_variable);
        letVariableText.setText(variable);
        lhsText = (EditText) layout.findViewById(R.id.lhs_text);
        rhsText = (EditText) layout.findViewById(R.id.rhs_text);
        opSpinner = (Spinner) layout.findViewById(R.id.operator_spinner);
        if (expression != null) {
            lhsText.setText(expression.getLhs());
            rhsText.setText(expression.getRhs());
            Integer oppos = ((ArrayAdapter<String>) opSpinner.getAdapter()).getPosition(expression.getOperator().getSymbol());
            opSpinner.setSelection(oppos);
        }
        return layout;
    }

    @Override
    public void update() {

        variable = letVariableText.getText().toString();
        String type = opSpinner.getSelectedItem().toString();
        String lhs = lhsText.getText().toString();
        String rhs = rhsText.getText().toString();
        expression = ExpressionMaker.generateExpression(type,lhs,rhs);
        instruction = variable + " = " + expression.toString();

    }

    @Override
    public void parse(String line) {

    }

    protected void parse(String var, String exp) {

        String[] tokens = var.split("\\s+"); // Split on whitespace
        variable = tokens[0];

        try {
            expression = ExpressionMaker.generateExpression(exp);
            instruction = variable + " = " + expression.toString();
        } catch (ExpressionParseException e) {
            System.out.print(e.getMessage());
        }
    }

    @Override
    public String run(VariableStore variableStore) throws InstructionRunException {
        Integer result;
        try {
            result = expression.evaluate(variableStore);
            variableStore.setVariable(variable, result);
        } catch (VariableDoesNotExistException e) {
            throw new InstructionRunException(e.getMessage());
        }
        return "";
    }
}
