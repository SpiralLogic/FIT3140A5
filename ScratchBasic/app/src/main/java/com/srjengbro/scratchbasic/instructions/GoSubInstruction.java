package com.srjengbro.scratchbasic.instructions;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

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

    private Integer nextLine;
    private String subLabel;
    private transient EditText goSubText;

    public GoSubInstruction() {
        this.name = "GOSUB";
    }

    @Override
    public View getLayout(LayoutInflater inflater, ArrayList instructionList) {
        View layout = inflater.inflate(R.layout.inst_gosub, null);
        goSubText = (EditText) layout.findViewById(R.id.gosub_text);
        goSubText.setText(instruction);
        return layout;
    }

    @Override
    public void update() {
        this.instruction = goSubText.getText().toString();
        this.subLabel = this.instruction;
    }

    @Override
    public String run(ScratchBasicContext scratchBasicContext) throws InstructionRunException {
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

    public Integer getNextLine() {
        return nextLine;
    }
}
