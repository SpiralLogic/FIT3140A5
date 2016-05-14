package com.srjengbro.scratchbasic.instructions;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.srjengbro.scratchbasic.VariableStore;

import java.util.ArrayList;


/**
 * @author Sol Jennigns & Giles Browne
 * @description GotoInstruction This class allows for the creation of a goto instruction
 * It allows an instruction to change the next line to be executed in a ScratchBasic
 * program. It inherits from the instruction class. It handles the layout, running,
 * and processing of any GOTO instruction
 */
public class GotoInstruction extends Instruction {

    /**
     * Textbox for gotolayout
     */
    private transient EditText gotoText;
    /**
     * The line that the goto will goto
     */
    private Integer gotoLine;

    /**
     * @return the line to goto
     */
    public Integer getGotoLine() {

        return gotoLine;
    }

    /**
     * @param line the line number to set the goto instruction to
     */
    public void setGotoLine(Integer line) {
        gotoLine = line;
        instruction = line.toString();
    }

    /**
     * Constructor
     */
    public GotoInstruction() {
        name = "GOTO";
        gotoLine = null;
        this.instruction = "";
        hasDialog = false;
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
     * @param instructionList
     * @return the layout for the instruction
     */
    @Override
    public View getLayout(LayoutInflater inflater, ArrayList instructionList) {
        return null;
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
     * @return the next line that will run
     */
    public Integer getNextLine() {

        return getGotoLine();
    }
}
