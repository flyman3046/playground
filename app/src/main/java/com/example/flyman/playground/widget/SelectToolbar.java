package com.example.flyman.playground.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.example.flyman.playground.R;

/**
 * The toolbar style widget at the bottom
 */
public class SelectToolbar extends LinearLayout {

    public SelectToolbar(Context context) {
        this(context, null, 0);
    }

    public SelectToolbar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SelectToolbar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(final Context context) {
        setBackground(null);
        setOrientation(HORIZONTAL);
        View.inflate(context, R.layout.select_view_layout, this);
    }
}
