package com.srjengbro.scratchbasic;

import com.srjengbro.scratchbasic.instructions.Instruction;
import com.srjengbro.scratchbasic.instructions.RemInstruction;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author      Sol Jennings
 * @description
 */
public class ScratchBasicContext implements Serializable {

    /**
     *
     */
    private ArrayList<Instruction> instructions = new ArrayList<>();
    /**
     *
     */
    private String author;
    /**
     *
     */
    private String description;
    /**
     *
     */
    private String filename;

    /**
     * @return
     */
    public ArrayList<Instruction> getInstructions() {
        return instructions;
    }


    /**
     *
     */
    public void newProgram() {
        instructions.clear();
        RemInstruction instruction = new RemInstruction();
        instruction.update("Your first line!");
        instructions.add(instruction);
    }

}
