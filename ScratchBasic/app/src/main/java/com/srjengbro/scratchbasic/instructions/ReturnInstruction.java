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

    private Integer nextLine;

    public ReturnInstruction() {
        this.name = "RETURN";
        this.hasDialog = false;
    }

    @Override
    public View getLayout(LayoutInflater inflater, ScratchBasicContext scratchBasicContext) {
        return null;
    }

    @Override
    public void update() {

    }

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

    public void updatePointer(ScratchBasicContext scratchBasicContext) {
        scratchBasicContext.setCurrentLine(nextLine);
    }
}
