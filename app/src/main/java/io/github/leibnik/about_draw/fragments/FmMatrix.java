package io.github.leibnik.about_draw.fragments;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
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

/**
 * Created by Droidroid on 2016/2/7.
 */
public class FmMatrix extends Fragment implements View.OnClickListener {

    private Button translateBtn, skewBtn, rotateBtn, scaleBtn;
    private ImageView imageView;
    private Bitmap picture;
    private Bitmap product;
    private Canvas mCanvas;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fm_matrix, container, false);
        translateBtn = (Button) view.findViewById(R.id.translate);
        skewBtn = (Button) view.findViewById(R.id.skew);
        rotateBtn = (Button) view.findViewById(R.id.rotate);
        scaleBtn = (Button) view.findViewById(R.id.scale);
        imageView = (ImageView) view.findViewById(R.id.imageview);

        picture = BitmapFactory.decodeResource(getResources(), R.drawable.cat);
        product = Bitmap.createBitmap(picture.getWidth() * 2, picture.getHeight() * 2,
                Bitmap.Config.ARGB_8888);
        imageView.setImageBitmap(picture);
        mCanvas = new Canvas(product);

        translateBtn.setOnClickListener(this);
        skewBtn.setOnClickListener(this);
        rotateBtn.setOnClickListener(this);
        scaleBtn.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        mCanvas.drawColor(Color.WHITE);
        switch (v.getId()) {
            case R.id.translate:
                Matrix translateMatrix = new Matrix();
                translateMatrix.setTranslate(100.0f, 100.0f);
                mCanvas.drawBitmap(picture, translateMatrix, null);
                break;
            case R.id.skew:
                Matrix skewMatrix = new Matrix();
                skewMatrix.setSkew(0.0f, 0.8f);
                mCanvas.drawBitmap(picture, skewMatrix, null);
                break;
            case R.id.rotate:
                Matrix rotateMatrix = new Matrix();
                rotateMatrix.setRotate(60.0f, picture.getWidth() / 2 + imageView.getPaddingLeft(),
                        picture.getHeight() / 2 + imageView.getPaddingTop());
                Log.e("imageview padding left", imageView.getPaddingLeft() + "");
                Log.e("imageview padding top", imageView.getPaddingTop() + "");
                Log.e("imageview left", imageView.getLeft() + "");
                Log.e("imageview top", imageView.getTop() + "");
                mCanvas.drawBitmap(picture, rotateMatrix, null);
                break;
            case R.id.scale:
                Matrix scaleMatrix = new Matrix();
                scaleMatrix.setScale(0.5f, 0.5f);
                mCanvas.drawBitmap(picture, scaleMatrix, null);
                break;
        }
        imageView.setImageBitmap(product);
    }
}
