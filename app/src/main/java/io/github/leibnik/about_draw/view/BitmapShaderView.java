package io.github.leibnik.about_draw.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

import io.github.leibnik.about_draw.R;

/**
 * Created by Droidroid on 2016/2/15.
 */
public class BitmapShaderView extends View {

    private Drawable mDrawable;
    private Bitmap mBitmap;

    public BitmapShaderView(Context context) {
        this(context, null);
    }

    public BitmapShaderView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BitmapShaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.BitmapShaderView);
        int n = ta.getIndexCount();
        for (int i = 0; i < n; i++) {
            int attr = ta.getIndex(i);
            switch (attr) {
                case R.styleable.BitmapShaderView_pic:
                    mDrawable = ta.getDrawable(R.styleable.BitmapShaderView_pic);
                    mBitmap = ((BitmapDrawable) mDrawable).getBitmap();
                    break;
            }
        }
        ta.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setShader(new BitmapShader(mBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
        canvas.drawCircle(mBitmap.getWidth()/2, mBitmap.getHeight()/2, Math.min(mBitmap.getHeight() / 2, mBitmap.getWidth() / 2), mPaint);
    }
}
