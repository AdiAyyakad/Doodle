package com.cmsc434.adi.doodle;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

/**
 * Created by Adi on 11/2/16.
 */

public class ScribbleView extends View implements SeekBar.OnSeekBarChangeListener {

    private Resources res = getResources();
    private Path drawPath;
    private Paint drawPaint, canvasPaint;
    private int paintColor = 0xFF660000;
    private Canvas drawCanvas;
    private Bitmap canvasBitmap;
    private ArrayList<APath> paths = new ArrayList<>();
    private ArrayList<APath> undonePaths = new ArrayList<>();

    public ScribbleView(Context context, AttributeSet attrs){
        super(context, attrs);

        setupScribble();
    }

    // MARK: - View Overrides

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        canvasBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        drawCanvas = new Canvas(canvasBitmap);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // canvas.drawBitmap(canvasBitmap, 0, 0, canvasPaint);
        for (APath path : paths) {
            canvas.drawPath(path.path, path.paint);
        }
    }

    // MARK: - Setup

    private void setupScribble() {

        // Inits
        drawPath = new Path();
        drawPaint = new Paint();

        // Setup paint
        drawPaint.setColor(paintColor);
        drawPaint.setAntiAlias(true);
        drawPaint.setStrokeWidth(res.getInteger(R.integer.initial_size));
        drawPaint.setStyle(Paint.Style.STROKE);
        drawPaint.setStrokeJoin(Paint.Join.ROUND);
        drawPaint.setStrokeCap(Paint.Cap.ROUND);

        // Setup canvas paint
        canvasPaint = new Paint(Paint.DITHER_FLAG);
    }

    // MARK: - Touch Event Handling (Scribbling)

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        float touchX = event.getX();
        float touchY = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                undonePaths.clear();
                drawPath.moveTo(touchX, touchY);
                break;
            case MotionEvent.ACTION_MOVE:
                drawPath.lineTo(touchX, touchY);
                break;
            case MotionEvent.ACTION_UP:
                drawCanvas.drawPath(drawPath, drawPaint);
                paths.add(new APath(drawPath, new Paint(drawPaint)));
                drawPath = new Path();
                break;
            default:
                return false;
        }

        invalidate();
        return true;

    }

    // MARK: - Undo/Redo

    public void undo() {
        if (paths.isEmpty()) return;

        undonePaths.add(paths.get(paths.length() - 1));
        paths.remove(paths.length()-1);

        invalidate();
    }

    public void redo() {
        if (undonePaths.isEmpty()) return;

        paths.add(undonePaths.get(undonePaths.length() - 1));
        undonePaths.remove(undonePaths.length()-1);

        invalidate();
    }

    // MARK: - Seek Bar

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        if (seekBar.getId() == R.id.brush_seek_bar) {
            drawPaint.setStrokeWidth(i);
        } else {
            changeColor(seekBar, i);
        }
    }

    private void changeColor(SeekBar seekBar, int i) {
        switch (seekBar.getId()) {
            case R.id.opacity_seek_bar:
                paintColor = Color.argb(i, Color.red(paintColor), Color.green(paintColor), Color.blue(paintColor));
                break;
            case R.id.red_seek_bar:
                paintColor = Color.argb(Color.alpha(paintColor), i, Color.green(paintColor), Color.blue(paintColor));
                break;
            case R.id.green_seek_bar:
                paintColor = Color.argb(Color.alpha(paintColor), Color.red(paintColor), i, Color.blue(paintColor));
                break;
            case R.id.blue_seek_bar:
                paintColor = Color.argb(Color.alpha(paintColor), Color.red(paintColor), Color.green(paintColor), i);
                break;
            default:
                throw new IllegalArgumentException("Invalid seek bar changed");
        }

        drawPaint.setColor(paintColor);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
    }

}
