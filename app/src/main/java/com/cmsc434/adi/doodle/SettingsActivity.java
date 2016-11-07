package com.cmsc434.adi.doodle;

import android.content.res.Resources;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;

public class SettingsActivity extends AppCompatActivity {
    Resources res;
    BrushPreviewView brushPreviewView;
    int paintColor;
    int brushSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        res = getResources();
        getIntentInfo();
        setupBrushSizeSeekBar();
    }

    // MARK: - Setup

    private void getIntentInfo() {
        paintColor = Preferences.sharedInstance.paintColor;
        brushSize = Preferences.sharedInstance.brushSize;
    }

    private void setupBrushSizeSeekBar() {
        brushPreviewView = (BrushPreviewView) findViewById(R.id.brush_preview_view);
        brushPreviewView.changeSize(brushSize);
        brushPreviewView.changeColor(paintColor);

        SeekBar brushSizeSeekBar = (SeekBar) findViewById(R.id.brush_seek_bar);
        SeekBar opacitySeekBar = (SeekBar) findViewById(R.id.opacity_seek_bar);
        SeekBar redSeekBar = (SeekBar) findViewById(R.id.red_seek_bar);
        SeekBar greenSeekBar = (SeekBar) findViewById(R.id.green_seek_bar);
        SeekBar blueSeekBar = (SeekBar) findViewById(R.id.blue_seek_bar);

        brushSizeSeekBar.setProgress(brushSize);
        opacitySeekBar.setProgress(Color.alpha(paintColor));
        redSeekBar.setProgress(Color.red(paintColor));
        greenSeekBar.setProgress(Color.green(paintColor));
        blueSeekBar.setProgress(Color.blue(paintColor));

        BrushSeekListener listener = new BrushSeekListener();

        brushSizeSeekBar.setOnSeekBarChangeListener(listener);
        opacitySeekBar.setOnSeekBarChangeListener(listener);
        redSeekBar.setOnSeekBarChangeListener(listener);
        greenSeekBar.setOnSeekBarChangeListener(listener);
        blueSeekBar.setOnSeekBarChangeListener(listener);

    }

    // MARK: - On Click

    public void onClickClose(View v) {
        Preferences.sharedInstance.paintColor = paintColor;
        Preferences.sharedInstance.brushSize = brushSize;
        finish();
    }

    // MARK: - Listener

    class BrushSeekListener implements SeekBar.OnSeekBarChangeListener {
        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            if (seekBar.getId() == R.id.brush_seek_bar) { // change brush size
                brushSize = i;
                brushPreviewView.changeSize(i);
            } else { // change paintColor
                int alpha = Color.alpha(paintColor);
                int red = Color.red(paintColor);
                int green = Color.green(paintColor);
                int blue = Color.blue(paintColor);

                switch (seekBar.getId()) {
                    case R.id.opacity_seek_bar:
                        paintColor = Color.argb(i, red, green, blue);
                        break;
                    case R.id.red_seek_bar:
                        paintColor = Color.argb(alpha, i, green, blue);
                        break;
                    case R.id.green_seek_bar:
                        paintColor = Color.argb(alpha, red, i, blue);
                        break;
                    case R.id.blue_seek_bar:
                        paintColor = Color.argb(alpha, red, green, i);
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid seek bar changed");
                }


                brushPreviewView.changeColor(paintColor);
            }
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
        }
    }
}
