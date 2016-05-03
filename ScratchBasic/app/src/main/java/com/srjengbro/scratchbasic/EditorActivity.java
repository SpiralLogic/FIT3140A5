package com.srjengbro.scratchbasic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
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

public class EditorActivity extends AppCompatActivity {

    private ArrayList<Instruction> instructions;
    private VariableStore variableStore = new VariableStore();
    private ListView instructionListView;
    private InstructionAdapter instructionAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        ScratchApplication app = (ScratchApplication) getApplication();
        instructions = app.instructions;
        app.variableStore = variableStore;


        instructionListView = (ListView) findViewById(R.id.instruction_listView);
        instructionAdapter = new InstructionAdapter(instructions, this);
        instructionListView.setAdapter(instructionAdapter);
        Button runButton = (Button) findViewById(R.id.run_button);
        runButton.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View v) {
                                             Intent i = new Intent(getApplicationContext(), RunActivity.class);
                                             startActivity(i);
                                         }
                                     }
        );
        Button saveButton = (Button) findViewById(R.id.save_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {
                                              try {
                                                  FileOutputStream fos = new FileOutputStream(getFilesDir() + "MyData.ser");
                                                  // Serialize data object to a file
                                                  ObjectOutputStream out = new ObjectOutputStream(fos);
                                                  out.writeObject(instructions);
                                                  out.close();
                                                  fos.close();
                                                  Toast.makeText(v.getContext(), "File Saved", Toast.LENGTH_LONG).show();

                                              } catch (IOException e) {
                                                  e.printStackTrace();

                                                  Toast.makeText(v.getContext(), "Save Failed", Toast.LENGTH_LONG).show();
                                              }

                                          }
                                      }
        );

    }

    @Override
    protected void onResume() {
        super.onResume();
        instructionAdapter.notifyDataSetChanged();

    }
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
