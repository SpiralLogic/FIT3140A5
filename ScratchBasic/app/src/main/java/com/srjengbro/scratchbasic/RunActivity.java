package com.srjengbro.scratchbasic;

import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
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
    private Integer nextLine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run);
        ScratchApplication app = (ScratchApplication) getApplication();
        instructions = app.instructions;
        variableStore = app.variableStore;
        outputText = (TextView) findViewById(R.id.output_text);
        outputText.setMovementMethod(new ScrollingMovementMethod());
        Button newButton = (Button) findViewById(R.id.start_button);
        Button stopButton = (Button) findViewById(R.id.stop_button);
        lineText = (TextView) findViewById(R.id.line_text);
        newButton.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View v) {
                                             runInstructions();
                                         }
                                     }
        );
        stopButton.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {
                                              running = false;
                                          }
                                      }
        );


    }

    private Handler mHandler = new Handler();

    private void runInstructions() {
        running = true;
        nextLine = 0;
        runInstruction();
    }

    private void runInstruction() {
        if (!running) {
            return;
        }
        Instruction inst = instructions.get(nextLine);
        String result;
        try {
            lineText.setText("Line " + nextLine.toString() + ": " + inst.getName() + " " + inst.getInstruction());
            result = inst.run(variableStore);
        } catch (InstructionRunException e) {
            result = "Error on line " + nextLine.toString() + " " + e.getMessage();
            running = false;
        }
        updateCommandOutput(result);
        if (null == inst.getNextLine()) {
            nextLine += 1;
        } else {
            nextLine = inst.getNextLine();
        }
        if (nextLine >= instructions.size()) {
            running = false;
        }
        if (running) {
            mHandler.postDelayed(new Runnable() {
                public void run() {
                    runInstruction();
                }
            }, 500);
        }

    }

    private void updateCommandOutput(String line) {
        if (line.length() > 0) {
            outputText.append("\n" + line);
        }

    }
}
