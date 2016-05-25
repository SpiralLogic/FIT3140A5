package com.srjengbro.scratchbasic;

import android.util.Log;

import com.srjengbro.scratchbasic.instructions.Instruction;
import com.srjengbro.scratchbasic.instructions.RemInstruction;
import com.srjengbro.scratchbasic.instructions.SubInstruction;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Stack;

/**
 * @author Sol Jennigns & Giles Browne
 * @description The context of the program, stores the metadata and instructions of a program.
 * can be serialized so that a program can be saved and loaded.
 */
public class ScratchBasicContext implements Serializable {

    /**
     * instruction list
     */
    private ArrayList<Instruction> instructions = new ArrayList<>();
    /**
     * DoubleVariable Storage
     */
    private VariableStore variableStore = new VariableStore();

    /**
     * Stack for storing subroutine stack calls
     */
    private transient Stack<Integer> callStack = new Stack<>();

    /**
     * next line to run
     */
    private transient Integer currentLine = 0;
    /**
     * author of the program
     */
    private String author;
    /**
     * description of the program
     */
    private String description;

    /**
     * Email metadata of the program
     */
    private String email;
    /**
     * filename of the current program
     */
    private String filename;
    /**
     * Available Sub Routines
     */

    private ArrayList<String> subRoutines;

    /**
     * @return Getter for subroutine list
     */
    public ArrayList<String> getSubRoutines() {
        return subRoutines;
    }

    /**
     * @return get the filename of the current program
     */
    public String getFilename() {
        return filename;
    }

    /**
     * @param filename set the filename
     */
    public void setFilename(String filename) {
        this.filename = filename;
    }

    /**
     * @return get the description of the current program
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description set the description
     */
    public void setDescription(String description) {
        this.description = description;
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
        setEmail("");
        setDescription("");
        setFilename("newfile");
        RemInstruction instruction = new RemInstruction();
        instruction.update("Your first line!");
        instructions.add(instruction);
    }

    /**
     * @return Programs DoubleVariable store
     */
    public VariableStore getVariableStore() {
        return variableStore;
    }

    /**
     * @return getter for call stack
     */
    public Stack<Integer> getCallStack() {
        if (callStack == null) {
            callStack = new Stack<>();
        }
        return callStack;
    }

    /**
     * Resets the current calling stack
     */
    public void resetCallStack() {
        callStack = new Stack<>();
    }

    /**
     * @return the next line of the program to run
     */
    public Integer getCurrentLine() {
        return currentLine;
    }

    /**
     * @param currentLine set the next line of the program to run
     */
    public void setCurrentLine(Integer currentLine) {

        if (currentLine == null) {
            currentLine = instructions.size();
        }
        this.currentLine = currentLine;
    }

    /**
     * @return Getter for email metadata
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email Setter for email metadata
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Update the list of possbile sub routines
     */
    public void updateSubRoutineList() {

        Instruction inst;
        subRoutines = new ArrayList<>();
        for (int i = 0; i < instructions.size(); i++) {
            inst = instructions.get(i);
            if (inst instanceof SubInstruction) {
                SubInstruction si = (SubInstruction) inst;

                subRoutines.add(si.getInstruction());

            }
        }

    }
}
