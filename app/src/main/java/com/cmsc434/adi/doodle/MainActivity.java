package com.cmsc434.adi.doodle;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    Resources res;
    ScribbleView scribbleView;

    @Override
    protected void onResume() {
        super.onResume();

        scribbleView.changePaintColor(Preferences.sharedInstance.paintColor);
        scribbleView.changeBrushSize(Preferences.sharedInstance.brushSize);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Preferences.sharedInstance = new Preferences(this);
        res = getResources();
        scribbleView = (ScribbleView) findViewById(R.id.scribble);
    }

    public void onClickUndoPath(View v) {
        scribbleView.undo();
    }

    public void onClickRedoPath(View v) {
        scribbleView.redo();
    }

    public void onClickSettings(View v) {
        Preferences.sharedInstance.paintColor = scribbleView.paintColor;
        Preferences.sharedInstance.brushSize = scribbleView.getBrushSize();

        Intent i = new Intent(this, SettingsActivity.class);
        startActivity(i);
    }

}
