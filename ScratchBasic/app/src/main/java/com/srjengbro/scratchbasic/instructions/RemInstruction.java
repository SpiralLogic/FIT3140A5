package com.srjengbro.scratchbasic.instructions;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.srjengbro.scratchbasic.R;
import com.srjengbro.scratchbasic.VariableStore;

/**
 * @author      Sol Jennings
 * @description
 */
public class RemInstruction extends Instruction {
    protected transient EditText remText;

    public RemInstruction() {
        name = "REM";
        instruction = "This is just a comment!";
    }

    public View getLayout(LayoutInflater inflater) {
        View layout = inflater.inflate(R.layout.inst_rem, null);
        remText = (EditText) layout.findViewById(R.id.rem_text);
        remText.setText(instruction);
        return layout;
    }

    public void update() {
        instruction = remText.getText().toString();

    }

    public void update(String instruction) {
        this.instruction = instruction;
    }
    @Override
    public String run(VariableStore variableStore) throws InstructionRunException {
        return null;
    }


}
