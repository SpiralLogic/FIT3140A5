package com.srjengbro.scratchbasic;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

/**
 * Created by Maidenii on 16/04/16.
 */
public class PrintInstruction extends Instruction {

    protected EditText printText;
    protected Expression expression;

    public PrintInstruction() {
        name = "PRINT";
    }

    public View getLayout(LayoutInflater inflater) {
        View layout = inflater.inflate(R.layout.inst_print, null);
        printText = (EditText) layout.findViewById(R.id.print_text);
        printText.setText(instruction);
        return layout;
    }

    public void update() {
        parse(printText.getText().toString());
    }

    protected void parse(String line) {
        try {
            expression = ExpressionMaker.generateExpression(line);
            instruction = expression.toString();
        } catch (ExpressionParseException e) {
            System.out.print(e.getMessage());
        }
    }

    @Override
    public String run() {
//TODO
        return "";
    }

}