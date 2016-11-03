package com.cmsc434.adi.doodle;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.MotionEvent;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Adi on 11/2/16.
 */

public class ScribbleView extends View {

    private Path drawPath;
    private Paint drawPaint, canvasPaint;
    private int paintColor = 0xFF660000;
    private int brushSize = R.dimen.smallest_brush;
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
        drawPaint.setStrokeWidth(20);
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
                System.out.println("DOWN");
                drawPath.moveTo(touchX, touchY);
                break;
            case MotionEvent.ACTION_MOVE:
                System.out.println("MOVE");
                drawPath.lineTo(touchX, touchY);
                break;
            case MotionEvent.ACTION_UP:
                System.out.println("UP");
                drawCanvas.drawPath(drawPath, drawPaint);
                drawPath.reset();
                break;
            default:
                return false;
        }

        invalidate();
        return true;

    }

}
