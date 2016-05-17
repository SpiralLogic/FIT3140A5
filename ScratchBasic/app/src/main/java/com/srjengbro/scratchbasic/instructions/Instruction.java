package com.srjengbro.scratchbasic.instructions;


import android.view.LayoutInflater;
import android.view.View;

import com.srjengbro.scratchbasic.ScratchBasicContext;
import com.srjengbro.scratchbasic.VariableStore;

import java.util.ArrayList;


/**
 * @author Sol Jennigns & Giles Browne
 * @description This is the main abstract Instruction class for any scratch basic instructions
 * All instructions must inherit from this class and it dictates which methods
 * must be implemented by an instruction
 */
public abstract class Instruction implements java.io.Serializable {


    /**
     * Name of the instruction
     */
    protected String name = "UNKNOWN";

    /**
     * @return name of the instruction
     */
    public String getName() {

        return name;
    }

    /**
     * string representation of the instruction
     */
    protected String instruction = "";

    /**
     * @return instruction text
     */
    public String getInstruction() {

        return instruction;
    }

    /**
     * @return returns whether the instruction has an edit dialogue or not
     */
    public Boolean getHasDialog() {
        return hasDialog;
    }

    /**
     * Whether the instruction has an edit dialog
     */
    protected Boolean hasDialog = true;


    /**
     * @param inflater        inflater
     * @param scratchBasicContext
     * @return layout of the instruction
     */
    public abstract View getLayout(LayoutInflater inflater, ScratchBasicContext scratchBasicContext);

    /**
     * update the instruction after edit
     */
    public abstract void update();


    /**
     * @param scratchBasicContext@return run the instruction and return the result
     * @throws InstructionRunException
     */
    public abstract String run(ScratchBasicContext scratchBasicContext) throws InstructionRunException;

    /**
     * @param scratchBasicContext
     * @return the next line to execture
     */
    public void updatePointer(ScratchBasicContext scratchBasicContext) {
        scratchBasicContext.setCurrentLine(scratchBasicContext.getCurrentLine() + 1);
    }

}
