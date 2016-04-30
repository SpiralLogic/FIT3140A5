package com.srjengbro.scratchbasic;


import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

/**
 * Created by Maidenii on 20/04/16.
 */
public class IfInstruction extends Instruction {

    protected EditText ifText;
    protected GotoInstruction gotoInstruction;
    protected EditText gotoText;
    protected Expression expression;
    public IfInstruction() {
        name = "IF";
        gotoInstruction = new GotoInstruction();

    }

    public void commit() {

        gotoInstruction.parse(gotoText.getText().toString());
        parse(ifText.getText().toString());

    }

    public void parse(String line) {
        String[] tokens = line.split("\\s+"); // Split on whitespace
        try {
            if (tokens.length < 3) {
                expression = ExpressionMaker.generateExpression(tokens[0]);

            }
            if (tokens.length == 3) {
                expression = ExpressionMaker.generateExpression(tokens[1], tokens[0], tokens[2]);
            }
            instruction = expression.toString() + " GOTO " + gotoInstruction.instruction;
        } catch (ExpressionParseException e) {
            System.out.print(e.getMessage());
        }

    }

    public View getLayout(LayoutInflater inflater) {
        View layout = inflater.inflate(R.layout.inst_if, null);
        ifText = (EditText) layout.findViewById(R.id.if_text);
        ifText.setText(instruction);
        gotoText = (EditText) layout.findViewById(R.id.goto_line);
        gotoText.setText(gotoInstruction.instruction);
        return layout;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String run() {
        return null;
    }
}
