package com.srjengbro.scratchbasic;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

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
    public String run() {
        return null;
    }
}
