package com.cmsc434.adi.doodle;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    Resources res;
    ScribbleView scribbleView;
    ImageButton undo, redo;

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
        undo = (ImageButton) findViewById(R.id.undo_btn);
        redo = (ImageButton) findViewById(R.id.redo_btn);
        scribbleView = (ScribbleView) findViewById(R.id.scribble);
        scribbleView.containerActivity = this;
        scribbleView.canUndoRedo();
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

    public void onClickClear(View v) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle(R.string.clear_dialog_title);
        alert.setMessage(R.string.clear_dialog_message);
        alert.setCancelable(true);

        alert.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        alert.setPositiveButton(R.string.clear, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                scribbleView.clear();
            }
        });

        alert.show();
    }

}
