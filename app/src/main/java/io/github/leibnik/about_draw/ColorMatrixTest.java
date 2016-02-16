package io.github.leibnik.about_draw;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import io.github.leibnik.about_draw.adapter.FragmentsAdapter;
import io.github.leibnik.about_draw.fragments.FmRGBAMatrix;
import io.github.leibnik.about_draw.fragments.FmRGBAPixel;
import io.github.leibnik.about_draw.fragments.FmRGBASeekbar;
import io.github.leibnik.about_draw.view.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Droidroid on 2016/1/21.
 */
public class ColorMatrixTest extends AppCompatActivity implements View.OnClickListener{
    private Button btnSeekbar, btnMatrix, btnPixel;
    private NoScrollViewPager viewPager;
    private FragmentsAdapter adapter;
    private List<Fragment> fragments;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.aty_rgba);
        fragments = new ArrayList<>();
        fragments.add(new FmRGBASeekbar());
        fragments.add(new FmRGBAMatrix());
        fragments.add(new FmRGBAPixel());
        btnSeekbar = (Button) findViewById(R.id.btn1);
        btnMatrix = (Button) findViewById(R.id.btn2);
        btnPixel = (Button) findViewById(R.id.btn3);
        viewPager = (NoScrollViewPager) findViewById(R.id.pager);
        adapter = new FragmentsAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(adapter);
        btnSeekbar.setOnClickListener(this);
        btnMatrix.setOnClickListener(this);
        btnPixel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn1:
                viewPager.setCurrentItem(0);
                break;
            case R.id.btn2:
                viewPager.setCurrentItem(1);
                break;
            case R.id.btn3:
                viewPager.setCurrentItem(2);
                break;
        }
    }
}
