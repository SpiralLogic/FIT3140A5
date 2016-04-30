package com.srjengbro.scratchbasic;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class GotoEditor extends FragmentActivity {

    private ArrayList<Instruction> instructions = new ArrayList<>();
    private VariableStore variableStore = new VariableStore();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goto_editor);
        Instruction instruction = new RemInstruction();
        instruction.instruction = "Your first line!";
        instructions.add(instruction);


        ListView instructionListView = (ListView) findViewById(R.id.instruction_listView);
        InstructionAdapter instructionAdapter = new InstructionAdapter(instructions, this);
        instructionListView.setAdapter(instructionAdapter);
    }
}
