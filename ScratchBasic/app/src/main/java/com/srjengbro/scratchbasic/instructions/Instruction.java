package com.srjengbro.scratchbasic.instructions;


import android.view.LayoutInflater;
import android.view.View;
import com.srjengbro.scratchbasic.VariableStore;


/**
 * @author      Sol Jennings
 * @description This is the main abstract Instruction class for any scratch basic instructions
 *              All instructions must inherit from this class and it dictates which methods
 *              must be implemented by an instruction
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
     * @param inflater inflater
     * @return layout of the instruction
     */
    public abstract View getLayout(LayoutInflater inflater);

    /**
     * update the instruction after edit
     */
    public abstract void update();


    /**
     * @param variableStore variables
     * @return run the instruction and return the result
     * @throws InstructionRunException
     */
    public abstract String run(VariableStore variableStore) throws InstructionRunException;

    /**
     * @return the next line to execture
     */
    public Integer getNextLine() {
        return null;
    }

}
