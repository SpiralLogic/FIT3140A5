package com.srjengbro.scratchbasic.instructions;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.srjengbro.scratchbasic.R;
import com.srjengbro.scratchbasic.ScratchBasicContext;

import java.util.ArrayList;
import java.util.Stack;

/**
 * @author: Sol Jennings 26356015
 * @date: 14/05/16
 * @description: Instruction for going to a sub instruction set
 */
public class GoSubInstruction extends Instruction {

    /**
     * Next line to go to
     */
    private Integer nextLine;
    /**
     * Instruction lavel to go to
     */
    private String subLabel;
    /**
     * Sub Routine spinner
     */
    private transient Spinner goSubSpinner;

    /**
     * constructor
     */
    public GoSubInstruction() {
        this.name = "GOSUB";
    }

    /**
     * @param inflater            inflater
     * @param scratchBasicContext program ontext
     * @return kayout for the gosub instruction
     */
    @Override
    public View getLayout(LayoutInflater inflater, ScratchBasicContext scratchBasicContext) {
        scratchBasicContext.updateSubRoutineList();
        View layout = inflater.inflate(R.layout.inst_gosub, null);
        ArrayList<String> routineList = scratchBasicContext.getSubRoutines();
        TextView warningText = (TextView) layout.findViewById(R.id.gosub_text_warning);
        goSubSpinner = (Spinner) layout.findViewById(R.id.gosub_spinner);
        if (routineList.isEmpty()) {
            Toast.makeText(inflater.getContext(), "No sub routines to choose from", Toast.LENGTH_SHORT).show();
            warningText.setVisibility(View.VISIBLE);
            goSubSpinner.setVisibility(View.INVISIBLE);
        } else {
            warningText.setVisibility(View.INVISIBLE);
            goSubSpinner.setVisibility(View.VISIBLE);

        }

        ArrayAdapter<String> routinesAdapter = new ArrayAdapter<>(layout.getContext(), android.R.layout.simple_spinner_dropdown_item, routineList);

        goSubSpinner.setAdapter(routinesAdapter);
        int pos = routineList.indexOf(subLabel);
        goSubSpinner.setSelection(pos);
        return layout;
    }

    /**
     * update the intruction after edit
     */
    @Override
    public void update() {
        Object spinnerItem = goSubSpinner.getSelectedItem();
        if (spinnerItem != null) {

            this.instruction = spinnerItem.toString();
        } else {
            this.subLabel = null;
            this.instruction = "";
        }
        this.subLabel = this.instruction;

    }

    /**
     * @param scratchBasicContext program context
     * @return run the instruction and return the result
     * @throws InstructionRunException
     */
    @Override
    public String run(ScratchBasicContext scratchBasicContext) throws InstructionRunException {
        if (subLabel == null) {
            throw new InstructionRunException("No subroutine chosen to go to");

        }
        ArrayList<Instruction> instructionList = scratchBasicContext.getInstructions();
        Instruction inst;
        for (int i = 0; i < instructionList.size(); i++) {
            inst = instructionList.get(i);
            if (inst instanceof SubInstruction && inst.instruction.equals(this.subLabel)) {
                Stack<Integer> callStack = scratchBasicContext.getCallStack();
                callStack.push(scratchBasicContext.getCurrentLine() + 1);
                nextLine = i;
                return null;
            }
        }
        throw new InstructionRunException("Could not find sub routine " + this.subLabel);
    }

    /**
     * @param scratchBasicContext update the program context pointer
     */
    public void updatePointer(ScratchBasicContext scratchBasicContext) {
        scratchBasicContext.setCurrentLine(nextLine);
    }

    /**
     * @return background colour for instruction
     */
    @Override
    public int getBackgroundColor() {
        if (subLabel == null) {
            return Color.RED;
        }else
        {
            return super.getBackgroundColor();
        }

    }
}
