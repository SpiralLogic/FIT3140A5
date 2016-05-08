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

    public String getFilename() {
        return filename;
    }

    public String getDescription() {
        return description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

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
        setAuthor("");
        setFilename("newfile");
        RemInstruction instruction = new RemInstruction();
        instruction.update("Your first line!");
        instructions.add(instruction);
    }

}
