package io.github.leibnik.about_draw.util;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;

/**
 * Created by Droidroid on 2016/1/29.
 */
public class ImageUtil {
    public final static int ANTIQUE_MODE = 0;
    public final static int CAMEO_MODE = 1;
    public final static int NEGATIVE_MODE = 2;
    public final static int BLUR_MODE = 3;

    public static Bitmap handleEffect(Bitmap bm, float hue, float saturation, float lum) {
        Bitmap bmp = Bitmap.createBitmap(bm.getWidth(), bm.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmp);
        Paint paint = new Paint();

        ColorMatrix hueMatrix = new ColorMatrix();
        hueMatrix.setRotate(0, hue);
        hueMatrix.setRotate(1, hue);
        hueMatrix.setRotate(2, hue);

        ColorMatrix saturationMatrix = new ColorMatrix();
        saturationMatrix.setSaturation(saturation);

        ColorMatrix lumMatrix = new ColorMatrix();
        lumMatrix.setScale(lum, lum, lum, 1);

        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.postConcat(hueMatrix);
        colorMatrix.postConcat(saturationMatrix);
        colorMatrix.postConcat(lumMatrix);

        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        canvas.drawBitmap(bm, 0, 0, paint);
        return bmp;
    }

    public static Bitmap handleEffect(Bitmap bm, int mode) {
        Bitmap bmp = Bitmap.createBitmap(bm.getWidth(), bm.getHeight(), Bitmap.Config.ARGB_8888);
        int bmWidth = bm.getWidth();
        int bmHeight = bm.getHeight();
        int[] oldPixels = new int[bmWidth * bmHeight];
        int[] newPixels = new int[oldPixels.length];
        bm.getPixels(oldPixels, 0, bmWidth, 0, 0, bmWidth, bmHeight);
        int r, g, b, a;
        switch (mode) {
            case CAMEO_MODE:
                for (int i = 0; i < bmWidth * bmHeight - 1; i++) {
                    r = Color.red(oldPixels[i]);
                    g = Color.green(oldPixels[i]);
                    b = Color.blue(oldPixels[i]);
                    a = Color.alpha(oldPixels[i]);

                    int r1 = Color.red(oldPixels[i + 1]);
                    int g1 = Color.green(oldPixels[i + 1]);
                    int b1 = Color.blue(oldPixels[i + 1]);

                    r = r1 - r + 127;
                    g = g1 - g + 127;
                    b = b1 - b + 127;

                    if (r > 255) {
                        r = 255;
                    } else if (r < 0) {
                        r = 0;
                    }
                    if (g > 255) {
                        g = 255;
                    } else if (g < 0) {
                        g = 0;
                    }
                    if (b > 255) {
                        b = 255;
                    } else if (b < 0) {
                        b = 0;
                    }
                    newPixels[i] = Color.argb(a, r, g, b);
                }
                newPixels[bmWidth * bmHeight - 1] = oldPixels[bmWidth * bmHeight - 1];
                bmp.setPixels(newPixels, 0, bmWidth, 0, 0, bmWidth, bmHeight);
                break;
            case ANTIQUE_MODE:
                for (int i = 0; i < bmWidth * bmHeight; i++) {
                    r = Color.red(oldPixels[i]);
                    g = Color.green(oldPixels[i]);
                    b = Color.blue(oldPixels[i]);
                    a = Color.alpha(oldPixels[i]);

                    r = (int) (0.393 * r + 0.769 * g + 0.189 * b);
                    g = (int) (0.349 * r + 0.686 * g + 0.168 * b);
                    b = (int) (0.272 * r + 0.534 * g + 0.131 * b);

                    if (r > 255) {
                        r = 255;
                    } else if (r < 0) {
                        r = 0;
                    }
                    if (g > 255) {
                        g = 255;
                    } else if (g < 0) {
                        g = 0;
                    }
                    if (b > 255) {
                        b = 255;
                    } else if (b < 0) {
                        b = 0;
                    }
                    newPixels[i] = Color.argb(a, r, g, b);
                }
                bmp.setPixels(newPixels, 0, bmWidth, 0, 0, bmWidth, bmHeight);
                break;
            case NEGATIVE_MODE:
                for (int i = 0; i < bmWidth * bmHeight; i++) {
                    r = Color.red(oldPixels[i]);
                    g = Color.green(oldPixels[i]);
                    b = Color.blue(oldPixels[i]);
                    a = Color.alpha(oldPixels[i]);

                    r = 255 - r;
                    g = 255 - g;
                    b = 255 - b;
                    if (r > 255) {
                        r = 255;
                    } else if (r < 0) {
                        r = 0;
                    }
                    if (g > 255) {
                        g = 255;
                    } else if (g < 0) {
                        g = 0;
                    }
                    if (b > 255) {
                        b = 255;
                    } else if (b < 0) {
                        b = 0;
                    }
                    newPixels[i] = Color.argb(a, r, g, b);
                }
                bmp.setPixels(newPixels, 0, bmWidth, 0, 0, bmWidth, bmHeight);
                break;
            case BLUR_MODE:
                int iterations = 3;
                for (int i = 0; i < iterations; i++) {
                    blur(oldPixels, newPixels, bmWidth, bmHeight, 30);
                    blur(newPixels, oldPixels, bmHeight, bmWidth, 30);
                }
                blurFractional(oldPixels, newPixels, bmWidth, bmHeight, 30);
                blurFractional(newPixels, oldPixels, bmHeight, bmWidth, 30);
                bmp.setPixels(oldPixels, 0, bmWidth, 0, 0, bmWidth, bmHeight);
                break;
        }

        return bmp;
    }

