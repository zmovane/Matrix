package io.github.leibnik.about_draw;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import io.github.leibnik.about_draw.adapter.ListAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;

    private ListAdapter adapter;
    private List<Button> btns;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getSupportActionBar().hide();
        setContentView(io.github.leibnik.about_draw.R.layout.aty_main);
        listView = (ListView) findViewById(io.github.leibnik.about_draw.R.id.listview);
        btns = new ArrayList<>();
        for (int i = 0; i <= 7; i++){
            Button btn = new Button(this);
            switch (i){
                case 0: btn.setText("屏幕的尺寸信息");
                        btn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(MainActivity.this,ScreenTest.class);
                                startActivity(intent);
                            }
                        });
                    break;
                case 1: btn.setText("Canvas绘图基础");
                        btn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(MainActivity.this, CanvasBaseTest.class);
                                startActivity(intent);
                            }
                        });
                    break;
                case 2: btn.setText("Android XML绘图");
                        btn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(MainActivity.this,XmlTest.class);
                                startActivity(intent);
                            }
                        });
                    break;
                case 3: btn.setText("Canvas绘图示例");
                        btn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(MainActivity.this,CanvasProTest.class);
                                startActivity(intent);
                            }
                        });
                    break;
                case 4: btn.setText("图像处理之色彩特效处理");
                        btn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(MainActivity.this,ColorMatrixTest.class);
                                startActivity(intent);
                            }
                        });
                    break;
                case 5: btn.setText("图像处理之图形特效处理");
                        btn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(MainActivity.this, MatrixTest.class);
                                startActivity(intent);
                            }
                        });
                    break;
                case 6: btn.setText("图像处理之画笔特效处理");
                        btn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(MainActivity.this, PaintTest.class);
                                startActivity(intent);
                            }
                        });
                    break;
                case 7: btn.setText("SurfaceView示例");
                        btn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(MainActivity.this, SurfaceViewTest.class);
                                startActivity(intent);
                            }
                        });
                    break;
            }
            btns.add(btn);
        }
        adapter = new ListAdapter(this,btns);
        listView.setAdapter(adapter);
    }
}
