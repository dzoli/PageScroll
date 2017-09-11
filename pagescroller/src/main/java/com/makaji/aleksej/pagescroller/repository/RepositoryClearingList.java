package com.makaji.aleksej.pagescroller.repository;

import com.makaji.aleksej.pagescroller.listener.OnPageChangedListener;

import java.util.List;

public class RepositoryClearingList extends RepositoryBean {

    private OnPageChangedListener listener;

    public RepositoryClearingList(OnPageChangedListener listener) {
        super();
        this.listener = listener;
    }

    /**
     * Updates list of data that is shown in ListView. This method always clearing list
     * and then updates values again.
     *
     * @param maxPage Value of max page that is shown in ListView.
     * @param currentPage Value of current page in ListView.
     */
    public void updateItems(Integer maxPage, Integer currentPage) {
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

    /**
     * Getter
     *
     * @return List of data items.
     */
    public List<Integer> getItems() {
        return itemList;
    }
}
