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
    public String getName() {
        return this.name;
    }
    public void update() {
        gotoInstruction.parse(gotoText.getText().toString());
        parse(ifText.getText().toString());
    }

    @Override
    protected void parse(String line) {
        try {
            expression = ExpressionMaker.generateExpression(line);
            StringBuilder s = new StringBuilder();
            s.append(expression.toString());
            s.append(" ");
            s.append(gotoInstruction.getName());
            s.append(" ");
            s.append(gotoInstruction.getInstruction());
            instruction = s.toString();
        } catch (ExpressionParseException e) {
            System.out.print(e.getMessage());
        }
    }

    public View getLayout(LayoutInflater inflater) {
        View layout = inflater.inflate(R.layout.inst_if, null);
        ifText = (EditText) layout.findViewById(R.id.if_text);
        if ( expression!= null ) {
            ifText.setText(expression.toString());
        }
        gotoText = (EditText) layout.findViewById(R.id.goto_line);
        gotoText.setText(gotoInstruction.getInstruction());
        return layout;
    }



    @Override
    public String run() {
        //TODO
        return null;
    }
}
