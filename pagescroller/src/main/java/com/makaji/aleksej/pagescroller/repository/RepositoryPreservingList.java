package com.makaji.aleksej.pagescroller.repository;

import com.makaji.aleksej.pagescroller.listener.OnPageChangedListener;

import java.util.List;

public class RepositoryPreservingList extends RepositoryBean {

    private OnPageChangedListener listener;

    private Integer maxPageBefore = 0;

    public RepositoryPreservingList(OnPageChangedListener listener) {
        this.listener = listener;
    }

    @Override
    public void addItems(Integer maxPage, Integer currentPage) {
        //if list consisted 2 items before, we remove 1 element which we added before, to fix scrolling bug
        if (maxPageBefore == 2) {
            itemList.remove(2);
            maxPageBefore = maxPage;
        }

        //If item list was zero, add items (initialize list)
        if (itemList.size() == 0 || itemList.indexOf(0) == 0) {
            itemList.clear();   //
            for (int i = 1; i <= maxPage; i++) {
                itemList.add(i);
            }

            //If nothing is selected, show 0 pages
        } else if (maxPage == 0) {
            itemList.clear();
            itemList.add(0);
            listener.pageChanged(0);

            //Adding to list
        } else if (maxPage > itemList.size()) {
            int numberElementsAdded = maxPage - itemList.size();
            for (int i = 1; i <= numberElementsAdded; i++) {
                itemList.add(itemList.size() + 1);
            }
            listener.pageChanged(currentPage);

            //Removing from list
        } else if (maxPage < itemList.size()) {
            int numberElementsDeleted = itemList.size() - maxPage;
            itemList.subList(itemList.size() - numberElementsDeleted, itemList.size()).clear();
            if (currentPage < itemList.size()) {
                listener.pageChanged(currentPage);
            }
        }

        //if list consist 2 elements, add one more element to fix scrolling bug (later we remove it)
        if (maxPage == 2) {
            itemList.add(-1);
            maxPageBefore = 2;
            if (currentPage > 2) {
                listener.pageChanged(2);
            }
        }

        if (maxPage == 1) {
            listener.pageChanged(1);
        }

    }

    @Override
    public List<Integer> getItems() {
        return itemList;
    }
}
