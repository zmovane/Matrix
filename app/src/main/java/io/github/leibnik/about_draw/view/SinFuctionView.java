package io.github.leibnik.about_draw.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by Droidroid on 2016/1/22.
 */
public class SinFuctionView extends SurfaceView implements SurfaceHolder.Callback, Runnable {

    private SurfaceHolder mHolder;
    private Canvas mCanvas;
    private Paint mPaint;
    private Path mPath;
    private boolean mIsDrawing;
    private int x, y;

    public SinFuctionView(Context context) {
        super(context);
        init();
    }

    public SinFuctionView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SinFuctionView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mHolder = getHolder();
        mHolder.addCallback(this);
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setAntiAlias(true);
        mPaint.setPathEffect(new CornerPathEffect(20));
        mPath = new Path();
        mPath.moveTo(0, 400);
//        setFocusable(true);
//        setFocusableInTouchMode(true);
//        setKeepScreenOn(true);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mIsDrawing = true;
        new Thread(this).start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        mIsDrawing = false;
    }

    @Override
    public void run() {
        long start = (long) System.currentTimeMillis();
        while (mIsDrawing) {
            x += 1;
            y = (int) (100 * Math.sin(2 * x * Math.PI / 180) + 400);
            mPath.lineTo(x, y);
            draw();
        }
        long end = (long) System.currentTimeMillis();
        if (end - start < 100) {
            try {
                Thread.sleep(100 - (end - start));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void draw() {
        try {
            mCanvas = mHolder.lockCanvas();
            mCanvas.drawColor(Color.WHITE);
            mCanvas.drawPath(mPath,mPaint);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (mCanvas != null){
                mHolder.unlockCanvasAndPost(mCanvas);
            }
        }

    }
}
