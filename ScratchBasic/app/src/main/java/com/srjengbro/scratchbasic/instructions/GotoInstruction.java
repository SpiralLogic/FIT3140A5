package com.srjengbro.scratchbasic.instructions;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.srjengbro.scratchbasic.R;
import com.srjengbro.scratchbasic.VariableStore;

/**
 *
 *
 *
 *
 */
public class GotoInstruction extends Instruction {

    private EditText gotoText;
    private Integer gotoLine;

    public GotoInstruction() {
        name = "GOTO";
        gotoLine = 0;
        this.instruction = "0";
    }

    @Override
    public String run(VariableStore variableStore) {

        return "";
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
    public Integer getNextLine() {
        return getGotoLine();
    }
}
