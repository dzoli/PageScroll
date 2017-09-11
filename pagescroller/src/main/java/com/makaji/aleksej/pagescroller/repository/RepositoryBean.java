package com.makaji.aleksej.pagescroller.repository;

import org.androidannotations.annotations.EBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Novica on 9/8/2017.
 */

@EBean
public abstract class RepositoryBean {

    protected List<Integer> itemList = new ArrayList<>();

    public RepositoryBean() {
    }

    public abstract void addItems(Integer maxPages, Integer currPage);

    public abstract List<Integer> getItems();

}
