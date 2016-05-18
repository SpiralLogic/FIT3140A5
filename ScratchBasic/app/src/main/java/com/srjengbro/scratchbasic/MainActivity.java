package com.srjengbro.scratchbasic;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;

/**
 * @author Sol Jennigns & Giles Browne
 * @description Main activity for the application. allows creating a new program and loading
 */
public class MainActivity extends AppCompatActivity {

    /**
     * Program Fiename
     */
    private String filename;

    /**
     * File list spinner
     */
    private Spinner fileListSpinner;

    /**
     * program load button
     */
    private Button loadButton;

    /**
     * @param savedInstanceState something to do with android
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fileListSpinner = (Spinner) findViewById(R.id.file_list_spinner);
        Button newButton = (Button) findViewById(R.id.new_button);

        if (newButton != null) {
            newButton.setOnClickListener(new View.OnClickListener() {
                                             @Override
                                             public void onClick(View v) {
                                                 newProgram();
                                             }
                                         }
            );
        }
        loadButton = (Button) findViewById(R.id.load_button);
        if (loadButton != null) {
            loadButton.setOnClickListener(new View.OnClickListener() {
                                              @Override
                                              public void onClick(View v) {
                                                  loadProgram();

                                              }
                                          }

            );
        }
        setupFileSpinner();


    }

    private void setupFileSpinner() {
        String[] savedFiles = fileList();
        ArrayList<String> fileList = new ArrayList<>();


        for (String file : savedFiles) {
            if (file.endsWith(".sb")) {
                Log.d("File", file);
                fileList.add(file);
            }
        }
        if (fileList.isEmpty()) {
            fileListSpinner.setVisibility(View.INVISIBLE);
            loadButton.setEnabled(false);
        } else {

            ArrayAdapter<String> variablesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, fileList);
            fileListSpinner.setAdapter(variablesAdapter);
            fileListSpinner.setVisibility(View.VISIBLE);
            loadButton.setEnabled(true);
        }
    }

    /**
     * Create a new program and go the editor activity
     */
    private void newProgram() {
        ScratchApplication app = (ScratchApplication) getApplication();
        app.getScratchBasicContext().newProgram();
        Intent i = new Intent(getApplicationContext(), EditorActivity.class);
        startActivity(i);
    }

    /**
     * Load a program and go to the editor activity
     */
    private void loadProgram() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.file_prompt);
        // Set up the input
        final EditText input = new EditText(this);
        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        // Set up the buttons
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                filename = input.getText().toString();
                ScratchApplication app = (ScratchApplication) getApplication();
                if (app.loadProgram(filename)) {
                    Intent i = new Intent(getApplicationContext(), EditorActivity.class);
                    startActivity(i);
                }
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();

    }

    /**
     * When activity is resumed
     */
    @Override
    protected void onResume() {
        super.onResume();
        setupFileSpinner();
    }
}
