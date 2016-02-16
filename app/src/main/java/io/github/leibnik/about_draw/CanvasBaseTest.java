package io.github.leibnik.about_draw;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;

/**
 * Created by Droidroid on 2016/1/21.
 */
public class CanvasBaseTest extends Activity {

    private Canvas mCanvas;
    private Paint mPaint;
    private static int dx = 100;
    private static int dy = 60;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_canvas);
    }
}
