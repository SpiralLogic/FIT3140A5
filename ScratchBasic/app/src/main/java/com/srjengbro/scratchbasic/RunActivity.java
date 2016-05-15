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
 * @author Sol Jennigns & Giles Browne
 * @description Run a program activity. Allows a program to be run and the output observed.
 * A program can be in 3 states, stopped paused or running.  Each instruction is
 * executed after a time delay to ensure that the UI thread isn't locked.
 */
public class RunActivity extends AppCompatActivity {

    /**
     * constant for the time between instruction evaluations
     */
    private int TIME_BETWEEN_INSTRUCTIONS = 500;
    /**
     * list of instructions to run
     */
    private ArrayList<Instruction> instructions;

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
     * Scratch Basic context
     */
    private ScratchBasicContext scratchBasicContext;
    /**
     * Create the activity
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run);
        ScratchApplication app = (ScratchApplication) getApplication();
        scratchBasicContext = app.getScratchBasicContext();
        instructions = scratchBasicContext.getInstructions();
        scratchBasicContext.getVariableStore().clear();
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
                    scratchBasicContext.setCurrentLine(0);
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
        scratchBasicContext.setCurrentLine(0);
        stopButton.setEnabled(false);
        startButton.setEnabled(true);
        pauseButton.setEnabled(false);
        scratchBasicContext.getVariableStore().clear();
    }

    /**
     * handler for run button being expression
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
        Integer line = scratchBasicContext.getCurrentLine();
        Instruction inst = instructions.get(line);
        String result;
        try {
            lineText.setText("Line " + scratchBasicContext.getCurrentLine().toString() + ": " + inst.getName() + " " + inst.getInstruction());
            result = inst.run(scratchBasicContext);
        } catch (InstructionRunException e) {
            String error = "Error on line " + scratchBasicContext.getCurrentLine().toString() + ": " + e.getMessage();
            stop();
            updateCommandOutput(error);
            return;
        }
        updateCommandOutput(result);
        inst.updatePointer(scratchBasicContext);
        if (scratchBasicContext.getCurrentLine() >= instructions.size()) {
            updateCommandOutput("Program Finished!");
            stop();
        }
        if (running) {
            mHandler.postDelayed(new Runnable() {
                public void run() {
                    runNextInstruction();
                }
            }, TIME_BETWEEN_INSTRUCTIONS);
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
