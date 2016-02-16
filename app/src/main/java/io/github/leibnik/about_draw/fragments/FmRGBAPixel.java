package io.github.leibnik.about_draw.fragments;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import io.github.leibnik.about_draw.R;
import io.github.leibnik.about_draw.util.ImageUtil;

/**
 * Created by Droidroid on 2016/1/29.
 */
public class FmRGBAPixel extends Fragment implements View.OnClickListener{

    private Button cameoEffect, antiqueEffect, nagativeEffect,blurEffect;
    private ImageView imageView;
    private Bitmap picture;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fm_rgba_pixel,container,false);
        picture = BitmapFactory.decodeResource(getResources(),R.drawable.cat);

        imageView = (ImageView) view.findViewById(R.id.imageview);
        antiqueEffect = (Button) view.findViewById(R.id.antique);
        cameoEffect = (Button) view.findViewById(R.id.cameo);
        nagativeEffect = (Button) view.findViewById(R.id.negative);
        blurEffect = (Button) view.findViewById(R.id.blur);

        imageView.setImageBitmap(picture);
        antiqueEffect.setOnClickListener(this);
        cameoEffect.setOnClickListener(this);
        nagativeEffect.setOnClickListener(this);
        blurEffect.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cameo:
                imageView.setImageBitmap(ImageUtil.handleEffect(picture, ImageUtil.CAMEO_MODE));
                break;
            case R.id.antique:
                imageView.setImageBitmap(ImageUtil.handleEffect(picture, ImageUtil.ANTIQUE_MODE));
                break;
            case R.id.negative:
                imageView.setImageBitmap(ImageUtil.handleEffect(picture, ImageUtil.NEGATIVE_MODE));
                break;
            case R.id.blur:
                long start = System.currentTimeMillis();

                // box blur：x轴或y轴单方向的均值模糊,第三个参数0：x轴方向模糊 1：y轴方向模糊
//                imageView.setImageBitmap(ImageUtil.boxBlur(picture, 20, 0));

                // fast blur 网上流传较广的算法，均值模糊与高斯模糊的结合
//                imageView.setImageBitmap(ImageUtil.fastBlur(picture, 20,false));

                // box blur：均值模糊
                imageView.setImageBitmap(ImageUtil.handleEffect(picture,ImageUtil.BLUR_MODE));
                long end = System.currentTimeMillis();
                Log.e("milliseconds:", String.valueOf(end - start));
                break;
        }
    }
}
