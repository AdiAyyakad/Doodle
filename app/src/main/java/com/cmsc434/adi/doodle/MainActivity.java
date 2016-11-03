package com.cmsc434.adi.doodle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupBrushSizeSeekBar();
    }

    private void setupBrushSizeSeekBar() {
        ScribbleView scribbleView = (ScribbleView) findViewById(R.id.scribble);
        SeekBar brushSizeSeekBar = (SeekBar) findViewById(R.id.brush_seek_bar);

        brushSizeSeekBar.setMax(getResources().getInteger(R.integer.largest_size));
        brushSizeSeekBar.setProgress(getResources().getInteger(R.integer.initial_size));
        brushSizeSeekBar.setOnSeekBarChangeListener(scribbleView);
    }

}