    public static void blur(int[] in, int[] out, int width, int height, float radius) {
        int widthMinus1 = width - 1;
        int r = (int) radius;
        int tableSize = 2 * r + 1;
        int divide[] = new int[256 * tableSize];

        for (int i = 0; i < 256 * tableSize; i++)
            divide[i] = i / tableSize;

        int inIndex = 0;

        for (int y = 0; y < height; y++) {
            int outIndex = y;
            int ta = 0, tr = 0, tg = 0, tb = 0;

            for (int i = -r; i <= r; i++) {
                int rgb = in[inIndex + clamp(i, 0, width - 1)];
                ta += (rgb >> 24) & 0xff;
                tr += (rgb >> 16) & 0xff;
                tg += (rgb >> 8) & 0xff;
                tb += rgb & 0xff;
            }

            for (int x = 0; x < width; x++) {
                out[outIndex] = (divide[ta] << 24) | (divide[tr] << 16) | (divide[tg] << 8) | divide[tb];

                int i1 = x + r + 1;
                if (i1 > widthMinus1)
                    i1 = widthMinus1;
                int i2 = x - r;
                if (i2 < 0)
                    i2 = 0;
                int rgb1 = in[inIndex + i1];
                int rgb2 = in[inIndex + i2];

                ta += ((rgb1 >> 24) & 0xff) - ((rgb2 >> 24) & 0xff);
                tr += ((rgb1 & 0xff0000) - (rgb2 & 0xff0000)) >> 16;
                tg += ((rgb1 & 0xff00) - (rgb2 & 0xff00)) >> 8;
                tb += (rgb1 & 0xff) - (rgb2 & 0xff);
                outIndex += height;
            }
            inIndex += width;
        }
    }

    public static void blurFractional(int[] in, int[] out, int width, int height, float radius) {
        float f = 1.0f / (1 + 2 * radius);
        int inIndex = 0;

        for (int y = 0; y < height; y++) {
            int outIndex = y;
            for (int x = 0; x < width; x++) {
                int index2 = inIndex + x;
                int index1 = index2 - 1;
                int index3 = index2 + 1;

                if (index1 < inIndex) {
                    index1 = inIndex;
                }
                if (index3 > inIndex + width - 1) {
                    index3 = inIndex + width - 1;
                }

                int rgb1 = in[index1];
                int rgb2 = in[index2];
                int rgb3 = in[index3];

                int a1 = (rgb1 >> 24) & 0xff;
                int r1 = (rgb1 >> 16) & 0xff;
                int g1 = (rgb1 >> 8) & 0xff;
                int b1 = rgb1 & 0xff;
                int a2 = (rgb2 >> 24) & 0xff;
                int r2 = (rgb2 >> 16) & 0xff;
                int g2 = (rgb2 >> 8) & 0xff;
                int b2 = rgb2 & 0xff;
                int a3 = (rgb3 >> 24) & 0xff;
                int r3 = (rgb3 >> 16) & 0xff;
                int g3 = (rgb3 >> 8) & 0xff;
                int b3 = rgb3 & 0xff;
                a1 = a2 + (int) ((a1 + a3) * radius);
                r1 = r2 + (int) ((r1 + r3) * radius);
                g1 = g2 + (int) ((g1 + g3) * radius);
                b1 = b2 + (int) ((b1 + b3) * radius);
                a1 *= f;
                r1 *= f;
                g1 *= f;
                b1 *= f;
                out[outIndex] = (a1 << 24) | (r1 << 16) | (g1 << 8) | b1;
                outIndex += height;
            }
            inIndex += width;
        }
    }

