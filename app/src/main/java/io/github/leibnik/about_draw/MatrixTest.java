package io.github.leibnik.about_draw;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import io.github.leibnik.about_draw.fragments.FmMatrix;

/**
 * Created by Droidroid on 2016/1/21.
 */
public class MatrixTest extends AppCompatActivity{

    private FmMatrix matrixFm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_matrix);
        matrixFm = new FmMatrix();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.content,matrixFm);
        transaction.commit();
    }
}
