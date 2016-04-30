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
        printText = (EditText) layout.findViewById(R.id.printText);
        printText.setText(instruction);
        return layout;
    }

    public void commit() {
        String[] tokens = printText.getText().toString().split("\\s+"); // Split on whitespace
        try {
            expression = ExpressionMaker.generateExpression(tokens[1], tokens[0], tokens[2]);
            instruction = expression.toString();
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }

    }

    @Override
    public String run() {
        try {
            return expression.evaluate().toString();
        } catch (VariableDoesNotExistException e) {

        }
        return "";
    }

}