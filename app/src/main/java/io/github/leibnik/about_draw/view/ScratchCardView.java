package io.github.leibnik.about_draw.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

import io.github.leibnik.about_draw.R;

/**
 * Created by Droidroid on 2016/1/22.
 */
public class ScratchCardView extends View {

    private Bitmap mBitmap;
    private int mPenSize;
    private Path mPath;
    private Bitmap mFgBitmap;
    private Paint mPaint;
    private Canvas mCanvas;

    public ScratchCardView(Context context) {
        this(context, null);
    }

    public ScratchCardView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ScratchCardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray ta = context.getTheme().obtainStyledAttributes(attrs, R.styleable.ScratchCardView,defStyleAttr,0);
        for (int i = 0; i < ta.getIndexCount(); i++){
            int attr = ta.getIndex(i);
            switch (attr){
                case R.styleable.ScratchCardView_scratchcard:
                    mBitmap = BitmapFactory.decodeResource(getResources(), ta.getResourceId(attr, 0));
                    break;
                case R.styleable.ScratchCardView_pensize:
                    mPenSize = ta.getDimensionPixelSize(attr,
                            (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,10F,getResources().getDisplayMetrics()));
                    break;
            }
        }
        ta.recycle();
        mFgBitmap = Bitmap.createBitmap(mBitmap.getWidth(),mBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        mPaint = new Paint();
        mPaint.setAlpha(0);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeWidth(mPenSize);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        mPath = new Path();
        mCanvas = new Canvas(mFgBitmap);
        mCanvas.drawColor(Color.GRAY);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mPath.reset();
                mPath.moveTo(event.getX(),event.getY());
                break;
            case MotionEvent.ACTION_MOVE:
                mPath.lineTo(event.getX(),event.getY());
                break;
        }
        mCanvas.drawPath(mPath,mPaint);
        invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(mBitmap, 10, 10, null);
        canvas.drawBitmap(mFgBitmap, 10, 10,null);
    }
}
