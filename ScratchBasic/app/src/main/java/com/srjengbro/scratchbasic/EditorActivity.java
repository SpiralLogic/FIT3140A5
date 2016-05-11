package com.srjengbro.scratchbasic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.srjengbro.scratchbasic.instructions.*;
import java.util.ArrayList;
/**
 * @author      Sol Jennigns & Giles Browne
 * @description Activity Class for editing a program. handles the GUI for the editing activity
 *              on an android device. Allows the addition and deletion of instructions
 *              from a scratch basic program. Also opens a dialogue for editing a
 *              scratch basic instruction
 */
public class EditorActivity extends AppCompatActivity {

    /**
     * the set of instructions of the program
     */
    private ArrayList<Instruction> instructions;
    /**
     * listview to show the instructions on the screen
     */
    private ListView instructionListView;
    /**
     * adapter for the instructions
     */
    private InstructionAdapter instructionAdapter;

    /**
     *  Text box for the file name
     */
    private EditText filenameText;


    /**
     *  The box for the autor
     */
    private EditText authorText;
    /**
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        ScratchApplication app = (ScratchApplication) getApplication();
        instructions = app.getScratchBasicContext().getInstructions();


        instructionListView = (ListView) findViewById(R.id.instruction_listView);
        instructionAdapter = new InstructionAdapter(instructions, this);
        instructionListView.setAdapter(instructionAdapter);

        filenameText = (EditText) findViewById(R.id.filename_text);
        authorText = (EditText) findViewById(R.id.author_text);
        authorText.setText(app.getScratchBasicContext().getAuthor());
        filenameText.setText(app.getScratchBasicContext().getFilename());
        Button runButton = (Button) findViewById(R.id.run_button);
        if (runButton != null) {
            runButton.setOnClickListener(new View.OnClickListener() {
                                             @Override
                                             public void onClick(View v) {
                                                 run();
                                             }
                                         }
            );
        }
        Button saveButton = (Button) findViewById(R.id.save_button);
        if (saveButton != null) {
            saveButton.setOnClickListener(new View.OnClickListener() {
                                              @Override
                                              public void onClick(View v) {
                                                saveProgram();

                                              }
                                          }
            );
        }
        Button backButton = (Button) findViewById(R.id.back_button);
        if (backButton != null) {
            backButton.setOnClickListener(new View.OnClickListener() {
                                             @Override
                                             public void onClick(View v) {
                                                 backButton();
                                             }
                                         }
            );
        }

    }

    /**
     * Run the program by starting the run activity
     */
    private void run() {
        Intent i = new Intent(getApplicationContext(), RunActivity.class);
        startActivity(i);
    }

    /**
     * Save the program
     */
    private void saveProgram() {
        ScratchApplication app =((ScratchApplication) getApplication());
        app.getScratchBasicContext().setFilename(filenameText.getText().toString());
        app.getScratchBasicContext().setAuthor(authorText.getText().toString());
        app.saveProgram();
    }



    /**
     * resumes the activity, make sure that the instructions and the metadata are up to date
     */
    @Override
    protected void onResume() {
        super.onResume();
        ScratchApplication app =((ScratchApplication) getApplication());
        instructionAdapter.notifyDataSetChanged();
        authorText.setText(app.getScratchBasicContext().getAuthor());

        filenameText.setText(app.getScratchBasicContext().getFilename());
    }

    /**
     * @param keyCode
     * @param event
     * @return finish this activity when the back buttn is pressed
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if ((keyCode == KeyEvent.KEYCODE_BACK))
        {
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
    /**
     * return to the editor activity making sure the program is stopped first
     */
    private void backButton() {
        this.dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_BACK));
        this.dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_BACK));
    }
}
