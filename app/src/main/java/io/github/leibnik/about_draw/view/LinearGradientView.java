package io.github.leibnik.about_draw.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Droidroid on 2016/2/15.
 */
public class LinearGradientView extends View {
    public LinearGradientView(Context context) {
        this(context, null);
    }

    public LinearGradientView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LinearGradientView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint mPaint = new Paint();
        mPaint.setShader(new LinearGradient(0, 0, 300, 300, Color.BLUE, Color.YELLOW,
                Shader.TileMode.CLAMP));
        canvas.drawRect(0, 0, 300, 300, mPaint);
    }
}
