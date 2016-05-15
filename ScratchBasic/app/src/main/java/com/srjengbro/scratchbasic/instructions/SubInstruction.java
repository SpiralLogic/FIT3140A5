package com.srjengbro.scratchbasic.instructions;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.srjengbro.scratchbasic.R;
import com.srjengbro.scratchbasic.ScratchBasicContext;

import java.util.ArrayList;

/**
 * @author: Sol Jennings 26356015
 * @date: 14/05/16
 * @description: command for labelling a sub instruction set
 */
public class SubInstruction extends Instruction {

    private transient EditText subText;

    /**
     * contructor
     */
    public SubInstruction() {
        this.name = "SUB";

    }

    @Override
    public View getLayout(LayoutInflater inflater, ArrayList instructionList) {
        View layout = inflater.inflate(R.layout.inst_sub, null);
        subText = (EditText) layout.findViewById(R.id.sub_text);
        subText.setText(instruction);
        return layout;
    }

    @Override
    public void update() {
        this.instruction = subText.getText().toString();


    }

    @Override
    public String run(ScratchBasicContext scratchBasicContext) throws InstructionRunException {
        return null;
    }
}