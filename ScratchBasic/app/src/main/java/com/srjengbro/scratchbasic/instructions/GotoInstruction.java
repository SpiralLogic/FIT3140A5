package com.srjengbro.scratchbasic.instructions;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import com.srjengbro.scratchbasic.R;
import com.srjengbro.scratchbasic.VariableStore;


/**
 * @author      Sol Jennings
 * @description GotoInstruction, allows program to go to another line to execute
 */
public class GotoInstruction extends Instruction {

    /**
     *  Textbox for gotolayout
     */
    private transient EditText gotoText;
    /**
     *  The line that the goto will goto
     */
    private Integer gotoLine;

    /**
     * @return the line to goto
     */
    public Integer getGotoLine() {

        return gotoLine;
    }

    /**
     * Constructor
     */
    public GotoInstruction() {
        name = "GOTO";
        gotoLine = null;
        this.instruction = "";
    }

    /**
     * @param variableStore variable store
     * @return the result
     */
    @Override
    public String run(VariableStore variableStore) {
        return "";
    }

    /**
     * @param inflater layout inflator
     * @return the layout for the instruction
     */
    @Override
    public View getLayout(LayoutInflater inflater) {
        View layout = inflater.inflate(R.layout.inst_goto, null);
        gotoText = (EditText) layout.findViewById(R.id.goto_line);
        gotoText.setText(instruction);
        return layout;
    }

    /**
     * update the instruction after edit
     */
    @Override
    public void update() {

        parse(gotoText.getText().toString());
    }

    /**
     * @param line parse the instruction from a string
     */
    public void parse(String line) {
        try {
            gotoLine = Integer.parseInt(line);
            instruction = line;
        } catch (NumberFormatException e) {
            System.out.print(e.getMessage());
        }
    }

    /**
     * decrease the goto line used for autoline numbering
     */
    public void decreaseGotoLine() {
        if (gotoLine == null) return;
        gotoLine -= 1;
        instruction = gotoLine.toString();
    }

    /**
     * increase the goto line used for autoline numbering
     */
    public void increaseGotoLine() {
        if (gotoLine == null) return;
        gotoLine += 1;
        instruction = gotoLine.toString();
    }


    /**
     * @return  the next line that will run
     */
    public Integer getNextLine() {

        return getGotoLine();
    }
}
