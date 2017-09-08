package com.makaji.aleksej.pagescroller.view;

import android.content.Context;
import android.util.DisplayMetrics;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import java.util.Locale;

@EViewGroup(resName = "item_view_number")
public class NumberItemView extends LinearLayout {

    @ViewById
    TextView numberTv;

    public NumberItemView(Context context) {
        super(context);
    }

    public void bind(Integer num){
        numberTv.setText(String.format(Locale.getDefault(), "%d", num));
    }

    /**
     * Set height of element in item list with custom attribute
     * @param heightOfElementsAndTextSize
     */
    public void setHeightOfElementsAndTextSize(Integer heightOfElementsAndTextSize) {

        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        float scaledDensity = displayMetrics.scaledDensity;

        float textSize =(heightOfElementsAndTextSize/scaledDensity)- ((heightOfElementsAndTextSize/scaledDensity)/4);

        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) numberTv.getLayoutParams();
        params.height = heightOfElementsAndTextSize;
        numberTv.setTextSize(textSize);
        numberTv.setLayoutParams(params);

        invalidate();
        requestLayout();
    }

    public void setTextColor(Integer colorCode) {
        numberTv.setTextColor(colorCode);
    }




}
