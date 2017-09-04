package com.makaji.aleksej.pagescroller;

import android.content.Context;

import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
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


    @ViewById
    public TextView page;

    @ViewById
    public TextView slash;

    String textColor;
    Integer mHeightOfElementsAndTextSize;

    public PageScrollerView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.PageScrollerView,
                0, 0);
        try {

            textColor = a.getString(R.styleable.PageScrollerView_textColor);
            mHeightOfElementsAndTextSize = a.getInteger(R.styleable.PageScrollerView_heightOfElementsAndTextSize, 0);

        } finally {
            a.recycle();
        }

    }

    @AfterViews
    public void init() {

        View footerAndHeaderView = ((LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE))
                .inflate(R.layout.page_scroller_view_header_and_foother_view, null, false);

        //header and footer for setting custom attribute
        footerAndHeaderView.setLayoutParams(new ListView.LayoutParams(LayoutParams.MATCH_PARENT,mHeightOfElementsAndTextSize));

        currentPageListView.addFooterView(footerAndHeaderView);
        currentPageListView.addHeaderView(footerAndHeaderView);

        currentPageListView.setEnabled(false);
        pageScrollerAdapter.setPagesNumber(itemList);
        currentPageListView.setAdapter(pageScrollerAdapter);

        if (mHeightOfElementsAndTextSize!=null) {
            setmHeightOfElementsAndTextSize(mHeightOfElementsAndTextSize);
        }
        if (textColor!=null){
            setTextColor(Color.parseColor(textColor));
        }
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

    public Integer getmHeightOfElementsAndTextSize() {
        return mHeightOfElementsAndTextSize;
    }

    public void setmHeightOfElementsAndTextSize(Integer heightOfElementsAndTextSize) {

        LayoutParams params = (LayoutParams) maxPages.getLayoutParams();
        params.height = heightOfElementsAndTextSize;
        params.setMargins(0, heightOfElementsAndTextSize, 0, 0);
        maxPages.setTextSize(TypedValue.COMPLEX_UNIT_SP, heightOfElementsAndTextSize/4);
        maxPages.setLayoutParams(params);

        LayoutParams paramsPage = (LayoutParams) page.getLayoutParams();
        paramsPage.height = heightOfElementsAndTextSize;
        paramsPage.setMargins(0, heightOfElementsAndTextSize, 0, 0);
        page.setTextSize(TypedValue.COMPLEX_UNIT_SP, heightOfElementsAndTextSize/4);
        page.setLayoutParams(paramsPage);

        LayoutParams paramsSlash = (LayoutParams) slash.getLayoutParams();
        paramsSlash.height = heightOfElementsAndTextSize;
        paramsSlash.setMargins(0, heightOfElementsAndTextSize, 0, 0);
        slash.setTextSize(TypedValue.COMPLEX_UNIT_SP, heightOfElementsAndTextSize/4);
        slash.setLayoutParams(paramsSlash);

        LayoutParams paramsList = (LayoutParams) currentPageListView.getLayoutParams();
        paramsList.height = heightOfElementsAndTextSize*3;
        currentPageListView.setFadingEdgeLength(heightOfElementsAndTextSize + heightOfElementsAndTextSize/2);
        currentPageListView.setLayoutParams(paramsList);

        pageScrollerAdapter.setHeightOfElementsAndTextSize(heightOfElementsAndTextSize);

        invalidate();
        requestLayout();
    }

    public void setTextColor(Integer colorCode) {
        maxPages.setTextColor(colorCode);
        page.setTextColor(colorCode);
        slash.setTextColor(colorCode);
        pageScrollerAdapter.setTextColor(colorCode);
    }
}