package com.makaji.aleksej.pagescroller;

import android.content.Context;

import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


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

    private final List<Integer> itemList = new ArrayList<>();

    private Integer currentPage = 0;
    private Integer maxPageBefore = 0;
    private String textColor;
    private String textChange;
    private Integer heightOfElementsAndTextSize;
    private Integer numbersFading;
    private Integer animationSpeed;
    private Float textSize;

    public PageScrollerView(Context context, AttributeSet attrs) {
        super(context, attrs);

        //get custom attributes
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.PageScrollerView,
                0, 0);
        try {
            textColor = a.getString(R.styleable.PageScrollerView_textColor);
            heightOfElementsAndTextSize = a.getInteger(R.styleable.PageScrollerView_heightOfElementsAndTextSize, 0);
            textChange = a.getString(R.styleable.PageScrollerView_textChange);
            numbersFading = a.getInteger(R.styleable.PageScrollerView_numbersFading, 0);
            animationSpeed = a.getInteger(R.styleable.PageScrollerView_animationSpeed, 200);
        } finally {
            a.recycle();
        }
    }

    @AfterViews
    public void init() {

        final ViewGroup nullParent = null;
        View footerAndHeaderView = ((LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE))
                .inflate(R.layout.page_scroller_view_header_and_footer_view, nullParent, false);

        //If custom attribute for height and text size is set, accept changes
        if (heightOfElementsAndTextSize != 0) {

            //max height can be 50 while min 16
            if (heightOfElementsAndTextSize >50) {
                heightOfElementsAndTextSize = 50;
            } else if (heightOfElementsAndTextSize < 16) {
                heightOfElementsAndTextSize =16;
            }

            //scalling text and height of elements based on device density
            DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
            float scaledDensity = displayMetrics.scaledDensity;
            float heightDensity = heightOfElementsAndTextSize *scaledDensity;
            textSize = (float)(heightOfElementsAndTextSize - heightOfElementsAndTextSize /4);
            heightOfElementsAndTextSize = (int)heightDensity;

            setHeightOfElementsAndTextSize(heightOfElementsAndTextSize);

            //set height of footer and header with custom attributes
            footerAndHeaderView.setLayoutParams(new ListView.LayoutParams(LayoutParams.MATCH_PARENT, heightOfElementsAndTextSize));
        }

        //Limits for custom animation speed attribute
        if (animationSpeed>350) {
            animationSpeed = 350;
        } else if (animationSpeed < 150) {
            animationSpeed = 150;
        }

        //If custom attribute for color is set, accept changes
        if (textColor!=null){
            setTextColor(Color.parseColor(textColor));
        }

        //If custom attribute for changing text is set, accept changes
        if (textChange!=null) {
            page.setText(textChange);
        }

        //If custom attribute for changing fade is set, accept changes
        if (numbersFading!=0) {
            currentPageListView.setFadingEdgeLength(numbersFading);
        }

        currentPageListView.addFooterView(footerAndHeaderView);
        currentPageListView.addHeaderView(footerAndHeaderView);

        currentPageListView.setEnabled(false);
        pageScrollerAdapter.setPagesNumber(itemList);
        currentPageListView.setAdapter(pageScrollerAdapter);

    }

    public void setMaxCount(Integer maxPage) {

        //Get width of slash character
        int widthOfOneChar = getWidthOfCharacter(slash);

        //Set width of list depending on number digits as item
        LayoutParams paramsList = (LayoutParams) currentPageListView.getLayoutParams();
        if (currentPage > maxPage) {
            int nDigitsMax = (int) (Math.floor(Math.log10(Math.abs(maxPage))) + 1);
            paramsList.width = (widthOfOneChar+(widthOfOneChar/5)+widthOfOneChar/3)*(nDigitsMax+1);
        } else if (currentPage == 0) {
            paramsList.width = (widthOfOneChar+(widthOfOneChar/5)+widthOfOneChar/3)*(1+1);
            currentPage = 1;
        } else {
                int nDigitsCurrentPage = (int) (Math.floor(Math.log10(Math.abs(currentPage))) + 1);
                paramsList.width = (widthOfOneChar+(widthOfOneChar/5)+widthOfOneChar/3)*(nDigitsCurrentPage+1);
        }
        currentPageListView.setLayoutParams(paramsList);

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
            setCurrPage(0);

            //Adding to list
        } else if (maxPage > itemList.size()) {
            int numberElementsAdded = maxPage - itemList.size();
            for (int i = 1; i <= numberElementsAdded; i++) {
                itemList.add(itemList.size() + 1);
            }
            setCurrPage(currentPage);

            //Removing from list
        } else if (maxPage < itemList.size()) {
            int numberElementsDeleted = itemList.size() - maxPage;
            itemList.subList(itemList.size() - numberElementsDeleted, itemList.size()).clear();
            if (currentPage < itemList.size()) {
                setCurrPage(currentPage);
            }
        }

        //if list consist 2 elements, add one more element to fix scrolling bug (later we remove it)
        if (maxPage == 2) {
            itemList.add(-1);
            maxPageBefore = 2;
            if (currentPage > 2) {
                setCurrPage(2);
            }
        }

        if (maxPage == 1) {
            setCurrPage(1);
        }

        pageScrollerAdapter.setPagesNumber(itemList);
        maxPages.setText(String.format(Locale.getDefault(),"%d", maxPage));
        currentPageListView.setLayoutParams(paramsList);

    }

    //@param currPage -set current page
    public void setCurrPage(Integer currPage) {
        int h1 = currentPageListView.getHeight();
        int h2 = maxPages.getHeight();

        currentPage = currPage;

        //Count how many digits have param integer maxPage
        int nDigits = (int) (Math.floor(Math.log10(Math.abs(currPage))) + 1);
        //Get width of character
        int widthOfOneChar = getWidthOfCharacter(slash);

        //set width of list
        LayoutParams paramsList = (LayoutParams) currentPageListView.getLayoutParams();
        paramsList.width = (widthOfOneChar+(widthOfOneChar/5)+widthOfOneChar/3)*(nDigits+1);
        currentPageListView.setLayoutParams(paramsList);

        //Scroll to requested item with animation
        currentPageListView.smoothScrollToPositionFromTop(currPage, h1 / 2 - h2 / 2, animationSpeed);
    }

    //set height and text size by custom attribute
    private void setHeightOfElementsAndTextSize(Integer heightOfElementsAndTextSize) {

        LayoutParams params = (LayoutParams) maxPages.getLayoutParams();
        params.height = heightOfElementsAndTextSize;
        params.setMargins(0, heightOfElementsAndTextSize, 0, 0);
        maxPages.setTextSize(textSize);
        maxPages.setLayoutParams(params);

        LayoutParams paramsPage = (LayoutParams) page.getLayoutParams();
        paramsPage.height = heightOfElementsAndTextSize;
        paramsPage.setMargins(0, heightOfElementsAndTextSize, 0, 0);
        page.setTextSize(textSize);
        page.setLayoutParams(paramsPage);

        LayoutParams paramsSlash = (LayoutParams) slash.getLayoutParams();
        paramsSlash.height = heightOfElementsAndTextSize;
        paramsSlash.setMargins(0, heightOfElementsAndTextSize, 0, 0);
        slash.setTextSize(textSize);
        slash.setLayoutParams(paramsSlash);

        LayoutParams paramsList = (LayoutParams) currentPageListView.getLayoutParams();
        paramsList.height = heightOfElementsAndTextSize*3;
        paramsList.width = heightOfElementsAndTextSize/2 + heightOfElementsAndTextSize/3;
        currentPageListView.setFadingEdgeLength(heightOfElementsAndTextSize + heightOfElementsAndTextSize/3 + heightOfElementsAndTextSize/10);
        currentPageListView.setLayoutParams(paramsList);

        pageScrollerAdapter.setHeightOfElementsAndTextSize(heightOfElementsAndTextSize);

        invalidate();
        requestLayout();
    }

    //set color of elements by custom attribute
    private void setTextColor(Integer colorCode) {
        maxPages.setTextColor(colorCode);
        page.setTextColor(colorCode);
        slash.setTextColor(colorCode);
        pageScrollerAdapter.setTextColor(colorCode);
    }

    //Get width of character or more characters from textView
    private int getWidthOfCharacter(TextView textView) {
        Rect bounds = new Rect();
        Paint textPaint = textView.getPaint();
        textPaint.getTextBounds(textView.getText().toString(), 0, textView.getText().length(), bounds);
        return bounds.width();
    }

}