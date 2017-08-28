package android.dmi.pmf.novica.myapplication.dao;

import android.dmi.pmf.novica.myapplication.model.DataItem;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Novica on 8/28/2017.
 */

@EBean
public class Repository {

    private List<Integer> itemList = new ArrayList<>();

    //invoke after injected
    @AfterInject
    void init(){
        for (int i = 1; i <= 10; i++) {
            itemList.add(i);
        }
    }

    public List<Integer> getAllItems(){
        return itemList;
    }

    public int getCount(){
        return itemList.size();
    }
}
