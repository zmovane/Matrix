package io.github.leibnik.about_draw.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

import io.github.leibnik.about_draw.R;

/**
 * Created by Droidroid on 2016/2/15.
 */
public class ReflectionView extends View {

    private Drawable mDrawable;
    private Bitmap mSrcBitmap;
    private Bitmap mRefBitmap;
    private LinearGradient mLineGradient;
    private PorterDuffXfermode mXfermode;
    private Paint mPaint;

    public ReflectionView(Context context) {
        this(context, null);
    }

    public ReflectionView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ReflectionView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.ReflectionView);
        int n = ta.getIndexCount();
        for (int i = 0; i < n; i++) {
            int attr = ta.getIndex(i);
            switch (attr) {
                case R.styleable.ReflectionView_img:
                    mDrawable = ta.getDrawable(R.styleable.ReflectionView_img);
                    mSrcBitmap = ((BitmapDrawable) mDrawable).getBitmap();
                    break;
            }
        }
        ta.recycle();
        Matrix matrix = new Matrix();
        matrix.setScale(1.0F, -1.0F);
        mRefBitmap = Bitmap.createBitmap(mSrcBitmap, 0, 0, mSrcBitmap.getWidth(), mSrcBitmap.getHeight()
                , matrix, true);
        mLineGradient = new LinearGradient(0, mSrcBitmap.getHeight(), 0, mSrcBitmap.getHeight() + mSrcBitmap.getHeight() / 4,
                0xDD00ffff, 0x1000ffff, Shader.TileMode.CLAMP);
        mXfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);
        mPaint = new Paint();
        mPaint.setXfermode(mXfermode);
        mPaint.setShader(mLineGradient);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.BLACK);
        canvas.drawBitmap(mSrcBitmap, 0, 0, null);
        canvas.drawBitmap(mRefBitmap, 0, mSrcBitmap.getHeight(), null);
        canvas.drawRect(0, mSrcBitmap.getHeight(), mSrcBitmap.getWidth(), mSrcBitmap.getHeight() * 2, mPaint);
    }
}
