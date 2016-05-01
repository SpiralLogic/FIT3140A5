package com.srjengbro.scratchbasic.instructions;


import android.view.LayoutInflater;
import android.view.View;

import com.srjengbro.scratchbasic.VariableStore;

/**
 * Created by Maidenii on 12/04/16.
 */
public abstract class Instruction {
    protected String name = "UNKNOWN";

    public String getName() {
        return name;
    }

    public String getInstruction() {
        return instruction;
    }

    protected String instruction;


    public abstract View getLayout(LayoutInflater inflater);

    public abstract void update();

    public Integer getNextLine() {
                return null;
    }

    public abstract void parse(String line);

    public abstract String run(Integer line_no, VariableStore variableStore) throws InstructionRunException;

}
