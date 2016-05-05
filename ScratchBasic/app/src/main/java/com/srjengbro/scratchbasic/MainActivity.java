package com.srjengbro.scratchbasic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * @author      Sol Jennings
 * @description
 */
public class MainActivity extends AppCompatActivity {

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

    private void newProgram() {
        ScratchApplication app = (ScratchApplication) getApplication();
        app.getSratchBasicContext().newProgram();
        Intent i = new Intent(getApplicationContext(), EditorActivity.class);
        startActivity(i);
    }

    private void loadProgram() {

        FileInputStream fileIn;
        ObjectInputStream in;
        try {
            fileIn = new FileInputStream(getFilesDir() + "MyData.ser");
            in = new ObjectInputStream(fileIn);
            ScratchApplication app = (ScratchApplication) getApplication();
            app.setScratchBasicContext((ScratchBasicContext) in.readObject());
            in.close();
            fileIn.close();
            Intent i = new Intent(getApplicationContext(), EditorActivity.class);
            startActivity(i);
        } catch (FileNotFoundException e) {
            Toast.makeText(getApplicationContext(), "Load failed, no save program", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            Toast.makeText(getApplicationContext(), "Load failed, cannot read file", Toast.LENGTH_LONG).show();

        } catch (ClassNotFoundException e) {
            Toast.makeText(getApplicationContext(), "Load failed, critical error", Toast.LENGTH_LONG).show();

        } catch (ClassCastException e) {
            Toast.makeText(getApplicationContext(), "Load failed, invalid file data", Toast.LENGTH_LONG).show();

        }
    }
}
