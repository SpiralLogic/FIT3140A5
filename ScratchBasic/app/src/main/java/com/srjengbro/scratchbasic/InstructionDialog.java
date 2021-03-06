package com.srjengbro.scratchbasic;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.srjengbro.scratchbasic.instructions.Instruction;

/**
 * @author Sol Jennigns & Giles Browne
 * @description Dialogue box for editing an instruction
 */
public class InstructionDialog extends DialogFragment {

    /**
     * The instruction that is being edited
     */
    private Instruction instruction;
    /**
     * The adapter that the dialogue runs from
     */
    private InstructionAdapter adapter;

    /**
     * @param instruction set the instruction text
     */
    public void setInstruction(Instruction instruction) {
        this.instruction = instruction;
    }


    /**
     * @param adapter set the instruction adapter
     */
    public void setAdapter(InstructionAdapter adapter) {
        this.adapter = adapter;
    }

    /**
     * @param savedInstanceState something android needs
     * @return dialog
     */
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View layout = instruction.getLayout(inflater, adapter.getScratchBasicContext());


        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(layout)
                .setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        instruction.update();
                        adapter.notifyDataSetChanged();
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}
