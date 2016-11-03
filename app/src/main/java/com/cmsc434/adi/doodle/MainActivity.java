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
        SeekBar opacitySeekBar = (SeekBar) findViewById(R.id.opacity_seek_bar);
        SeekBar redSeekBar = (SeekBar) findViewById(R.id.red_seek_bar);
        SeekBar greenSeekBar = (SeekBar) findViewById(R.id.green_seek_bar);
        SeekBar blueSeekBar = (SeekBar) findViewById(R.id.blue_seek_bar);

        brushSizeSeekBar.setOnSeekBarChangeListener(scribbleView);
        opacitySeekBar.setOnSeekBarChangeListener(scribbleView);
        redSeekBar.setOnSeekBarChangeListener(scribbleView);
        greenSeekBar.setOnSeekBarChangeListener(scribbleView);
        blueSeekBar.setOnSeekBarChangeListener(scribbleView);

    }

}
