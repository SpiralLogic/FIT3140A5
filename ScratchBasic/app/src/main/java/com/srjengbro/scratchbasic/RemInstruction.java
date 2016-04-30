package com.srjengbro.scratchbasic;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

/**
 * Created by Maidenii on 29/04/16.
 */
public class RemInstruction extends Instruction {
    protected EditText remText;

    public RemInstruction() {
        name = "REM";
    }

    public View getLayout(LayoutInflater inflater) {
        View layout = inflater.inflate(R.layout.inst_rem, null);
        remText = (EditText) layout.findViewById(R.id.rem_text);
        remText.setText(instruction);
        return layout;
    }
    public void commit() {
        instruction = remText.getText().toString();

    }

    @Override
    public String run() {
        return null;
    }
}
