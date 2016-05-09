package com.srjengbro.scratchbasic;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.srjengbro.scratchbasic.instructions.*;
import java.util.ArrayList;
/**
 * @author      Sol Jennings
 * @description Run a program activity. Allows a program to be run and the output observed
 */
public class RunActivity extends AppCompatActivity {
    /**
     * list of instructions to run
     */
    private ArrayList<Instruction> instructions;
    /**
     * List of variables created uring execution
     */
    private VariableStore variableStore;
    /**
     * Whether the program is running
     */
    private Boolean running = false;
    /**
     * Handler for executing each instruction after a period of time
     */
    private Handler mHandler = new Handler();
    /**
     * The command ouput text box
     */

    private TextView outputText;
    /**
     * the text box showing the current line of execution
     */
    private TextView lineText;
    /**
     * the next line to execute
     */
    private Integer nextLine = 0;
    /**
     * button to start the prgram
     */
    private Button startButton;
    /**
     * button to stop the program
     */
    private Button stopButton;
    /**
     * button to pause the program
     */
    private Button pauseButton;
    /**
     * button to return to the editor activity
     */
    private Button backButton;

    /**
     * Create the activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run);
        ScratchApplication app = (ScratchApplication) getApplication();
        instructions = app.getScratchBasicContext().getInstructions();
        variableStore = new VariableStore();
        outputText = (TextView) findViewById(R.id.output_text);
        outputText.setMovementMethod(new ScrollingMovementMethod());
        startButton = (Button) findViewById(R.id.start_button);
        stopButton = (Button) findViewById(R.id.stop_button);
        pauseButton = (Button) findViewById(R.id.pause_button);
        backButton = (Button) findViewById(R.id.back_button);
        lineText = (TextView) findViewById(R.id.line_text);
        if (startButton != null) {
            startButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clearCommandOutput();
                    run();
                }
            });
        }
        if (stopButton != null)
            stopButton.setOnClickListener(new View.OnClickListener() {
                                              @Override
                                              public void onClick(View v) {
                                                  stop();

                                              }
                                          }
            );
        if (pauseButton != null)
            pauseButton.setOnClickListener(new View.OnClickListener() {
                                               @Override
                                               public void onClick(View v) {
                                                   pause();
                                               }
                                           }
            );
        if (backButton != null)
            backButton.setOnClickListener(new View.OnClickListener() {
                                              @Override
                                              public void onClick(View v) {
                                                  backButton();
                                              }
                                          }
            );


    }


    /**
     * handler for pause button being pressed
     */
    private void pause() {
        if (running) {
            running = false;
            pauseButton.setText(R.string.restart);
        } else {
            pauseButton.setText(R.string.pause);
            running = true;
            runNextInstruction();
        }
    }

    /**
     * handler for stop button being pressed
     */
    private void stop() {
        running = false;
        nextLine = 0;
        stopButton.setEnabled(false);
        startButton.setEnabled(true);
        pauseButton.setEnabled(false);
        variableStore.clear();
    }

    /**
     *handler for run button being pression
     */
    private void run() {
        running = true;
        runNextInstruction();
        startButton.setEnabled(false);
        stopButton.setEnabled(true);
        pauseButton.setEnabled(true);
        pauseButton.setText(R.string.pause);
    }

    /**
     * run the next instruction, will add a time delay before the next instruction is run
     */
    private void runNextInstruction() {
        if (!running) {
            return;
        }
        Instruction inst = instructions.get(nextLine);
        String result;
        try {
            lineText.setText("Line " + nextLine.toString() + ": " + inst.getName() + " " + inst.getInstruction());
            result = inst.run(variableStore);
        } catch (InstructionRunException e) {
            result = "Error on line " + nextLine.toString() + ": " + e.getMessage();
            running = false;
        }
        updateCommandOutput(result);
        if (null == inst.getNextLine()) {
            nextLine += 1;
        } else {
            nextLine = inst.getNextLine();
        }
        if (nextLine >= instructions.size()) {
            stop();
        }
        if (running) {
            mHandler.postDelayed(new Runnable() {
                public void run() {
                    runNextInstruction();
                }
            }, 500);
        }

    }

    /**
     * @param line update the command output text
     */
    private void updateCommandOutput(String line) {
        if (line != null && line.length() > 0) {
            outputText.append("\n" + line);
        }
    }

    /**
     * clear the command output text
     */
    private void clearCommandOutput() {
        outputText.setText("");
    }

    /**
     * return to the editor activity making sure the program is stopped first
     */
    private void backButton() {
        stop();
        this.dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_BACK));
        this.dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_BACK));
    }
}
