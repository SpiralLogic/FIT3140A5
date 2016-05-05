package com.srjengbro.scratchbasic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import com.srjengbro.scratchbasic.instructions.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
/**
 * @author      Sol Jennings
 * @description Activity for editing a program
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
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        ScratchApplication app = (ScratchApplication) getApplication();
        instructions = app.getSratchBasicContext().getInstructions();


        instructionListView = (ListView) findViewById(R.id.instruction_listView);
        instructionAdapter = new InstructionAdapter(instructions, this);
        instructionListView.setAdapter(instructionAdapter);
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
                                                  save();

                                              }
                                          }
            );
        }

    }

    /**
     *
     */
    private void run() {
        Intent i = new Intent(getApplicationContext(), RunActivity.class);
        startActivity(i);
    }

    /**
     *
     */
    private void save() {
        try {
            FileOutputStream fos = new FileOutputStream(getFilesDir() + "MyData.ser");
            // Serialize data object to a file
            ObjectOutputStream out = new ObjectOutputStream(fos);
            out.writeObject(((ScratchApplication) getApplication()).getSratchBasicContext());
            out.close();
            fos.close();
            Toast.makeText(getApplicationContext(), "File Saved", Toast.LENGTH_LONG).show();

        } catch (IOException e) {
            e.printStackTrace();

            Toast.makeText(getApplicationContext(), "Save Failed", Toast.LENGTH_LONG).show();
        }
    }

    /**
     *
     */
    @Override
    protected void onResume() {
        super.onResume();
        instructionAdapter.notifyDataSetChanged();

    }

    /**
     * @param keyCode
     * @param event
     * @return
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

}
