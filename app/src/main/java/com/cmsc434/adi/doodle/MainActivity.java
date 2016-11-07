package com.cmsc434.adi.doodle;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    ScribbleView scribbleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scribbleView = (ScribbleView) findViewById(R.id.scribble);
    }

    public void onClickUndoPath(View v) {
        scribbleView.undo();
    }

    public void onClickRedoPath(View v) {
        scribbleView.redo();
    }

    public void onClickSettings(View v) {
        Intent i = new Intent(MainActivity.this, SettingsActivity.class);
        startActivity(i);
    }

}
