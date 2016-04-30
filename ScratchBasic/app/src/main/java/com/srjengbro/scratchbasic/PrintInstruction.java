package com.srjengbro.scratchbasic;

import android.util.Log;
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
        parse(printText.getText().toString());
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
            instruction = expression.toString();
        } catch (ExpressionParseException e) {
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