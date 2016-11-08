package com.cmsc434.adi.doodle;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Adi on 11/6/16.
 */

public class BrushPreviewView extends View {
    Paint brushPaint = new Paint();
    private Canvas canvas;

    public BrushPreviewView(Context context, AttributeSet attrs) {
        super(context, attrs);

        setupPaint();
    }

    // MARK: - Setup

    private void setupPaint() {
        brushPaint.setColor(Color.BLACK);
        brushPaint.setAntiAlias(true);
        brushPaint.setStrokeWidth(getResources().getInteger(R.integer.initial_size));
        brushPaint.setStyle(Paint.Style.STROKE);
        brushPaint.setStrokeJoin(Paint.Join.ROUND);
        brushPaint.setStrokeCap(Paint.Cap.ROUND);
    }

    // MARK: - Overrides

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        canvas = new Canvas(Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (canvas != null) {
            canvas.drawLine(0, getHeight()/2, getWidth(), getHeight()/2, brushPaint);
        }
    }

    // MARK: - Actions

    public void changeColor(int paintColor) {
        brushPaint.setColor(paintColor);
        invalidate();
    }

    public void changeSize(int size) {
        brushPaint.setStrokeWidth(size);
        invalidate();
    }

}
