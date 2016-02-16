package io.github.leibnik.about_draw;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.TextView;

import io.github.leibnik.about_draw.util.DisplayUtil;

/**
 * Created by Droidroid on 2016/1/21.
 */
public class ScreenTest extends Activity {

    private TextView tv1, tv2;
    private int widthPixels, heightPixels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_screen_msg);
        tv1 = (TextView) findViewById(R.id.width);
        tv2 = (TextView) findViewById(R.id.height);
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        widthPixels = metrics.widthPixels;
        heightPixels = metrics.heightPixels;
        tv1.setText("Hi!你的屏幕宽为" + widthPixels + "px(" + DisplayUtil.px2dp(this, widthPixels) + "dp),"
                + "屏幕高为" + heightPixels + "px(" + DisplayUtil.px2dp(this, heightPixels) + "dp)");
        tv2.setText("本字体大小为" + tv2.getTextSize()
                + "px(" + DisplayUtil.px2sp(this, tv2.getTextSize()) + "sp,"
                + DisplayUtil.px2dp(this, tv2.getTextSize()) + "dp)");
    }
}
