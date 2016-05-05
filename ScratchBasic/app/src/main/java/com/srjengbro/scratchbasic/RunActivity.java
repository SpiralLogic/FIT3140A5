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
 * @description
 */
public class RunActivity extends AppCompatActivity {
    /**
     *
     */
    private ArrayList<Instruction> instructions;
    /**
     *
     */
    private VariableStore variableStore;
    /**
     *
     */
    private Boolean running = false;
    /**
     *
     */
    private Handler mHandler = new Handler();
    /**
     *
     */

    private TextView outputText;
    /**
     *
     */
    private TextView lineText;
    /**
     *
     */
    private Integer nextLine = 0;
    /**
     *
     */
    private Button startButton;
    /**
     *
     */
    private Button stopButton;
    /**
     *
     */
    private Button pauseButton;
    /**
     *
     */
    private Button backButton;

    /**
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run);
        ScratchApplication app = (ScratchApplication) getApplication();
        instructions = app.getSratchBasicContext().getInstructions();
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
     *
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
     *
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
     *
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
     *
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
     * @param line
     */
    private void updateCommandOutput(String line) {
        if (line != null && line.length() > 0) {
            outputText.append("\n" + line);
        }
    }

    /**
     *
     */
    private void clearCommandOutput() {
        outputText.setText("");
    }

    /**
     *
     */
    private void backButton() {
        stop();
        this.dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_BACK));
        this.dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_BACK));
    }
}
