package io.github.leibnik.about_draw.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import io.github.leibnik.about_draw.util.DisplayUtil;

/**
 * Created by Droidroid on 2016/2/15.
 */
public class CanvasBaseView extends View {

    private Paint mPaint;
    private int dx = 200;
    private int dy = 80;
    private int txtLeft = 10;
    private int txtTop = 50;

    private int mLeft = 60;
    private int mRight = 110;
    private int mTop = 5;
    private int mBottom = 55;
    public CanvasBaseView(Context context) {
        this(context, null);
    }

    public CanvasBaseView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CanvasBaseView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        txtLeft = DisplayUtil.dp2px(context, txtLeft);
        txtTop = DisplayUtil.dp2px(context, txtTop);
        dy = DisplayUtil.dp2px(context, dy);
        dx = DisplayUtil.dp2px(context,dx);
        mBottom = DisplayUtil.dp2px(context,mBottom);
        mTop = DisplayUtil.dp2px(context, mTop);
        mRight = DisplayUtil.dp2px(context, mRight);
        mLeft = DisplayUtil.dp2px(context, mLeft);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawText("空心矩形", txtLeft, txtTop, mPaint);
        canvas.drawRect(mLeft, mTop, mRight, mBottom, mPaint);

        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawText("实心矩形", txtLeft + dx, txtTop, mPaint);
        canvas.drawRect(mLeft + dx, mTop, mRight + dx, mBottom, mPaint);

        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawText("点", txtLeft, txtTop + dy, mPaint);
        canvas.drawPoint(mLeft, mTop + dy, mPaint);


        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawText("直线", txtLeft + dx, txtTop + dy, mPaint);
        canvas.drawLine(mLeft + dx, mTop + dy, mRight + dx, mBottom + dy, mPaint);

        canvas.drawText("圆角矩形", txtLeft, txtTop + 2 * dy, mPaint);
        canvas.drawRoundRect(new RectF(mLeft, mTop + 2 * dy, mRight, mBottom + 2 * dy), 7.0f, 7.0f, mPaint);

        canvas.drawText("空心圆", txtLeft + dx, txtTop + 2 * dy, mPaint);
        canvas.drawCircle((mLeft + dx + mRight + dx) / 2, (mTop + 2 * dy + mBottom + 2 * dy) / 2, 25, mPaint);

        canvas.drawText("扇形", txtLeft, txtTop + 3 * dy, mPaint);
        canvas.drawArc(new RectF(mLeft, mTop + 3 * dy, mRight, mBottom + 3 * dy), 30.0f, 30.0f, true, mPaint);

        canvas.drawText("弧形", txtLeft + dx, txtTop + 3 * dy, mPaint);
        canvas.drawArc(new RectF(mLeft + dx, mTop + 3 * dy, mRight + dx, mBottom + 3 * dy), 0f, mLeft, false, mPaint);

        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawText("实心扇形", txtLeft, txtTop + 4 * dy, mPaint);
        canvas.drawArc(new RectF(mLeft, mTop + 4 * dy, mRight, mBottom + 4 * dy), 30.0f, 30.0f, true, mPaint);

        canvas.drawText("实心弧形", txtLeft + dx, txtTop + 4 * dy, mPaint);
        canvas.drawArc(new RectF(mLeft + dx, mTop + 4 * dy, mRight + dx, mBottom + 4 * dy), 0f, mLeft, false, mPaint);

        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawText("椭圆", txtLeft, txtTop + 5 * dy, mPaint);
        canvas.drawOval(new RectF(mLeft, mTop + 5 * dy, mRight+70, mBottom + 5 * dy), mPaint);

        canvas.drawText("路径",txtLeft + dx, txtTop + 5 * dy,mPaint);
        Path path  = new Path();
        path.moveTo(mLeft + dx, mTop + 5 * dy);
        path.lineTo(mLeft + dx + 20, mTop + 5 * dy + 50);
        path.lineTo(mRight + dx, mBottom + 5 * dy);
        canvas.drawPath(path,mPaint);
    }
}
