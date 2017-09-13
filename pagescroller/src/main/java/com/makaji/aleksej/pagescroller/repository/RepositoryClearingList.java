package com.makaji.aleksej.pagescroller.repository;

import com.makaji.aleksej.pagescroller.listener.OnPageChangedListener;

import java.util.List;

/**
 * This is implementation class that is responsible for manipulating whit items of list.
 * This class always clear list before manipulation.
 */
public class RepositoryClearingList extends RepositoryBean {

    private final OnPageChangedListener listener;

    private final Integer INVISIBLE_ELEMENT = -1;

    private final Integer FIRST_ELEMENT = 1;

    private final Integer SECOND_ELEMENT = 2;

    /**
     * This constructor stores listener for changing page.
     *
     * @param listener Listener with callback method.
     */
    public RepositoryClearingList(OnPageChangedListener listener) {
        super();
        this.listener = listener;
    }

    /**
     * Updates list of data that is shown in ListView. This method always clearing list
     * and then updates values again.
     *
     * @param maxPage     Value of max page that is shown in ListView.
     * @param currentPage Value of current page in ListView.
     */
    public void updateItems(Integer maxPage, Integer currentPage) {
        // always clear before modifications
        itemList.clear();

        // special case when invisible element is added
        if (maxPage == SECOND_ELEMENT) {
            for (int i = 1; i <= SECOND_ELEMENT; i++) {
                itemList.add(i);
            }
            itemList.add(INVISIBLE_ELEMENT);   // add invisible element to list

            // check if delete is occurred
            if (currentPage > maxPage) {
                listener.pageChanged(SECOND_ELEMENT);
            }
        } else {
            for (int i = 1; i <= maxPage; i++) {
                itemList.add(i);
            }

            // always explicitly set first element because of invisible element in the list
            if (maxPage == FIRST_ELEMENT) {
                listener.pageChanged(FIRST_ELEMENT);
            }
        }
    }

    /**
     * Getter method for parent class field {@link RepositoryBean#itemList}.
     *
     * @return List of data items.
     */
    public List<Integer> getItems() {
        return itemList;
    }
}