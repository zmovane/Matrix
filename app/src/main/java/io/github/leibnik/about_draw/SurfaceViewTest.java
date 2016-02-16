package io.github.leibnik.about_draw;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import io.github.leibnik.about_draw.fragments.FmDrawingBoard;
import io.github.leibnik.about_draw.fragments.FmSinFuction;

/**
 * Created by Droidroid on 2016/1/21.
 */
public class SurfaceViewTest extends AppCompatActivity{

    private Button btn_sin;
    private Button btn_board;
    private FmSinFuction sinFucFm;
    private FmDrawingBoard boardFm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(io.github.leibnik.about_draw.R.layout.aty_surfaceview);
        init();
    }

    private void init() {
        sinFucFm = new FmSinFuction();
        boardFm = new FmDrawingBoard();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(io.github.leibnik.about_draw.R.id.content,boardFm);
        transaction.commit();
        btn_sin = (Button) findViewById(io.github.leibnik.about_draw.R.id.btn1);
        btn_board = (Button) findViewById(io.github.leibnik.about_draw.R.id.btn2);
        btn_sin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(io.github.leibnik.about_draw.R.id.content,sinFucFm);
                transaction.commit();
            }
        });
        btn_board.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(io.github.leibnik.about_draw.R.id.content,boardFm);
                transaction.commit();
            }
        });
    }
}
