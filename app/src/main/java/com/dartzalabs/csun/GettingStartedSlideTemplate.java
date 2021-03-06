package com.dartzalabs.csun;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class GettingStartedSlideTemplate extends Fragment {

    private static final String ARG_LAYOUT_RES_ID = "layoutResId";

    public static GettingStartedSlideTemplate newInstance(int layoutResId) {
        GettingStartedSlideTemplate GettingStartedSlideTemplate = new GettingStartedSlideTemplate();

        Bundle args = new Bundle();
        args.putInt(ARG_LAYOUT_RES_ID, layoutResId);
        GettingStartedSlideTemplate.setArguments(args);

        return GettingStartedSlideTemplate;
    }

    private int layoutResId;

    public GettingStartedSlideTemplate() {}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getArguments() != null && getArguments().containsKey(ARG_LAYOUT_RES_ID))
            layoutResId = getArguments().getInt(ARG_LAYOUT_RES_ID);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(layoutResId, container, false);
    }

}