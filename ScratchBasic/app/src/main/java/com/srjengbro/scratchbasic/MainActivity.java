package com.srjengbro.scratchbasic;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.srjengbro.scratchbasic.instructions.Instruction;
import com.srjengbro.scratchbasic.instructions.RemInstruction;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class MainActivity extends Activity {
    protected ArrayList<Instruction> instructions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Button newButton = (Button) findViewById(R.id.new_button);
        newButton.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View v) {
                                             ScratchApplication app = (ScratchApplication) getApplication();
                                             instructions = app.instructions;
                                             instructions.clear();
                                             Instruction instruction = new RemInstruction();
                                             instruction.parse("Your first line!");
                                             instructions.add(instruction);
                                             Intent i = new Intent(getApplicationContext(), EditorActivity.class);
                                             startActivity(i);
                                         }
                                     }
        );
        Button loadButton = (Button) findViewById(R.id.load_button);
        loadButton.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {
                                              FileInputStream fileIn;
                                              ObjectInputStream in;
                                              try {
                                                  fileIn = new FileInputStream(getFilesDir() + "MyData.ser");
                                                  in = new ObjectInputStream(fileIn);
                                                  ArrayList<Instruction> instructions = (ArrayList) in.readObject();
                                                  ScratchApplication app = (ScratchApplication) getApplication();
                                                  app.instructions = instructions;
                                                  in.close();
                                                  fileIn.close();
                                                  Intent i = new Intent(getApplicationContext(), EditorActivity.class);
                                                  startActivity(i);
                                              } catch (FileNotFoundException e) {
                                                  e.printStackTrace();
                                              } catch (IOException e) {
                                                  e.printStackTrace();
                                              } catch (ClassNotFoundException e) {
                                                  e.printStackTrace();

                                              }

                                          }
                                      }

        );

    }
}
