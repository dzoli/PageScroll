package com.makaji.aleksej.pagescroller.repository;

import org.androidannotations.annotations.EBean;

import java.util.ArrayList;
import java.util.List;

@EBean
public abstract class RepositoryBean {

    protected final List<Integer> itemList = new ArrayList<>();

    public abstract void updateItems(Integer maxPages, Integer currPage);

    public abstract List<Integer> getItems();

}
