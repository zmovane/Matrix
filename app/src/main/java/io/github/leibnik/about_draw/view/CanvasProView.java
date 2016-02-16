package io.github.leibnik.about_draw.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Droidroid on 2016/2/15.
 */
public class CanvasProView extends View {

    private int mWidth, mHeight;

    public CanvasProView(Context context) {
        this(context, null);
    }

    public CanvasProView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CanvasProView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mWidth = getResources().getDisplayMetrics().widthPixels;
        mHeight = getResources().getDisplayMetrics().heightPixels;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paintCircle = new Paint();
        paintCircle.setStyle(Paint.Style.STROKE);
        paintCircle.setStrokeWidth(5);
        paintCircle.setAntiAlias(true);
        canvas.drawCircle(mWidth / 2, mHeight / 2, mWidth / 2, paintCircle);

        Paint paintDegree = new Paint();
        paintDegree.setAntiAlias(true);
        paintDegree.setStyle(Paint.Style.STROKE);
        paintDegree.setStrokeWidth(3);
        for (int i = 0; i < 24; i++) {
            if (i == 0 || i == 6 || i == 12 || i == 18) {
                paintDegree.setStrokeWidth(5);
                paintDegree.setTextSize(25);
                canvas.drawLine(mWidth / 2, mHeight / 2 - mWidth / 2
                        , mWidth / 2, mHeight / 2 - mWidth / 2 + 60, paintDegree);
                String degree = String.valueOf(i);
                canvas.drawText(degree, mWidth / 2 - paintDegree.measureText(degree) / 2
                        , mHeight / 2 - mWidth / 2 + 90, paintDegree);
            } else {
                paintDegree.setStrokeWidth(3);
                paintDegree.setTextSize(15);
                canvas.drawLine(mWidth / 2, mHeight / 2 - mWidth / 2
                        , mWidth / 2, mHeight / 2 - mWidth / 2 + 30, paintDegree);
                String degree = String.valueOf(i);
                canvas.drawText(degree, mWidth / 2 - paintDegree.measureText(degree) / 2
                        , mHeight / 2 - mWidth / 2 + 60, paintDegree);
            }
            canvas.rotate(15, mWidth / 2, mHeight / 2);
        }
        canvas.save();
        Paint paintMinute = new Paint();
        paintMinute.setAntiAlias(true);
        paintMinute.setStrokeWidth(10);

        Paint paintHour = new Paint();
        paintHour.setAntiAlias(true);
        paintHour.setStrokeWidth(20);
        canvas.translate(mWidth / 2, mHeight / 2);
        canvas.drawLine(0, 0, 100, 100, paintHour);
        canvas.drawLine(0, 0, 100, 200, paintMinute);
        canvas.restore();
    }
}
