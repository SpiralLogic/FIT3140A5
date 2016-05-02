package com.srjengbro.scratchbasic;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.srjengbro.scratchbasic.instructions.*;


import java.util.ArrayList;

public class EditorActivity extends FragmentActivity {

    private ArrayList<Instruction> instructions = new ArrayList<>();
    private VariableStore variableStore = new VariableStore();
    private ListView instructionListView;
    private TextView commandOutput;
    private Boolean running = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        ScratchApplication app = (ScratchApplication) getApplication();

        app.instructions = instructions;
        app.variableStore = variableStore;


        final Instruction instruction = new RemInstruction();
        instruction.parse("Your first line!");
        instructions.add(instruction);


        instructionListView = (ListView) findViewById(R.id.instruction_listView);
        InstructionAdapter instructionAdapter = new InstructionAdapter(instructions, this);
        instructionListView.setAdapter(instructionAdapter);

        commandOutput = (TextView) findViewById(R.id.command_output);
        Button newButton = (Button) findViewById(R.id.run_button);

        newButton.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View v) {

                                             Intent i = new Intent(getApplicationContext(), RunActivity.class);
                                             startActivity(i);

                                         }


                                     }
        );

    }


}
