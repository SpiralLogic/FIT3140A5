package com.srjengbro.scratchbasic.instructions;


import android.view.LayoutInflater;
import android.view.View;
import com.srjengbro.scratchbasic.VariableStore;

/**
 * Created by Maidenii on 12/04/16.
 */
public abstract class Instruction implements java.io.Serializable {
    protected String name = "UNKNOWN";

    public String getName() {

        return name;
    }

    protected String instruction;

    public String getInstruction() {
        return instruction;
    }


    public abstract View getLayout(LayoutInflater inflater);

    public abstract void update();

    public abstract void parse(String line);

    public abstract String run(VariableStore variableStore) throws InstructionRunException;

    public Integer getNextLine() {
        return null;
    }

}
