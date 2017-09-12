package com.makaji.aleksej.pagescroller.repository;

import org.androidannotations.annotations.EBean;

import java.util.ArrayList;
import java.util.List;


/**
 * Abstract class that represents repository that contains one List of data and have methods
 * that manipulate with list.
 */
@EBean
public abstract class RepositoryBean {

    final List<Integer> itemList = new ArrayList<>();

    /**
     * Do modification of a list with always clearing or preserving list items.
     * That decision is on to child classes.
     *
     * @param maxPages Value of max page that is shown in ListView.
     * @param currPage Value of current page in ListView.
     */
    public abstract void updateItems(Integer maxPages, Integer currPage);

    /**
     * Getter method for {@link RepositoryBean#itemList}
     *
     * @return Items of list.
     */
    public abstract List<Integer> getItems();
}
