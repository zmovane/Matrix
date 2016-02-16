package io.github.leibnik.about_draw.fragments;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;

import io.github.leibnik.about_draw.R;
import io.github.leibnik.about_draw.util.ImageUtil;

/**
 * Created by Droidroid on 2016/1/29.
 */
public class FmRGBASeekbar extends Fragment {

    private SeekBar seekbarHue, seekbarSaturation, seekbarLum;
    private float mHue, mSaturation = 1.0f, mLum = 1.0f;
    private Bitmap picture;
    private ImageView imageView;
    private int MID_VALUE = 50;
    private SeekBar.OnSeekBarChangeListener listener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            switch (seekBar.getId()){
                case R.id.seekbarHue:
                    mHue = (progress - MID_VALUE) * 1.0F / MID_VALUE * 180;
                    break;
                case R.id.seekbarSaturation:
                    mSaturation = progress * 1.0F / MID_VALUE;
                    break;
                case R.id.seekbarLum:
                    mLum = progress * 1.0F / MID_VALUE;
                    break;
            }
            imageView.setImageBitmap(ImageUtil.handleEffect(picture, mHue, mSaturation, mLum));
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fm_rgba_seekbar, container, false);
        imageView = (ImageView) view.findViewById(R.id.imageview);
        seekbarHue = (SeekBar) view.findViewById(R.id.seekbarHue);
        seekbarSaturation = (SeekBar) view.findViewById(R.id.seekbarSaturation);
        seekbarLum = (SeekBar) view.findViewById(R.id.seekbarLum);

        seekbarHue.setMax(2 * MID_VALUE);
        seekbarSaturation.setMax(2 * MID_VALUE);
        seekbarLum.setMax(2 * MID_VALUE);
        seekbarHue.setProgress(MID_VALUE);
        seekbarSaturation.setProgress(MID_VALUE);
        seekbarLum.setProgress(MID_VALUE);

        seekbarHue.setOnSeekBarChangeListener(listener);
        seekbarSaturation.setOnSeekBarChangeListener(listener);
        seekbarLum.setOnSeekBarChangeListener(listener);

        picture = BitmapFactory.decodeResource(getResources(),R.drawable.cat);
        imageView.setImageBitmap(picture);

        return view;
    }


}
