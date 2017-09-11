package com.makaji.aleksej.pagescroller.repository;

import com.makaji.aleksej.pagescroller.listener.OnPageChangedListener;

import java.util.List;

public class RepositoryClearingList extends RepositoryBean {

    private OnPageChangedListener listener;

    public RepositoryClearingList(OnPageChangedListener listener) {
        this.listener = listener;
    }

    public RepositoryClearingList() {
        super();
    }

    public void addItems(Integer maxPage, Integer currentPage) {
        itemList.clear();
        if (maxPage == 2) {
            for (int i = 1; i <= 2; i++) {
                itemList.add(i);
            }
            itemList.add(-1);
            if (currentPage > maxPage) {
                listener.pageChanged(2);
            }
        } else {
            for (int i = 1; i <= maxPage; i++) {
                itemList.add(i);
            }
            if (maxPage == 1) {
                listener.pageChanged(1);
            }
        }
    }

    public List<Integer> getItems() {
        return itemList;
    }
}
