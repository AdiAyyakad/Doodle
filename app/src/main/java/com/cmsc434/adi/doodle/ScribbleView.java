package com.cmsc434.adi.doodle;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
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

    private Path drawPath;
    private Paint drawPaint, canvasPaint;
    private int paintColor = 0xFF660000;
    private final int smallestSize = getResources().getInteger(R.integer.smallest_size);
    private final int largestSize = getResources().getInteger(R.integer.largest_size);
    private int brushSize = getResources().getInteger(R.integer.initial_size);
    private Canvas drawCanvas;
    private Bitmap canvasBitmap;

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
        canvas.drawBitmap(canvasBitmap, 0, 0, canvasPaint);
        canvas.drawPath(drawPath, drawPaint);
    }

    // MARK: - Setup

    private void setupScribble() {

        // Inits
        drawPath = new Path();
        drawPaint = new Paint();

        // Setup paint
        drawPaint.setColor(paintColor);
        drawPaint.setAntiAlias(true);
        drawPaint.setStrokeWidth(brushSize);
        drawPaint.setStyle(Paint.Style.STROKE);
        drawPaint.setStrokeJoin(Paint.Join.ROUND);
        drawPaint.setStrokeCap(Paint.Cap.ROUND);

        // Setup canvas paint
        canvasPaint = new Paint(Paint.DITHER_FLAG);
    }

    // MARK: - Touch Events

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        float touchX = event.getX();
        float touchY = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                drawPath.moveTo(touchX, touchY);
                break;
            case MotionEvent.ACTION_MOVE:
                drawPath.lineTo(touchX, touchY);
                break;
            case MotionEvent.ACTION_UP:
                drawCanvas.drawPath(drawPath, drawPaint);
                drawPath.reset();
                break;
            default:
                return false;
        }

        invalidate();
        return true;

    }

    // MARK: - Seek Bar

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        if (seekBar.getId() == R.id.brush_seek_bar) {
            if (i < smallestSize) {
                i = smallestSize;
            }
            drawPaint.setStrokeWidth(i);
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
    }

}
