package com.srjengbro.scratchbasic.instructions;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;

import com.srjengbro.scratchbasic.ScratchBasicContext;


/**
 * @author Sol Jennigns & Giles Browne
 * @description GotoInstruction This class allows for the creation of a goto instruction
 * It allows an instruction to change the next line to be executed in a ScratchBasic
 * program. It inherits from the instruction class. It handles the layout, running,
 * and processing of any GOTO instruction
 */
public class GotoInstruction extends Instruction {

    /**
     * The line that the goto will goto
     */
    private Integer gotoLine;

    /**
     * Constructor
     */
    public GotoInstruction() {
        name = "GOTO";
        gotoLine = null;
        this.instruction = "Click to set goto line";
        hasDialog = false;
    }

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
        if (line == null) {
            instruction = "Click to set goto line";
        } else {
            instruction = line.toString();
        }
    }

    /**
     * @param inflater            layout inflater
     * @param scratchBasicContext program context
     * @return the layout for the instruction
     */
    @Override
    public View getLayout(LayoutInflater inflater, ScratchBasicContext scratchBasicContext) {
        return null;
    }

    /**
     * Update after edit
     */
    @Override
    public void update() {

    }

    /**
     * @param scratchBasicContext program context
     * @return the result
     */
    @Override
    public String run(ScratchBasicContext scratchBasicContext) {
        return "";
    }

    /**
     * @param scratchBasicContext program context
          */
    public void updatePointer(ScratchBasicContext scratchBasicContext) {
        scratchBasicContext.setCurrentLine(getGotoLine());
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
     * @return background colour for instruction
     */
    @Override
    public int getBackgroundColor() {
        if (gotoLine == null) {
            return Color.RED;
        }else
        {
            return super.getBackgroundColor();
        }

    }
}
