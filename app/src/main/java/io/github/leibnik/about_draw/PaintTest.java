package io.github.leibnik.about_draw;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import io.github.leibnik.about_draw.adapter.FragmentsAdapter;
import io.github.leibnik.about_draw.fragments.FmBitmapShader;
import io.github.leibnik.about_draw.fragments.FmLinearGradient;
import io.github.leibnik.about_draw.fragments.FmPathEffect;
import io.github.leibnik.about_draw.fragments.FmReflection;
import io.github.leibnik.about_draw.fragments.FmRoundPicture;
import io.github.leibnik.about_draw.fragments.FmScratchCard;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Droidroid on 2016/1/21.
 */
public class PaintTest extends AppCompatActivity implements View.OnClickListener{

    private Button circlePic, scratchCard, shader, gradient, reflect, patheffect;
    private ViewPager pager;
    private List<Fragment> frags;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(io.github.leibnik.about_draw.R.layout.aty_pen);

        circlePic = (Button) findViewById(io.github.leibnik.about_draw.R.id.circle_pic);
        scratchCard = (Button) findViewById(io.github.leibnik.about_draw.R.id.scratch_card);
        shader = (Button) findViewById(io.github.leibnik.about_draw.R.id.bitmapshader);
        gradient = (Button) findViewById(io.github.leibnik.about_draw.R.id.gradient);
        reflect = (Button) findViewById(io.github.leibnik.about_draw.R.id.reflection);
        patheffect = (Button) findViewById(io.github.leibnik.about_draw.R.id.patheffect);

        pager = (ViewPager) findViewById(io.github.leibnik.about_draw.R.id.pager);

        frags = new ArrayList<>();
        frags.add(new FmRoundPicture());
        frags.add(new FmScratchCard());
        frags.add(new FmBitmapShader());
        frags.add(new FmLinearGradient());
        frags.add(new FmReflection());
        frags.add(new FmPathEffect());

        FragmentsAdapter adapter = new FragmentsAdapter(getSupportFragmentManager(),frags);
        pager.setAdapter(adapter);

        circlePic.setOnClickListener(this);
        scratchCard.setOnClickListener(this);
        shader.setOnClickListener(this);
        gradient.setOnClickListener(this);
        reflect.setOnClickListener(this);
        patheffect.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.circle_pic:
                pager.setCurrentItem(0);
                break;
            case R.id.scratch_card:
                pager.setCurrentItem(1);
                break;
            case R.id.bitmapshader:
                pager.setCurrentItem(2);
                break;
            case R.id.gradient:
                pager.setCurrentItem(3);
                break;
            case R.id.reflection:
                pager.setCurrentItem(4);
                break;
            case R.id.patheffect:
                pager.setCurrentItem(5);
                break;
        }
    }
}