    /*
     * 网络上的代码，稍有缺陷：即生成后的图片的左边缘上边缘下边缘的像素的agrb值均与坐标(0,0)的像素的相等，
     * 右边缘的像素的argb值与坐标(width - 1, 0)的像素的相等
     */
//    private static void blurFractional(int[] in, int[] out, int width, int height, float radius) {
//        radius -= (int) radius;
//        float f = 1.0f / (1 + 2 * radius);
//        int inIndex = 0;
//        for (int y = 0; y < height; y++) {
//            int outIndex = y;
//            out[outIndex] = in[0];
//            outIndex += height;
//            for (int x = 1; x < width - 1; x++) {
//                int i = inIndex + x;
//                int rgb1 = in[i - 1];
//                int rgb2 = in[i];
//                int rgb3 = in[i + 1];
//
//                int a1 = (rgb1 >> 24) & 0xff;
//                int r1 = (rgb1 >> 16) & 0xff;
//                int g1 = (rgb1 >> 8) & 0xff;
//                int b1 = rgb1 & 0xff;
//                int a2 = (rgb2 >> 24) & 0xff;
//                int r2 = (rgb2 >> 16) & 0xff;
//                int g2 = (rgb2 >> 8) & 0xff;
//                int b2 = rgb2 & 0xff;
//                int a3 = (rgb3 >> 24) & 0xff;
//                int r3 = (rgb3 >> 16) & 0xff;
//                int g3 = (rgb3 >> 8) & 0xff;
//                int b3 = rgb3 & 0xff;
//                a1 = a2 + (int) ((a1 + a3) * radius);
//                r1 = r2 + (int) ((r1 + r3) * radius);
//                g1 = g2 + (int) ((g1 + g3) * radius);
//                b1 = b2 + (int) ((b1 + b3) * radius);
//                a1 *= f;
//                r1 *= f;
//                g1 *= f;
//                b1 *= f;
//                out[outIndex] = (a1 << 24) | (r1 << 16) | (g1 << 8) | b1;
//                outIndex += height;
//            }
//            out[outIndex] = in[width - 1];
//            inIndex += width;
//        }
//    }

    public static int clamp(int x, int a, int b) {
        return (x < a) ? a : (x > b) ? b : x;
    }

