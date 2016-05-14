package com.srjengbro.scratchbasic.instructions;

import android.view.LayoutInflater;
import android.view.View;

import com.srjengbro.scratchbasic.VariableStore;

import java.util.ArrayList;

/**
 * @author: Sol Jennings 26356015
 * @date: 14/05/16
 * @description: command for labelling a sub instruction set
 */
public class SubInstruction extends Instruction {
    /**
     * contructor
     */
    public SubInstruction() {
        this.name = "SUB";

    }

    @Override
    public View getLayout(LayoutInflater inflater, ArrayList instructionList) {
        return null;
    }

    @Override
    public void update() {

    }

    @Override
    public String run(VariableStore variableStore) throws InstructionRunException {
        return null;
    }
}
