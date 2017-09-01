package com.makaji.aleksej.pagescroller;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

/**
 * Created by Aleksej on 9/1/2017.
 */

@EViewGroup(resName = "item_view_number")
public class NumberItemView extends LinearLayout {

    @ViewById
    public TextView numberTv;

    public NumberItemView(Context context) {
        super(context);
    }

    public void bind(Integer num){
        numberTv.setText(num.toString());
    }

    public int getNumber(){
        return Integer.parseInt(numberTv.getText().toString());
    }

    public void setNumber(String number) {
        numberTv.setText(number);
    }
}