    // 网上流传较广的模糊算法，效率高
    public static Bitmap fastBlur(Bitmap sentBitmap, int radius, boolean canReuseInBitmap) {

        // Stack Blur v1.0 from
        // http://www.quasimondo.com/StackBlurForCanvas/StackBlurDemo.html
        //
        // Java Author: Mario Klingemann <mario at quasimondo.com>
        // http://incubator.quasimondo.com
        // created Feburary 29, 2004
        // Android port : Yahel Bouaziz <yahel at kayenko.com>
        // http://www.kayenko.com
        // ported april 5th, 2012

        // This is a compromise between Gaussian Blur and Box blur
        // It creates much better looking blurs than Box Blur, but is
        // 7x faster than my Gaussian Blur implementation.
        //
        // I called it Stack Blur because this describes best how this
        // filter works internally: it creates a kind of moving stack
        // of colors whilst scanning through the image. Thereby it
        // just has to add one new block of color to the right side
        // of the stack and remove the leftmost color. The remaining
        // colors on the topmost layer of the stack are either added on
        // or reduced by one, depending on if they are on the right or
        // on the left side of the stack.
        //
        // If you are using this algorithm in your code please add
        // the following line:
        //
        // Stack Blur Algorithm by Mario Klingemann <mario@quasimondo.com>

        Bitmap bitmap;
        if (canReuseInBitmap) {
            bitmap = sentBitmap;
        } else {
            bitmap = sentBitmap.copy(sentBitmap.getConfig(), true);
        }

        if (radius < 1) {
            return (null);
        }

        int w = bitmap.getWidth();
        int h = bitmap.getHeight();

        int[] pix = new int[w * h];
        bitmap.getPixels(pix, 0, w, 0, 0, w, h);

        int wm = w - 1;
        int hm = h - 1;
        int wh = w * h;
        int div = radius + radius + 1;

        int r[] = new int[wh];
        int g[] = new int[wh];
        int b[] = new int[wh];
        int rsum, gsum, bsum, x, y, i, p, yp, yi, yw;
        int vmin[] = new int[Math.max(w, h)];

        int divsum = (div + 1) >> 1;
        divsum *= divsum;
        int dv[] = new int[256 * divsum];
        for (i = 0; i < 256 * divsum; i++) {
            dv[i] = (i / divsum);
        }

        yw = yi = 0;

        int[][] stack = new int[div][3];
        int stackpointer;
        int stackstart;
        int[] sir;
        int rbs;
        int r1 = radius + 1;
        int routsum, goutsum, boutsum;
        int rinsum, ginsum, binsum;

        for (y = 0; y < h; y++) {
            rinsum = ginsum = binsum = routsum = goutsum = boutsum = rsum = gsum = bsum = 0;
            for (i = -radius; i <= radius; i++) {
                p = pix[yi + Math.min(wm, Math.max(i, 0))];
                sir = stack[i + radius];
                sir[0] = (p & 0xff0000) >> 16;
                sir[1] = (p & 0x00ff00) >> 8;
                sir[2] = (p & 0x0000ff);
                rbs = r1 - Math.abs(i);
                rsum += sir[0] * rbs;
                gsum += sir[1] * rbs;
                bsum += sir[2] * rbs;
                if (i > 0) {
                    rinsum += sir[0];
                    ginsum += sir[1];
                    binsum += sir[2];
                } else {
                    routsum += sir[0];
                    goutsum += sir[1];
                    boutsum += sir[2];
                }
            }
            stackpointer = radius;

            for (x = 0; x < w; x++) {

                r[yi] = dv[rsum];
                g[yi] = dv[gsum];
                b[yi] = dv[bsum];

                rsum -= routsum;
                gsum -= goutsum;
                bsum -= boutsum;

                stackstart = stackpointer - radius + div;
                sir = stack[stackstart % div];

                routsum -= sir[0];
                goutsum -= sir[1];
                boutsum -= sir[2];

                if (y == 0) {
                    vmin[x] = Math.min(x + radius + 1, wm);
                }
                p = pix[yw + vmin[x]];

                sir[0] = (p & 0xff0000) >> 16;
                sir[1] = (p & 0x00ff00) >> 8;
                sir[2] = (p & 0x0000ff);

                rinsum += sir[0];
                ginsum += sir[1];
                binsum += sir[2];

                rsum += rinsum;
                gsum += ginsum;
                bsum += binsum;

                stackpointer = (stackpointer + 1) % div;
                sir = stack[(stackpointer) % div];

                routsum += sir[0];
                goutsum += sir[1];
                boutsum += sir[2];

                rinsum -= sir[0];
                ginsum -= sir[1];
                binsum -= sir[2];

                yi++;
            }
            yw += w;
        }
        for (x = 0; x < w; x++) {
            rinsum = ginsum = binsum = routsum = goutsum = boutsum = rsum = gsum = bsum = 0;
            yp = -radius * w;
            for (i = -radius; i <= radius; i++) {
                yi = Math.max(0, yp) + x;

                sir = stack[i + radius];

                sir[0] = r[yi];
                sir[1] = g[yi];
                sir[2] = b[yi];

                rbs = r1 - Math.abs(i);

                rsum += r[yi] * rbs;
                gsum += g[yi] * rbs;
                bsum += b[yi] * rbs;

                if (i > 0) {
                    rinsum += sir[0];
                    ginsum += sir[1];
                    binsum += sir[2];
                } else {
                    routsum += sir[0];
                    goutsum += sir[1];
                    boutsum += sir[2];
                }

                if (i < hm) {
                    yp += w;
                }
            }
            yi = x;
            stackpointer = radius;
            for (y = 0; y < h; y++) {
                // Preserve alpha channel: ( 0xff000000 & pix[yi] )
                pix[yi] = (0xff000000 & pix[yi]) | (dv[rsum] << 16) | (dv[gsum] << 8) | dv[bsum];

                rsum -= routsum;
                gsum -= goutsum;
                bsum -= boutsum;

                stackstart = stackpointer - radius + div;
                sir = stack[stackstart % div];

                routsum -= sir[0];
                goutsum -= sir[1];
                boutsum -= sir[2];

                if (x == 0) {
                    vmin[y] = Math.min(y + r1, hm) * w;
                }
                p = x + vmin[y];

                sir[0] = r[p];
                sir[1] = g[p];
                sir[2] = b[p];

                rinsum += sir[0];
                ginsum += sir[1];
                binsum += sir[2];

                rsum += rinsum;
                gsum += ginsum;
                bsum += binsum;

                stackpointer = (stackpointer + 1) % div;
                sir = stack[stackpointer];

                routsum += sir[0];
                goutsum += sir[1];
                boutsum += sir[2];

                rinsum -= sir[0];
                ginsum -= sir[1];
                binsum -= sir[2];

                yi += w;
            }
        }

        bitmap.setPixels(pix, 0, w, 0, 0, w, h);

        return (bitmap);
    }

