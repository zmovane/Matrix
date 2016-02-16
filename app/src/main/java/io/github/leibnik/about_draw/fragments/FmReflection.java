package io.github.leibnik.about_draw.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Droidroid on 2016/2/15.
 */
public class FmReflection extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(io.github.leibnik.about_draw.R.layout.fm_reflection,container,false);
        return view;
    }
}
