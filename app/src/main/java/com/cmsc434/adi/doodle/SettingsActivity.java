package com.cmsc434.adi.doodle;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;

public class SettingsActivity extends AppCompatActivity {
    ScribbleView scribbleView;
    BrushPreviewView brushPreviewView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        setupBrushSizeSeekBar();
    }

    private void setupBrushSizeSeekBar() {
        scribbleView = (ScribbleView) findViewById(R.id.scribble);
        brushPreviewView = (BrushPreviewView) findViewById(R.id.brush_preview_view);

        SeekBar brushSizeSeekBar = (SeekBar) findViewById(R.id.brush_seek_bar);
        SeekBar opacitySeekBar = (SeekBar) findViewById(R.id.opacity_seek_bar);
        SeekBar redSeekBar = (SeekBar) findViewById(R.id.red_seek_bar);
        SeekBar greenSeekBar = (SeekBar) findViewById(R.id.green_seek_bar);
        SeekBar blueSeekBar = (SeekBar) findViewById(R.id.blue_seek_bar);

        BrushSeekListener listener = new BrushSeekListener();

        brushSizeSeekBar.setOnSeekBarChangeListener(listener);
        opacitySeekBar.setOnSeekBarChangeListener(listener);
        redSeekBar.setOnSeekBarChangeListener(listener);
        greenSeekBar.setOnSeekBarChangeListener(listener);
        blueSeekBar.setOnSeekBarChangeListener(listener);

    }

    // MARK: - Listener

    class BrushSeekListener implements SeekBar.OnSeekBarChangeListener {
        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            if (seekBar.getId() == R.id.brush_seek_bar) {
                scribbleView.drawPaint.setStrokeWidth(i);
                brushPreviewView.changeSize(i);
            } else {
                changeColor(seekBar, i);
            }
        }

        private void changeColor(SeekBar seekBar, int i) {
            int paint = scribbleView.paintColor;
            int alpha = Color.alpha(paint);
            int red = Color.red(paint);
            int green = Color.green(paint);
            int blue = Color.blue(paint);

            switch (seekBar.getId()) {
                case R.id.opacity_seek_bar:
                    scribbleView.paintColor = Color.argb(i, red, green, blue);
                    break;
                case R.id.red_seek_bar:
                    scribbleView.paintColor = Color.argb(alpha, i, green, blue);
                    break;
                case R.id.green_seek_bar:
                    scribbleView.paintColor = Color.argb(alpha, red, i, blue);
                    break;
                case R.id.blue_seek_bar:
                    scribbleView.paintColor = Color.argb(alpha, red, green, i);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid seek bar changed");
            }

            scribbleView.drawPaint.setColor(scribbleView.paintColor);
            brushPreviewView.changeColor(scribbleView.paintColor);
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
        }
    }
}
