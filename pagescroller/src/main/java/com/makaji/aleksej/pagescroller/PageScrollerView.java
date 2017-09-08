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

/**
 * This class is view group which enables scrolling pages with animation, with possibility of modifying some custom attributes
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

    private final List<Integer> itemList = new ArrayList<>();
    private Integer currentPage = 0;
    private String textColor;
    private String textChange;
    private Integer heightOfElementsAndTextSize;
    private Integer numbersFading;
    private Integer animationSpeed;
    private Float textSize;

    /**
     * Constructor in which we get custom attributes from xml
     * @param context
     * @param attrs
     */
    public PageScrollerView(Context context, AttributeSet attrs) {
        super(context, attrs);

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

        checkCustomAttributesAndSetValues(footerAndHeaderView, heightOfElementsAndTextSize, textColor, textChange, numbersFading, animationSpeed );

        currentPageListView.addFooterView(footerAndHeaderView);
        currentPageListView.addHeaderView(footerAndHeaderView);

        currentPageListView.setEnabled(false);
        pageScrollerAdapter.setPagesNumber(itemList);
        currentPageListView.setAdapter(pageScrollerAdapter);
    }

    /**
     * Set max pages
     * @param maxPage
     */
    public void setMaxCount(Integer maxPage) {

        setWidthOfListBasedOnDigits(maxPage);

        itemList.clear();
        //special case if there are 2 elements in list, due to scrolling bug
        if (maxPage == 2) {
            for (int i = 1; i <= 2; i++) {
                itemList.add(i);
            }
            itemList.add(-1);
            if (currentPage > maxPage){
                setCurrPage(2);
            }
        }else {
            for (int i = 1; i <= maxPage ; i++) {
                itemList.add(i);
            }
            if (maxPage == 1) {
                setCurrPage(1);
            }
        }

        pageScrollerAdapter.setPagesNumber(itemList);
        maxPages.setText(String.format(Locale.getDefault(),"%d", maxPage));
    }

    /**
     * Set to current Page
     * @param currPage -current page
     */
    public void setCurrPage(Integer currPage) {
        int h1 = currentPageListView.getHeight();
        int h2 = maxPages.getHeight();
        currentPage = currPage;

        //Count how many digits have param integer maxPage
        int nDigits = (int) (Math.floor(Math.log10(Math.abs(currPage))) + 1);

        int widthOfOneChar = getWidthOfCharacter(slash);

        //set width of list
        LayoutParams paramsList = (LayoutParams) currentPageListView.getLayoutParams();
        paramsList.width = (widthOfOneChar+(widthOfOneChar/5)+widthOfOneChar/3)*(nDigits+1);
        currentPageListView.setLayoutParams(paramsList);

        //Scroll to requested item with animation
        currentPageListView.smoothScrollToPositionFromTop(currPage, h1 / 2 - h2 / 2, animationSpeed);
    }

    /**
     * Set height and text size by custom attribute
     * @param heightOfElementsAndTextSize
     */
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

    /**
     * Set color of elements by custom attribute
     * @param colorCode
     */
    private void setTextColor(Integer colorCode) {
        maxPages.setTextColor(colorCode);
        page.setTextColor(colorCode);
        slash.setTextColor(colorCode);
        pageScrollerAdapter.setTextColor(colorCode);
    }

    /**
     * Get width of character or more characters from TextView
     * @param textView -width of text
     * @return width of character or more characters
     */
    private int getWidthOfCharacter(TextView textView) {
        Rect bounds = new Rect();
        Paint textPaint = textView.getPaint();
        textPaint.getTextBounds(textView.getText().toString(), 0, textView.getText().length(), bounds);
        return bounds.width();
    }

    /**
     * Check if custom attribute is set and apply his value if set
     * @param footerAndHeaderView
     * @param heightOfElementsAndTextSize
     * @param textColor
     * @param textChange
     * @param numbersFading
     * @param animationSpeed
     */
    private void checkCustomAttributesAndSetValues(View footerAndHeaderView, Integer heightOfElementsAndTextSize, String textColor, String textChange,
                                       Integer numbersFading, Integer animationSpeed) {

        //If custom attribute for height and text size is set, accept changes
        if (heightOfElementsAndTextSize != 0) {

            heightOfElementsAndTextSize = getHeightOfElementsAndTextSizeInScope(heightOfElementsAndTextSize);

            //scaling text and height of elements based on device density
            DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
            float scaledDensity = displayMetrics.scaledDensity;
            float heightDensity = heightOfElementsAndTextSize *scaledDensity;
            textSize = (float)(heightOfElementsAndTextSize - heightOfElementsAndTextSize /4);
            heightOfElementsAndTextSize = (int)heightDensity;

            setHeightOfElementsAndTextSize(heightOfElementsAndTextSize);

            //set height of footer and header with custom attributes
            footerAndHeaderView.setLayoutParams(new ListView.LayoutParams(LayoutParams.MATCH_PARENT, heightOfElementsAndTextSize));
        }

        this.animationSpeed = getAnimationSepeedInScope(animationSpeed);

        if (textColor!=null){
            setTextColor(Color.parseColor(textColor));
        }

        if (textChange!=null) {
            page.setText(textChange);
        }

        if (numbersFading!=0) {
            currentPageListView.setFadingEdgeLength(numbersFading);
        }
    }

    /**
     * Set width of list based on number digits as item
     * @param maxPage
     */
    void setWidthOfListBasedOnDigits(Integer maxPage) {

        int widthOfOneChar = getWidthOfCharacter(slash);

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
    }

    /**
     * Limits for custom animation speed attribute
     * @param animationSpeed
     * @return limited animation speed
     */
    private int getAnimationSepeedInScope(Integer animationSpeed) {
        return (animationSpeed > 280) ?
                280 : (animationSpeed < 150) ?
                150 : animationSpeed;
    }

    /**
     * Limits for custom height and text size attribute
     * @param heightOfElementsAndTextSize
     * @return limited height and text size
     */
    private int getHeightOfElementsAndTextSizeInScope(Integer heightOfElementsAndTextSize) {
        return (heightOfElementsAndTextSize > 50) ?
                50 : (heightOfElementsAndTextSize < 16) ?
                16 : heightOfElementsAndTextSize;
    }
}