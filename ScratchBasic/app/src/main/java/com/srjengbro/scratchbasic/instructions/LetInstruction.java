package com.srjengbro.scratchbasic.instructions;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.srjengbro.scratchbasic.Expression;
import com.srjengbro.scratchbasic.ExpressionMaker;
import com.srjengbro.scratchbasic.ExpressionParseException;
import com.srjengbro.scratchbasic.R;
import com.srjengbro.scratchbasic.VariableDoesNotExistException;
import com.srjengbro.scratchbasic.VariableStore;

public class LetInstruction extends Instruction {

    private transient EditText letVariableText;
    private String variable;
    private Expression expression;

    public LetInstruction() {
        name = "LET";
        expression = new Expression();

    }


    @Override
    public View getLayout(LayoutInflater inflater) {
        View layout = inflater.inflate(R.layout.inst_let, null);
        letVariableText = (EditText) layout.findViewById(R.id.let_variable);
        letVariableText.setText(variable);
        expression.layout(inflater, layout);
        return layout;
    }

    @Override
    public void update() {
        variable = letVariableText.getText().toString();
        String type = expression.opSpinner.getSelectedItem().toString();
        String lhs = expression.lhsText.getText().toString();
        String rhs = expression.rhsText.getText().toString();
        expression = ExpressionMaker.generateExpression(type, lhs, rhs);
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
        if (expression == null) {
            throw new InstructionRunException("Instruction missing expression");
        }
        if (variable == null) {
            throw new InstructionRunException("Instruction missing variable");
        }
        Integer result;
        try {
            result = expression.evaluate(variableStore);
            variableStore.setVariable(variable, result);
        } catch (VariableDoesNotExistException | ExpressionParseException e) {
            throw new InstructionRunException(e.getMessage());
        }
        return "";
    }
}
