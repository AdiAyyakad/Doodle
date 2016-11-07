package com.cmsc434.adi.doodle;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
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
    private Path drawPath = new Path();

    public BrushPreviewView(Context context, AttributeSet attrs) {
        super(context, attrs);

        setupPath();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        canvas = new Canvas(Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (canvas != null) {
            canvas.drawPath(drawPath, brushPaint);
        }
    }

    public void changeColor(int paintColor) {
        brushPaint.setColor(paintColor);
    }

    public void changeSize(int size) {
        brushPaint.setStrokeWidth(size);
    }

    private void setupPath() {
        int center = getHeight()/2;
        int offset = 50;

        drawPath.moveTo(offset, center);
        drawPath.lineTo(getWidth()-offset, center);

        invalidate();
    }


}
