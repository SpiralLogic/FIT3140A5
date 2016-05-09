package com.srjengbro.scratchbasic;

import com.srjengbro.scratchbasic.instructions.Instruction;
import com.srjengbro.scratchbasic.instructions.RemInstruction;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author      Sol Jennings
 * @description The context of the program, stores the metadata and instruction
 */
public class ScratchBasicContext implements Serializable {

    /**
     * instruction list
     */
    private ArrayList<Instruction> instructions = new ArrayList<>();
    /**
     * author of the program
     */
    private String author;
    /**
     * description of the program
     */
    private String description;

    /**
     * @return get the filename of the current program
     */
    public String getFilename() {
        return filename;
    }

    /**
     * @return get the description of the current program
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return get the author of the current program
     */
    public String getAuthor() {
        return author;
    }

    /**
     * @param author set the author
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * @param description set the description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @param filename set the filename
     */
    public void setFilename(String filename) {
        this.filename = filename;
    }

    /**
     * filename of the current program
     */
    private String filename;

    /**
     * @return the instructionlist of the current program
     */
    public ArrayList<Instruction> getInstructions() {
        return instructions;
    }


    /**
     * create a new program with an initial instruction
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
