package com.makaji.aleksej.pagescroller.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.makaji.aleksej.pagescroller.view.NumberItemView;
import com.makaji.aleksej.pagescroller.view.NumberItemView_;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.ArrayList;
import java.util.List;

@EBean
public class PageScrollerAdapter extends BaseAdapter {

    private List<Integer> dataItems = new ArrayList<>();

    @RootContext
    Context context;

    private Integer heightOfElementsAndTextSize;

    private Integer colorCode;

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
            if (heightOfElementsAndTextSize != null) {
                numberItemView.setHeightOfElementsAndTextSize(heightOfElementsAndTextSize);
            }
            if (colorCode != null) {
                numberItemView.setTextColor(colorCode);
            }

        } else {
            numberItemView = (NumberItemView) convertView;

        }

        numberItemView.bind((Integer) getItem(position));

        //When we have 2 elements in list, hide 3rd added element
        if (dataItems.size() == 3 && position == 2 && dataItems.get(2).equals(-1)) {
            numberItemView.setVisibility(View.GONE);
        } else {
            numberItemView.setVisibility(View.VISIBLE);
        }

        return numberItemView;
    }

    public void setPagesNumber(List<Integer> data) {
        dataItems = data;
        notifyDataSetChanged();
    }

    public void setHeightOfElementsAndTextSize(Integer heightOfElementsAndTextSize) {
        this.heightOfElementsAndTextSize = heightOfElementsAndTextSize;
    }

    public void setTextColor(Integer colorCode) {
        this.colorCode = colorCode;
    }

}
