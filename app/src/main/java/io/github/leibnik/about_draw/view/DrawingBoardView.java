package io.github.leibnik.about_draw.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by Droidroid on 2016/1/21.
 */
public class DrawingBoardView extends SurfaceView implements SurfaceHolder.Callback,Runnable{

    private SurfaceHolder mSurfaceHolder;
    private boolean mIsDrawing;
    private Canvas mCanvas;
    private Paint mPaint;
    private Path mPath;

    public DrawingBoardView(Context context) {
        super(context);
        init();
    }

    public DrawingBoardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DrawingBoardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getActionMasked()){
            case MotionEvent.ACTION_DOWN:
                mPath.moveTo(x, y);
                break;
            case MotionEvent.ACTION_MOVE:
                mPath.lineTo(x, y);
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return true;
    }

    private void init() {
        mSurfaceHolder = getHolder();
        mSurfaceHolder.addCallback(this);
        mPaint = new Paint();
        mPath = new Path();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setAntiAlias(true);
        mPaint.setPathEffect(new CornerPathEffect(20));
//        setFocusable(true);
//        setFocusableInTouchMode(true);
//        this.setKeepScreenOn(true);
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
        long start = System.currentTimeMillis();
        while (mIsDrawing){
            draw();
        }
        long end = System.currentTimeMillis();
        if (end - start < 100){
            try {
                Thread.sleep(100 - (end - start));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void draw() {
        try {
            mCanvas = mSurfaceHolder.lockCanvas();
            mCanvas.drawColor(Color.WHITE);
            mCanvas.drawPath(mPath,mPaint);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (mCanvas != null){
                mSurfaceHolder.unlockCanvasAndPost(mCanvas);
            }
        }
    }
}
