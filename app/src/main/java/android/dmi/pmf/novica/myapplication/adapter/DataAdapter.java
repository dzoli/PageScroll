package android.dmi.pmf.novica.myapplication.adapter;

import android.content.Context;
import android.dmi.pmf.novica.myapplication.dao.Repository;
import android.dmi.pmf.novica.myapplication.view.NumberItemView;
import android.dmi.pmf.novica.myapplication.view.NumberItemView_;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Novica on 8/28/2017.
 */

// List View adapter

@EBean
public class DataAdapter extends BaseAdapter {

    private List<Integer> dataItems = new ArrayList<>();

    @RootContext
    Context context;

    @Bean
    Repository repositoryBean;

    @AfterInject
    void inti() {
        setDataItems(repositoryBean.getAllItems());
    }

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
        } else {
            numberItemView = (NumberItemView) convertView;
        }

//        dataItemView.bind(getItem(position));

        numberItemView.bind((Integer) getItem(position));

        if (position == 0 || position == dataItems.size()-1) {
            numberItemView.setVisibility(View.GONE);
        }else{
            numberItemView.setVisibility(View.VISIBLE);
        }

        return numberItemView;
    }

    public void setDataItems(List<Integer> pages) {
        this.dataItems = pages;
        notifyDataSetChanged();
    }
}
