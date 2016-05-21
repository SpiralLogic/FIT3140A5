package com.srjengbro.scratchbasic;

import android.app.Application;
import android.content.Intent;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author Sol Jennigns & Giles Browne
 * @description overides the main android application to store the state of the program
 */
public class ScratchApplication extends Application {

    /**
     * the context of the program, holds all of the metadata and insturction list
     */
    private ScratchBasicContext scratchBasicContext = new ScratchBasicContext();

    /**
     * @return the context of the program
     */
    public ScratchBasicContext getScratchBasicContext() {
        return this.scratchBasicContext;
    }

    /**
     * used when loading
     *
     * @param scratchBasicContext set the context of the program
     */
    public void setScratchBasicContext(ScratchBasicContext scratchBasicContext) {
        this.scratchBasicContext = scratchBasicContext;
    }

    /**
     * save the current prgoram
     */
    public void saveProgram() {
        try {
            FileOutputStream fos = openFileOutput(scratchBasicContext.getFilename() + ".sb", MODE_PRIVATE);
            // Serialize data object to a file
            ObjectOutputStream out = new ObjectOutputStream(fos);
            out.writeObject(getScratchBasicContext());
            out.close();
            fos.close();
            Toast.makeText(getApplicationContext(), "File Saved", Toast.LENGTH_LONG).show();

        } catch (IOException e) {
            e.printStackTrace();

            Toast.makeText(getApplicationContext(), "Save Failed", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * load a specific filename if it exists
     */
    public Boolean loadProgram(String filename) {

        FileInputStream fileIn;
        ObjectInputStream in;
        try {
            fileIn = openFileInput(filename + ".sb");
            in = new ObjectInputStream(fileIn);
            setScratchBasicContext((ScratchBasicContext) in.readObject());
            in.close();
            fileIn.close();

        } catch (FileNotFoundException e) {
            Toast.makeText(getApplicationContext(), "Load failed, file does not exist", Toast.LENGTH_SHORT).show();
            return false;
        } catch (IOException e) {
            Toast.makeText(getApplicationContext(), "Load failed, cannot read file", Toast.LENGTH_SHORT).show();
            return false;
        } catch (ClassNotFoundException e) {
            Toast.makeText(getApplicationContext(), "Load failed, critical error", Toast.LENGTH_SHORT).show();
            return false;
        } catch (ClassCastException e) {
            Toast.makeText(getApplicationContext(), "Load failed, invalid file data", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
