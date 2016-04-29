package com.srjengbro.scratchbasic;


import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by Maidenii on 12/04/16.
 */
public abstract class Instruction {
    protected String name = "UNKNOWN";

    public String getName() {
        return name;
    }

    public String getInstruction() {
        return instruction;
    }

    public String instruction;


    public View getLayout(LayoutInflater inflater) {
        View layout = inflater.inflate(R.layout.instruction_line, null);
        return layout;
    }

    public void commit() {

        return;
    }

    public void parse() {
        return;
    }
}
