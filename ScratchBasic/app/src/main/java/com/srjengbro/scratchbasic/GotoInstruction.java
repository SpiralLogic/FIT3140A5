package com.srjengbro.scratchbasic;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

public class GotoInstruction extends Instruction {

    protected Expression expression;
    private EditText gotoText;
    private Integer gotoLine;

    public GotoInstruction() {
        name = "GOTO";
    }

    @Override
    public String run() {

        return null;
    }

    @Override
    public View getLayout(LayoutInflater inflater) {
        View layout = inflater.inflate(R.layout.inst_goto, null);
        gotoText = (EditText) layout.findViewById(R.id.goto_line);
        gotoText.setText(instruction);
        return layout;
    }

    @Override
    public void update() {
        parse(gotoText.getText().toString());
    }

    public void parse(String line) {
        try {
            gotoLine = Integer.parseInt(line);
            instruction = line;
        } catch (NumberFormatException e) {
            System.out.print(e.getMessage());
        }
    }

    public void decreaseGotoLine() {
        gotoLine -= 1;
        instruction = gotoLine.toString();
    }
    public void increaseGotoLine() {
        gotoLine += 1;
        instruction = gotoLine.toString();
    }
    public Integer getGotoLine() {
        return gotoLine;
    }
}
