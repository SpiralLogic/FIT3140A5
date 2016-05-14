package com.srjengbro.scratchbasic.instructions;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.srjengbro.scratchbasic.R;
import com.srjengbro.scratchbasic.VariableStore;

import java.util.ArrayList;

/**
 * @author      Sol Jennigns & Giles Browne
 * @description RemInstruction This class allows for the creation of a REM instruction
 *              It allows an instruction to be just a remark. It inherits from the instruction
 *              class.It handles the layout, running, and processing of any REM instruction.
 */
public class RemInstruction extends Instruction {
    /**
     * textbox for the instruction
     */
    protected transient EditText remText;

    /**
     * constructor
     */
    public RemInstruction() {
        name = "REM";
        instruction = "This is just a comment!";
    }

    /**
     * @param inflater inflater
     * @param instructionList
     * @return get layout for the instruction
     */
    public View getLayout(LayoutInflater inflater, ArrayList instructionList) {
        View layout = inflater.inflate(R.layout.inst_rem, null);
        remText = (EditText) layout.findViewById(R.id.rem_text);
        remText.setText(instruction);
        return layout;
    }

    /**
     * update after edit
     */
    public void update() {
        instruction = remText.getText().toString();

    }

    /**
     * @param instruction allow instruction to be aded by string
     */
    public void update(String instruction) {
        this.instruction = instruction;
    }

    /**
     * @param variableStore variables
     * @return execute the instruction
     * @throws InstructionRunException
     */
    @Override
    public String run(VariableStore variableStore) throws InstructionRunException {
        return null;
    }


}
