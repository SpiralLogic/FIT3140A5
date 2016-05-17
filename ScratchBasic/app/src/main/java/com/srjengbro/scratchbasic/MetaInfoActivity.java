package com.srjengbro.scratchbasic;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MetaInfoActivity extends AppCompatActivity {

    /**
     * Text box for the file name
     */
    private EditText filenameText;

    /**
     * The box for the author
     */
    private EditText authorText;
    /**
     * The box for the email
     */
    private EditText emailText;
    /**
     * The box for the description
     */

    private EditText descriptionText;

    /**
     * Current application
     */
    private ScratchApplication app;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meta_info);
        app = (ScratchApplication) getApplication();

        filenameText = (EditText) findViewById(R.id.filename_text);
        authorText = (EditText) findViewById(R.id.author_text);
        emailText = (EditText) findViewById(R.id.email_text);
        descriptionText = (EditText) findViewById(R.id.description_text);
        getMetadata();
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
     * Get the metadata for the program
     */
    private void getMetadata() {
        app = ((ScratchApplication) getApplication());
        authorText.setText(app.getScratchBasicContext().getAuthor());
        emailText.setText(app.getScratchBasicContext().getEmail());
        descriptionText.setText(app.getScratchBasicContext().getDescription());
        filenameText.setText(app.getScratchBasicContext().getFilename());
    }

    /**
     * Set the metadata for the program
     */
    private void setMetadata() {
        app = ((ScratchApplication) getApplication());
        app.getScratchBasicContext().setAuthor(authorText.getText().toString());
        app.getScratchBasicContext().setEmail(emailText.getText().toString());
        app.getScratchBasicContext().setDescription(descriptionText.getText().toString());
        app.getScratchBasicContext().setFilename(filenameText.getText().toString());
    }

    /**
     * resumes the activity, make sure that the instructions and the metadata are up to date
     */
    @Override
    protected void onResume() {
        super.onResume();
        getMetadata();
    }

    /**
     * @param keyCode
     * @param event
     * @return finish this activity when the back buttn is pressed
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            setMetadata();
        }
        return super.onKeyDown(keyCode, event);
    }
    /**
     * return to the editor activity making sure the program is stopped first
     */
    private void backButton() {
        setMetadata();
        this.dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_BACK));
        this.dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_BACK));
    }
}
