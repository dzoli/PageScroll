package com.makaji.aleksej.pagescroller.listener;

/**
 * This interface enables call of callback method between implementation classes
 * {@link com.makaji.aleksej.pagescroller.repository.RepositoryPreservingList},
 * {@link com.makaji.aleksej.pagescroller.repository.RepositoryPreservingList} and
 * {@link com.makaji.aleksej.pagescroller.view.PageScrollerView}
 */
public interface OnPageChangedListener {

    /**
     * Callback method that is called when page is changed from one of the implementation classes.
     *
     * @param newPageValue Value of new page that will be set to current page.
     */
    void pageChanged(Integer newPageValue);

}
