package com.makaji.aleksej.pagescroller.view;

import android.content.Context;
import android.util.DisplayMetrics;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import java.util.Locale;

/**
 * View which is represented as item in list view
 */
@EViewGroup(resName = "item_view_number")
public class NumberItemView extends LinearLayout {

    @ViewById
    TextView numberTv;

    public NumberItemView(Context context) {
        super(context);
    }

    /**
     * Method in which binding happening for views
     * @param num Number which will be bind for number text view
     */
    public void bind(Integer num) {
        numberTv.setText(String.format(Locale.getDefault(), "%d", num));
    }

    /**
     * Set height of element in item list with custom attribute
     *
     * @param heightOfElementsAndTextSize - height and text size of View elements
     */
    public void setHeightOfElementsAndTextSize(Integer heightOfElementsAndTextSize) {

        //scaling text and height of elements based on device density
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        float scaledDensity = displayMetrics.scaledDensity;

        float textSize = (heightOfElementsAndTextSize / scaledDensity) - ((heightOfElementsAndTextSize / scaledDensity) / 4);

        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) numberTv.getLayoutParams();
        params.height = heightOfElementsAndTextSize;
        numberTv.setTextSize(textSize);
        numberTv.setLayoutParams(params);

        //Remeasure and redraw layout
        invalidate();
        requestLayout();
    }

    /**
     * Set color for text view
     *
     * @param colorCode A color value in the form 0xAARRGGBB that is represent as an Integer.
     */
    public void setTextColor(Integer colorCode) {
        numberTv.setTextColor(colorCode);
    }


}
