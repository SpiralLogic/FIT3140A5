package com.srjengbro.scratchbasic.instructions;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.srjengbro.scratchbasic.R;
import com.srjengbro.scratchbasic.ScratchBasicContext;

/**
 * @author: Sol Jennings 26356015
 * @date: 14/05/16
 * @description: command for labelling a sub instruction set
 */
public class SubInstruction extends Instruction {

    private transient EditText subText;

    /**
     * constructor
     */
    public SubInstruction() {
        this.name = "SUB";

    }

    /**
     * @param inflater            inflater
     * @param scratchBasicContext program context
     * @return get layout for editing instruction
     */
    @Override
    public View getLayout(LayoutInflater inflater, ScratchBasicContext scratchBasicContext) {
        View layout = inflater.inflate(R.layout.inst_sub, null);
        subText = (EditText) layout.findViewById(R.id.sub_text);
        subText.setText(instruction);
        return layout;
    }

    /**
     * update instruction after edit
     */
    @Override
    public void update() {
        this.instruction = subText.getText().toString();
    }

    /**
     * @param scratchBasicContext program context
     * @return run the instruction and return the result
     * @throws InstructionRunException
     */
    @Override
    public String run(ScratchBasicContext scratchBasicContext) throws InstructionRunException {
        return null;
    }

    /**
     * @return background colour for instruction
     */
    @Override
    public int getBackgroundColor() {
        return Color.CYAN;
    }
}
