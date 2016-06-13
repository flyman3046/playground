package com.example.flyman.playground.widget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.ViewGroup.LayoutParams;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.flyman.playground.R;

/**
 * The popup view when user makes a selection
 */
public class SelectPopup {
    private PopupWindow mPopup;
    private View mContentView;
    private TextView mParkName;
    private TextView mParkAddress;

    public SelectPopup(final Context context) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mContentView = layoutInflater.inflate(R.layout.popup_content, null);

        mPopup = new PopupWindow(mContentView, LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
        mParkName = (TextView) mContentView.findViewById(R.id.park_location_name);
        mParkAddress = (TextView) mContentView.findViewById(R.id.park_address);

        mPopup.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mPopup.setOutsideTouchable(true);
    }

    public void showToolTip(final View anchor) {
        int[] anchorLocation = new int[2];
        anchor.getLocationOnScreen(anchorLocation);

        mContentView.measure(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        int contentWidth = mContentView.getMeasuredWidth();
        int contentHeight = mContentView.getMeasuredHeight();

        final int xOffset = -(contentWidth - anchor.getWidth()) / 2;
        final int yOffset = -(contentHeight + anchor.getHeight());
        if(mPopup != null && !mPopup.isShowing()) {
            mPopup.showAsDropDown(anchor, xOffset, yOffset);
        }
    }

    public final void hideToolTip() {
        if(isShowing()) {
            mPopup.dismiss();
        }
    }

    public boolean isShowing() {
        return mPopup != null && mPopup.isShowing();
    }

    public void setParkName(String parkName) {
        if(mParkName != null) {
            mParkName.setText(parkName);
        }
    }

    public void setParkAddress(String parkAddress) {
        if(parkAddress != null) {
            mParkAddress.setText(parkAddress);
        }
    }
}
