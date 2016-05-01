package com.srjengbro.scratchbasic.instructions;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.srjengbro.scratchbasic.Expression;
import com.srjengbro.scratchbasic.ExpressionMaker;
import com.srjengbro.scratchbasic.ExpressionParseException;
import com.srjengbro.scratchbasic.R;
import com.srjengbro.scratchbasic.Variable;
import com.srjengbro.scratchbasic.VariableDoesNotExistException;
import com.srjengbro.scratchbasic.VariableStore;

public class LetInstruction extends Instruction {

    private EditText letVariableText;
    private EditText letExpressionText;

    private String variable;
    private Expression expression;

    public LetInstruction() {
        name = "LET";
    }


    @Override
    public View getLayout(LayoutInflater inflater) {
        View layout = inflater.inflate(R.layout.inst_let, null);
        letVariableText = (EditText) layout.findViewById(R.id.let_variable);
        letVariableText.setText(variable);
        letExpressionText = (EditText) layout.findViewById(R.id.let_expression);
        if (expression != null) {
            letExpressionText.setText(expression.toString());
        }
        return layout;
    }

    @Override
    public void update() {

        String varText = letVariableText.getText().toString();
        String expText = letExpressionText.getText().toString();
        parse(varText, expText);


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
    public String run(Integer lineno, VariableStore variableStore) throws InstructionRunException {
        Integer result;
        try {
            result = expression.evaluate(variableStore);
            variableStore.setVariable(variable, result);
            Log.d("Result",result.toString());
        } catch (VariableDoesNotExistException e) {
            throw new InstructionRunException("Error on line " + lineno.toString() + " " + e.getMessage());
        }
        result = variableStore.getVariable(variable).getValue();
        Log.d("Result",result.toString());
        return "";
    }
}
