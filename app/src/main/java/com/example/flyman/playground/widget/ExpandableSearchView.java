package com.example.flyman.playground.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.flyman.playground.R;


/**
 * The expandable search view.
 */
public class ExpandableSearchView extends LinearLayout{

    private ExpandableLayout mExpandableLayout;
    private ImageView mSearchIcon;
    public ExpandableSearchView(Context context) {
        super(context);
    }

    public ExpandableSearchView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ExpandableSearchView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    private void init(final Context context, AttributeSet attrs) {

        setBackground(null);
        setOrientation(VERTICAL);
        final View rootView = View.inflate(context, R.layout.search_expandable, this);
        mExpandableLayout = (ExpandableLayout) rootView.findViewById(R.id.expandable_layout);
        mSearchIcon = (ImageView) rootView.findViewById(R.id.search_icon);
        mSearchIcon.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(mExpandableLayout.isOpened()) {
                    mExpandableLayout.hide();
                }
                else {
                    mExpandableLayout.show();
                }
            }
        });
    }
}
