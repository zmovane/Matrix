package io.github.leibnik.about_draw.fragments;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;

import io.github.leibnik.about_draw.R;
import io.github.leibnik.about_draw.adapter.GridAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Droidroid on 2016/1/29.
 */
public class FmRGBAMatrix extends Fragment implements View.OnClickListener{

    private Button changeMatrix, resetMatrix;
    private GridView gridView;
    private ImageView imageView;
    private Bitmap picture;
    private float[] mMatrix = new float[20];
    private List<EditText> mEts;
    private GridAdapter gridAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mEts = new ArrayList<>();
        View view = inflater.inflate(R.layout.fm_rgba_matrix, container, false);
        gridView = (GridView) view.findViewById(R.id.group);
        imageView = (ImageView) view.findViewById(R.id.imageview);
        changeMatrix = (Button) view.findViewById(R.id.change);
        resetMatrix = (Button) view.findViewById(R.id.reset);
        picture = BitmapFactory.decodeResource(getResources(), R.drawable.cat);
        imageView.setImageBitmap(picture);
        gridView.setNumColumns(5);
        for (int i = 0; i < 20; i++) {
            EditText ed = new EditText(getContext());
            mEts.add(ed);
        }
        gridAdapter = new GridAdapter(mEts);
        gridView.setAdapter(gridAdapter);
        initMatrix();
        changeMatrix.setOnClickListener(this);
        resetMatrix.setOnClickListener(this);
        return view;
    }

    private void initMatrix() {
        for (int i = 0; i < 20; i++) {
            if (i % 6 == 0) {
                mEts.get(i).setText(String.valueOf(1));
            } else {
                mEts.get(i).setText(String.valueOf(0));
            }
        }
        gridAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.change:
                getMatrix();
                setImageMatrix();
                break;
            case R.id.reset:
                initMatrix();
                getMatrix();
                setImageMatrix();
        }
    }

    private void setImageMatrix() {
        Bitmap bmp = Bitmap.createBitmap(picture.getWidth(),picture.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmp);
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.set(mMatrix);
        Paint paint = new Paint();
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        canvas.drawBitmap(picture,0,0,paint);
        imageView.setImageBitmap(bmp);
    }

    private void getMatrix() {
        for (int i = 0; i < 20; i++){
            mMatrix[i] = Float.valueOf(mEts.get(i).getText().toString());
        }
    }
}
