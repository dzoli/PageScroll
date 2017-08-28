package android.dmi.pmf.novica.myapplication.adapter;

import android.content.Context;
import android.dmi.pmf.novica.myapplication.dao.Repository;
import android.dmi.pmf.novica.myapplication.model.DataItem;
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

    private List<DataItem> dataItems = new ArrayList<>();

    @RootContext
    Context context;

    @Bean
    Repository repositoryBean;

    @AfterInject
    void inti() {
        setConversations(repositoryBean.getAllItems());
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

        if (convertView == null) {

        }

        return null;
    }


    public void setConversations(List<DataItem> dataItems) {
        this.dataItems = dataItems;
        notifyDataSetChanged();
    }
}
