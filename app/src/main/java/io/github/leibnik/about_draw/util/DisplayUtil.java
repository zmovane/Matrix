package io.github.leibnik.about_draw.util;

import android.content.Context;

/**
 * Created by Droidroid on 2016/2/15.
 * dp、sp转换px的工具类
 */
public class DisplayUtil {
    /**
     * dp转px
     * @param context
     * @param dpValue
     * @return
     */
    public static int dp2px(Context context,float dpValue){
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5);
    }

    /**
     * sp转px
     * @param context
     * @param spValue
     * @return
     */
    public static int sp2px(Context context, float spValue){
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5);
    }

    /**
     * px转dp
     * @param context
     * @param pxValue
     * @return
     */
    public static int px2dp(Context context, float pxValue){
        final float scale= context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5);
    }

    /**
     * px转sp
     * @param context
     * @param pxValue
     * @return
     */
    public static int px2sp(Context context, float pxValue){
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5);
    }
}