    //均值模糊
    public static Bitmap boxBlur(Bitmap bm, int radius, int mode) {
        Bitmap bmp = Bitmap.createBitmap(bm.getWidth(), bm.getHeight(), Bitmap.Config.ARGB_8888);
        int width = bm.getWidth();
        int height = bm.getHeight();
        int[] in = new int[width * height];
        bm.getPixels(in, 0, width, 0, 0, width, height);
        int[] out = new int[width * height];
        int widthMinus1 = width - 1;
        int tableSize = 2 * radius + 1;
        int divide[] = new int[256 * tableSize];

        // the value scope will be 0 to 255, and number of 0 is table size
        // will get means from index not calculate result again since
        // color value must be  between 0 and 255.
        for (int i = 0; i < 256 * tableSize; i++)
            divide[i] = i / tableSize;

        switch (mode) {
            case 0:
                // x轴方向模糊
                int inIndex = 0;
                for (int y = 0; y < height; y++) {
                    int outIndex = y * width;
                    int ta = 0, tr = 0, tg = 0, tb = 0; // ARGB -> prepare for the alpha, red, green, blue color value.

                    for (int i = -radius; i <= radius; i++) {
                        int rgb = in[inIndex + Math.min(width, Math.max(i, 0))]; // read input pixel data here. table size data.
                        ta += (rgb >> 24) & 0xff;
                        tr += (rgb >> 16) & 0xff;
                        tg += (rgb >> 8) & 0xff;
                        tb += rgb & 0xff;
                    }

                    for (int x = 0; x < width; x++) { // get output pixel data.
                        out[outIndex] = (divide[ta] << 24) | (divide[tr] << 16) | (divide[tg] << 8) | divide[tb]; // calculate the output data.

                        int i1 = x + radius + 1;
                        if (i1 > widthMinus1)
                            i1 = widthMinus1;
                        int i2 = x - radius;
                        if (i2 < 0)
                            i2 = 0;
                        int rgb1 = in[inIndex + i1];
                        int rgb2 = in[inIndex + i2];

                        ta += ((rgb1 >> 24) & 0xff) - ((rgb2 >> 24) & 0xff);
                        tr += ((rgb1 & 0xff0000) - (rgb2 & 0xff0000)) >> 16;
                        tg += ((rgb1 & 0xff00) - (rgb2 & 0xff00)) >> 8;
                        tb += (rgb1 & 0xff) - (rgb2 & 0xff);
                        outIndex++; // per column or per row as cycle...
                    }
                    inIndex += width; // next (i+ column number * n, n=1....n-1)
                }
                break;
            case 1:
                // y轴方向模糊
                inIndex = 0;
                for (int x = 0; x < width; x++) {
                    int outIndex = x;
                    int ta = 0, tr = 0, tg = 0, tb = 0; // ARGB -> prepare for the alpha, red, green, blue color value.

                    for (int i = -radius; i <= radius; i++) {
                        int rgb = in[inIndex + Math.min(height, Math.max(i, 0)) * width]; // read input pixel data here. table size data.
                        ta += (rgb >> 24) & 0xff;
                        tr += (rgb >> 16) & 0xff;
                        tg += (rgb >> 8) & 0xff;
                        tb += rgb & 0xff;
                    }

                    for (int y = 0; y < height; y++) { // get output pixel data.
                        out[outIndex] = (divide[ta] << 24) | (divide[tr] << 16) | (divide[tg] << 8) | divide[tb]; // calculate the output data.

                        int i1 = y + radius + 1;
                        if (i1 > height - 1)
                            i1 = height - 1;
                        int i2 = y - radius;
                        if (i2 < 0)
                            i2 = 0;
                        int rgb1 = in[inIndex + i1 * width];
                        int rgb2 = in[inIndex + i2 * width];

                        ta += ((rgb1 >> 24) & 0xff) - ((rgb2 >> 24) & 0xff);
                        tr += ((rgb1 & 0xff0000) - (rgb2 & 0xff0000)) >> 16;
                        tg += ((rgb1 & 0xff00) - (rgb2 & 0xff00)) >> 8;
                        tb += (rgb1 & 0xff) - (rgb2 & 0xff);
                        outIndex += width; // per column or per row as cycle...
                    }
                    inIndex++; // next (i+ column number * n, n=1....n-1)
                }

        }
        bmp.setPixels(out, 0, width, 0, 0, width, height);
        return bmp;
    }
}
