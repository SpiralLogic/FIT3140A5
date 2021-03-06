package com.srjengbro.scratchbasic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;

/**
 * @author Sol Jennigns & Giles Browne
 * @description Main activity for the application. allows creating a new program and loading
 */
public class MainActivity extends AppCompatActivity {


    /**
     * File list spinner
     */
    private Spinner fileListSpinner;

    /**
     * program load button
     */
    private Button loadButton;
    /**
     * program edit button
     */
    private Button editButton;

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
        editButton = (Button) findViewById(R.id.edit_button);
        if (editButton != null) {
            editButton.setOnClickListener(new View.OnClickListener() {
                                              @Override
                                              public void onClick(View v) {
                                                  openEditor();

                                              }
                                          }

            );
        }
        setupFileSpinner();


    }

    /**
     * Load the list of saved files for selection
     */
    private void setupFileSpinner() {
        String[] savedFiles = fileList();
        ArrayList<String> fileList = new ArrayList<>();


        for (String file : savedFiles) {
            if (file.endsWith(".sb")) {

                fileList.add(file.substring(0, file.length() - 3));
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
        openEditor();
    }

    /**
     * Load a program and go to the editor activity
     */
    private void loadProgram() {
        Object spinnerItem = fileListSpinner.getSelectedItem();
        if (spinnerItem != null) {
            ScratchApplication app = (ScratchApplication) getApplication();
            if (app.loadProgram(spinnerItem.toString())) {
                openEditor();
            }
        }


    }

    /**
     * Opens edit activity
     */
    private void openEditor() {
        Intent i = new Intent(getApplicationContext(), EditorActivity.class);
        startActivity(i);
    }

    /**
     * When activity is resumed
     */
    @Override
    protected void onResume() {
        super.onResume();
        setupFileSpinner();
        ScratchApplication app = (ScratchApplication) getApplication();
        ScratchBasicContext sbc = app.getScratchBasicContext();
        if (sbc == null || sbc.getInstructions().size() == 0) {
            editButton.setVisibility(View.INVISIBLE);
        } else {

            editButton.setVisibility(View.VISIBLE);
        }
    }
}
