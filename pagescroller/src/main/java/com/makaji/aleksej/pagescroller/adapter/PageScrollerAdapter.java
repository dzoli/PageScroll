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

/**
 * Adapter class handles in which way data should be represented
 */
@EBean
public class PageScrollerAdapter extends BaseAdapter {

    private List<Integer> dataItems = new ArrayList<>();

    @RootContext
    Context context;

    private Integer heightOfElementsAndTextSize;

    private Integer colorCode;

    /**
     * How many items are in the data set represented by this Adapter
     * @return Number of items in adapter
     */
    @Override
    public int getCount() {
        return dataItems.size();
    }

    /**
     * Get the data item associated with the specified position in the data set.
     * @param i Position in adapter
     * @return Object from selected position
     */
    @Override
    public Object getItem(int i) {
        return dataItems.get(i);
    }

    /**
     * Get the row id associated with the specified position in the list.
     * @param i Position in adapter
     * @return Object Id from selected position
     */
    @Override
    public long getItemId(int i) {
        return i;
    }

    /**
     * Get a View that displays the data at the specified position in the data set.
     * @param position Position of item in adapter
     * @param convertView Reused view
     * @param parent View group parent
     * @return View
     */
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

    /**
     * Set data for adapter
     * @param data List which will be represented by adapter
     */
    public void setPagesNumber(List<Integer> data) {
        dataItems = data;
        notifyDataSetChanged();
    }

    /**
     * Set height of element in item list with custom attribute
     * @param heightOfElementsAndTextSize Height of View and text size of elements on a Custom View.
     */
    public void setHeightOfElementsAndTextSize(Integer heightOfElementsAndTextSize) {
        this.heightOfElementsAndTextSize = heightOfElementsAndTextSize;
    }

    /**
     * Set color
     * @param colorCode A color value in the form 0xAARRGGBB that is represent as an Integer
     */
    public void setTextColor(Integer colorCode) {
        this.colorCode = colorCode;
    }
}
