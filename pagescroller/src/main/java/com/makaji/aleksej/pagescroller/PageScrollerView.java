package com.makaji.aleksej.pagescroller;

import android.content.Context;

import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.LinearLayout;
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
public class PageScrollerView extends LinearLayout {

    @ViewById
    ListView currentPageListView;

    @ViewById
    TextView maxPages;

    @Bean
    PageScrollerAdapter pageScrollerAdapter;

    @ViewById
    TextView page;

    @ViewById
    TextView slash;

    NumberItemView numberItemView;

    List<Integer> itemList = new ArrayList<>();

    Integer currentPage = 0;
    Integer maxPageBefore = 0;

    String textColor;
    String textChange;
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
            textChange = a.getString(R.styleable.PageScrollerView_textChange);

        } finally {
            a.recycle();
        }

    }

    @AfterViews
    public void init() {

        View footerAndHeaderView = ((LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE))
                .inflate(R.layout.page_scroller_view_header_and_foother_view, null, false);

        //da moze biti bez custom attributa
        if (mHeightOfElementsAndTextSize!= 0) {
            //max da moze biti 120 a min 30
            if (mHeightOfElementsAndTextSize>120) {
                mHeightOfElementsAndTextSize = 120;
            } else if (mHeightOfElementsAndTextSize < 30) {
                mHeightOfElementsAndTextSize = 30;
            }
            setmHeightOfElementsAndTextSize(mHeightOfElementsAndTextSize);

            //header and footer for setting custom attribute
            footerAndHeaderView.setLayoutParams(new ListView.LayoutParams(LayoutParams.MATCH_PARENT,mHeightOfElementsAndTextSize));
        }

        if (textColor!=null){
            setTextColor(Color.parseColor(textColor));
        }

        //If custom attribute for changing text is set, accept changes
        if (textChange!=null) {
            page.setText(textChange);
        }

        currentPageListView.addFooterView(footerAndHeaderView);
        currentPageListView.addHeaderView(footerAndHeaderView);

        currentPageListView.setEnabled(false);
        pageScrollerAdapter.setPagesNumber(itemList);
        currentPageListView.setAdapter(pageScrollerAdapter);

    }

    public void setMaxCount(Integer maxPage) {

        if (maxPageBefore == 2) {
            itemList.remove(2);
            maxPageBefore = maxPage;
        }

        //Count how many digits have param integer maxPage

        //Get width of slash characther
        Rect bounds = new Rect();
        Paint textPaint = slash.getPaint();
        textPaint.getTextBounds(slash.getText().toString(), 0, slash.getText().length(), bounds);
        int widthOfOneChar = bounds.width();
        Log.d("setMaxCount", "text width: " + widthOfOneChar);

        LayoutParams paramsList = (LayoutParams) currentPageListView.getLayoutParams();
        if (currentPage > maxPage) {
            Log.d("ASD", "curr>>>>itemsize");
            int nDigitsMax = (int) (Math.floor(Math.log10(Math.abs(maxPage))) + 1);
            paramsList.width = (widthOfOneChar+(widthOfOneChar/5)+widthOfOneChar/3)*(nDigitsMax+1);
        } else if (currentPage == 0) {
            paramsList.width = (widthOfOneChar+(widthOfOneChar/5)+widthOfOneChar/3)*(1+1);
        } else {
                Log.d("ASD", "Else");
                int nDigitsCurrentPage = (int) (Math.floor(Math.log10(Math.abs(currentPage))) + 1);
                paramsList.width = (widthOfOneChar+(widthOfOneChar/5)+widthOfOneChar/3)*(nDigitsCurrentPage+1);
        }
        currentPageListView.setLayoutParams(paramsList);

        invalidate();
        requestLayout();




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
        } else if (maxPage > itemList.size()) {
            int numberElementsAdded = maxPage - itemList.size();
            for (int i = 1; i <= numberElementsAdded; i++) {
                itemList.add(itemList.size() + 1);
            }
            setCurrPage(currentPage);

            //ako se vrsi brisanje (deselekcija)
        } else if (maxPage < itemList.size()) {
            int numberElementsDeleted = itemList.size() - maxPage;
            itemList.subList(itemList.size() - numberElementsDeleted, itemList.size()).clear();
        }

        if (maxPage == 2) {
            itemList.add(-1);
            maxPageBefore = 2;
            if (currentPage > 2) {
                setCurrPage(2);
            }
        }


        pageScrollerAdapter.setPagesNumber(itemList);
        maxPages.setText(maxPage.toString());
        currentPageListView.setLayoutParams(paramsList);

    }

    //@Background
    public void setCurrPage(Integer currPage) {
        int h1 = currentPageListView.getHeight();
        int h2 = maxPages.getHeight();

        Log.d("PAGE", "curr Page" + currPage);
        currentPage = currPage;

        //Count how many digits have param integer maxPage
        int nDigits = (int) (Math.floor(Math.log10(Math.abs(currPage))) + 1);
        Log.d("setMaxCount", "digits: " + nDigits);

        //Get width of slash characther
        Rect bounds = new Rect();
        Paint textPaint = slash.getPaint();
        textPaint.getTextBounds(slash.getText().toString(), 0, slash.getText().length(), bounds);
        int widthOfOneChar = bounds.width();
        Log.d("setMaxCount", "text width: " + widthOfOneChar);

        LayoutParams paramsList = (LayoutParams) currentPageListView.getLayoutParams();
        paramsList.width = (widthOfOneChar+(widthOfOneChar/5)+widthOfOneChar/3)*(nDigits+1);
        currentPageListView.setLayoutParams(paramsList);



        currentPageListView.smoothScrollToPositionFromTop(currPage, h1 / 2 - h2 / 2, 200);

    }

    public int getNumberOfPages(){
        return itemList.size();
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
        paramsList.width = heightOfElementsAndTextSize/2 + heightOfElementsAndTextSize/2;
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