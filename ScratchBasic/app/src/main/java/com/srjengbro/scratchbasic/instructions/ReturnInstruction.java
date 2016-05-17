package com.srjengbro.scratchbasic.instructions;

import android.view.LayoutInflater;
import android.view.View;

import com.srjengbro.scratchbasic.ScratchBasicContext;

import java.util.EmptyStackException;
import java.util.Stack;

/**
 * @author: Sol Jennings 26356015
 * @date: 14/05/16
 * @description: An instruction for returning from a GoSub instruction
 */
public class ReturnInstruction extends Instruction {

    /**
     * The next line to return to
     */
    private Integer nextLine;

    /**
     * contructor
     */
    public ReturnInstruction() {
        this.name = "RETURN";
        this.hasDialog = false;
    }

    /**
     * @param inflater            inflater
     * @param scratchBasicContext program context
     * @return layout for edit dialogue
     */
    @Override
    public View getLayout(LayoutInflater inflater, ScratchBasicContext scratchBasicContext) {
        return null;
    }

    /**
     * update after editing
     */
    @Override
    public void update() {

    }

    /**
     * @param scratchBasicContext
     * @return run the instruction and return the result
     * @throws InstructionRunException
     */
    @Override
    public String run(ScratchBasicContext scratchBasicContext) throws InstructionRunException {
        Stack callstack = scratchBasicContext.getCallStack();
        try {
            nextLine = (Integer) callstack.pop();
        } catch (EmptyStackException e) {
            nextLine = null;
        }
        return "";
    }

    /**
     * @param scratchBasicContext program context
     * update program pointer
     */
    public void updatePointer(ScratchBasicContext scratchBasicContext) {
        scratchBasicContext.setCurrentLine(nextLine);
    }
}
