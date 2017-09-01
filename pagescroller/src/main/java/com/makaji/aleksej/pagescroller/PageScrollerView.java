package com.makaji.aleksej.pagescroller;

import android.content.Context;

import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Novica on 8/28/2017.
 */


@EViewGroup(resName = "page_counter")
public class PageScrollerView extends RelativeLayout {

    @ViewById
    public ListView currentPageListView;

    @ViewById
    public TextView maxPages;

    @Bean
    public PageScrollerAdapter pageScrollerAdapter;

    List<Integer> itemList = new ArrayList<>();

    Integer currentPage = 0;

    public PageScrollerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @AfterViews
    public void init() {

        View footerAndHeaderView = ((LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE))
                .inflate(R.layout.page_scroller_view_header_and_foother_view, null, false);
        currentPageListView.addFooterView(footerAndHeaderView);
        currentPageListView.addHeaderView(footerAndHeaderView);

        currentPageListView.setEnabled(false);
        pageScrollerAdapter.setPagesNumber(itemList);
        currentPageListView.setAdapter(pageScrollerAdapter);
    }

    public void setMaxCount(Integer maxPage) {

        //ako je nula (prvi put da se kreira lista)
        if (itemList.size() == 0 || itemList.indexOf(0) == 0) {
            itemList.clear();   //ako se nista ne slekejtuje, da mogu prikazati nulu
            for (int i = 1; i <= maxPage; i++) {
                itemList.add(i);
            }

            //ako je maxpage 0
        } else if (maxPage == 0) {
            itemList.clear();
            itemList.add(0);
            setCurrPage(0);

            //ako se vrsi dodavanje
        }else if (maxPage > itemList.size()) {
            int numberElementsAdded = maxPage - itemList.size();
            for (int i = 1; i <= numberElementsAdded; i++) {
                itemList.add(itemList.size()+1);
            }
            setCurrPage(currentPage);

            //ako se vrsi brisanje (deselekcija)
        } else if (maxPage < itemList.size()) {
            int numberElementsDeleted = itemList.size()-maxPage;
            itemList.subList(itemList.size() - numberElementsDeleted, itemList.size()).clear();
        }

        pageScrollerAdapter.setPagesNumber(itemList);
        maxPages.setText(maxPage.toString());
    }

    @Background
    public void setCurrPage(final Integer currPage) {
        int h1 = currentPageListView.getHeight();
        int h2 = maxPages.getHeight();

        currentPage = currPage;
        currentPageListView.smoothScrollToPositionFromTop(currPage, h1 / 2 - h2 / 2, 200);
    }

    public int getNumberOfPages(){
        return itemList.size();
    }


}