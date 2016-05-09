package com.srjengbro.scratchbasic;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * @author Sol Jennings
 * @description Main activity for the application. allows creating a new program and loading
 */
public class MainActivity extends AppCompatActivity {

    private String filename;

    /**
     * @param savedInstanceState something to do with android
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        Button loadButton = (Button) findViewById(R.id.load_button);
        if (loadButton != null) {
            loadButton.setOnClickListener(new View.OnClickListener() {
                                              @Override
                                              public void onClick(View v) {
                                                  loadProgram();

                                              }
                                          }

            );
        }

    }

    /**
     *  Create a new program and go the editor activity
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
        builder.setTitle("Filename to load");
        // Set up the input
        final EditText input = new EditText(this);
        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        // Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
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
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();

    }
}
