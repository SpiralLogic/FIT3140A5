package com.srjengbro.scratchbasic;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

/**
 * Created by Maidenii on 16/04/16.
 */
public class PrintInstruction extends Instruction {

    protected EditText printText;

    public PrintInstruction() {
        name = "PRINT";
    }

    public View getLayout(LayoutInflater inflater) {
        View layout = inflater.inflate(R.layout.inst_print, null);
        printText  = (EditText) layout.findViewById(R.id.printText);
        printText.setText(instruction);
        return layout;
    }

    public void commit() {
        instruction = printText.getText().toString();

    }

}