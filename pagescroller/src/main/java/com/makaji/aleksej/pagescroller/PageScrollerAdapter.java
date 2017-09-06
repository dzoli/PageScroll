package com.makaji.aleksej.pagescroller;

import android.content.Context;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Novica on 8/28/2017.
 */

@EBean
public class PageScrollerAdapter extends BaseAdapter {

    private List<Integer> dataItems = new ArrayList<>();

    @RootContext
    Context context;

    NumberItemView numberItemView;

    public Integer heightOfElementsAndTextSize;

    public Integer colorCode;

    @Override
    public int getCount() {
        return dataItems.size();
    }

    @Override
    public Object getItem(int i) {
        return dataItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final NumberItemView numberItemView;
        if (convertView == null) {
            numberItemView = NumberItemView_.build(context);
            if (heightOfElementsAndTextSize!=null) {
                numberItemView.setHeightOfElementsAndTextSize(heightOfElementsAndTextSize);
            }
            if (colorCode!=null) {
                numberItemView.setTextColor(colorCode);
            }

        } else {
            numberItemView = (NumberItemView) convertView;

        }

        numberItemView.bind((Integer) getItem(position));

        return numberItemView;
    }

    public void setPagesNumber(List<Integer> data) {
        dataItems = data;
        notifyDataSetChanged();
    }

    public List<Integer> getDataItems(){
        return dataItems;
    }

    public void setHeightOfElementsAndTextSize(Integer heightOfElementsAndTextSize) {
        this.heightOfElementsAndTextSize = heightOfElementsAndTextSize;
    }

    public void setTextColor(Integer colorCode) {
        this.colorCode = colorCode;
    }


}
