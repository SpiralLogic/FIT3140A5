package com.srjengbro.scratchbasic.instructions;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.srjengbro.scratchbasic.R;

/**
 * Created by Maidenii on 29/04/16.
 */
public class RemInstruction extends Instruction {
    protected EditText remText;

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

    @Override
    public void parse(String line) {
        instruction = line;
    }

    @Override
    public String run() {
        return null;
    }
}