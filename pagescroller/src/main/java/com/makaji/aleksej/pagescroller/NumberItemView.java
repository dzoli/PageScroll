package com.makaji.aleksej.pagescroller;

import android.content.Context;
import android.util.TypedValue;
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

    public void setHeightOfElementsAndTextSize(Integer heightOfElementsAndTextSize) {

        //set height of element in item list with custom attribute
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) numberTv.getLayoutParams();
        params.height = heightOfElementsAndTextSize;
        numberTv.setTextSize(TypedValue.COMPLEX_UNIT_SP, heightOfElementsAndTextSize/4);
        numberTv.setLayoutParams(params);

        invalidate();
        requestLayout();
    }

    public void setTextColor(Integer colorCode) {
        numberTv.setTextColor(colorCode);
    }




}
