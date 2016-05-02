package com.srjengbro.scratchbasic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.srjengbro.scratchbasic.instructions.Instruction;
import com.srjengbro.scratchbasic.instructions.InstructionRunException;

import java.util.ArrayList;

public class RunActivity extends AppCompatActivity {
    private ArrayList<Instruction> instructions;
    private VariableStore variableStore;
    private Boolean running = false;
    private TextView outputText;
    private TextView lineText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run);
        ScratchApplication app = (ScratchApplication) getApplication();
        instructions = app.instructions;
        variableStore = app.variableStore;
        outputText = (TextView) findViewById(R.id.output_text);
        Button newButton = (Button) findViewById(R.id.start_button);
        lineText = (TextView) findViewById(R.id.line_text);
        newButton.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View v) {
                                             runInstructions();
                                         }
                                     }
        );

    }

    private void runInstructions() {

        int i = 0;
        running = true;
        while (i < instructions.size() && running) {
            Instruction inst = instructions.get(i);
            String result;
            try {
                lineText.setText("Line : " + ((Integer) i).toString() + ": " + inst.getName() + " " + inst.getInstruction());
                result = inst.run(variableStore);
            } catch (InstructionRunException e) {
                result = "Error on line " + ((Integer) i).toString() + " " + e.getMessage();
                running = false;
            }
            updateCommandOutput(result);
            Integer nextLine = inst.getNextLine();
            if ((null != nextLine) && (nextLine < instructions.size())) {
                i = nextLine;
            }
            i++;
        }
        running = false;

    }

    private void updateCommandOutput(String line) {
        String text = outputText.getText().toString();
        text += "\n" + line;
        outputText.setText(text);
        return;
    }
}
