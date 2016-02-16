package io.github.leibnik.about_draw.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

import io.github.leibnik.about_draw.R;

/**
 * Created by Droidroid on 2016/1/22.
 */
public class RoundPictureView extends View {

    private Drawable mPicture;
    private int mRadius;
    private Bitmap mOUt, mBitmap;
    private Paint mPaint;

    public RoundPictureView(Context context) {
        this(context, null);
    }

    public RoundPictureView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundPictureView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.roundpicture);
        int n = ta.getIndexCount();
        for (int i = 0; i < n; i++) {
            int attr = ta.getIndex(i);
            switch (attr) {
                case R.styleable.roundpicture_picture:
                    mPicture = ta.getDrawable(R.styleable.roundpicture_picture);
//        if (mPicture instanceof ColorDrawable) {
//            ((ColorDrawable) mPicture).getColor();
//            mBitmap = ((BitmapDrawable)(((ColorDrawable) mPicture).mutate())).getBitmap();
//        } else {
//            mBitmap = ((BitmapDrawable) mPicture).getBitmap();
//        }
                    break;
                case R.styleable.roundpicture_radius:
                    mRadius = ta.getDimensionPixelSize(R.styleable.roundpicture_radius, 20);
                    break;
            }
        }
        ta.recycle();
        mBitmap = ((BitmapDrawable) mPicture).getBitmap();
        mOUt = Bitmap.createBitmap(mBitmap.getWidth(), mBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(mOUt);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        canvas.drawRoundRect(new RectF(0, 0, mOUt.getWidth(), mOUt.getHeight()), mRadius, mRadius, mPaint);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(mBitmap, 0, 0, mPaint);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(mOUt, 10, 10, null);
    }
}
