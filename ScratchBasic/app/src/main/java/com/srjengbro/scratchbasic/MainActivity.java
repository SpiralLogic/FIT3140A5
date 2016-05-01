package com.srjengbro.scratchbasic;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button newButton = (Button) findViewById(R.id.new_button);

        newButton.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View v) {
                                             Intent i = new Intent(getApplicationContext(), EditorActivity.class);
                                             startActivity(i);
                                         }
                                     }
        );

    }
}
