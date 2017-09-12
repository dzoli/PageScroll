package com.makaji.aleksej.pagescroller.view;

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

import com.makaji.aleksej.pagescroller.R;
import com.makaji.aleksej.pagescroller.adapter.PageScrollerAdapter;
import com.makaji.aleksej.pagescroller.listener.OnPageChangedListener;
import com.makaji.aleksej.pagescroller.repository.RepositoryBean;
import com.makaji.aleksej.pagescroller.repository.RepositoryClearingList;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import java.util.Locale;

/**
 * This class is view group which enables scrolling pages with animation, with possibility of modifying some custom attributes.
 * Class implements listener with callback method that is responsible for changing current page. Developer can choose between two implementations.
 */
@EViewGroup(resName = "page_counter")
public class PageScrollerView extends LinearLayout implements OnPageChangedListener {

    private static final int ANIMATION_SPEED_MAX = 280;
    private static final int ANIMATION_SPEED_MIN = 150;
    private static final int ANIMATION_SPEED_DEFAULT = 200;
    private static final int NUMBERS_FADING_DEFAULT = 0;
    private static final int HEIGHT_AND_TEXT_SIZE_DEFAULT = 0;
    private static final int HEIGHT_AND_TEXT_SIZE_MAX = 50;
    private static final int HEIGHT_AND_TEXT_SIZE_MIN = 16;
    private Integer currentPage = 0;
    private final String textColor;
    private final String textChange;
    private final Integer heightOfElementsAndTextSize;
    private final Integer numbersFading;
    private Integer animationSpeed;
    private Float textSize;

    private final RepositoryBean repositoryBean = new RepositoryClearingList(this);

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

    /**
     * Constructor in which we get custom attributes from xml
     *
     * @param context Context of a View.
     * @param attrs   A collection of attributes, as found associated with a tag in an XML document of a View.
     */
    public PageScrollerView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.PageScrollerView,
                0, 0);
        try {
            textColor = a.getString(R.styleable.PageScrollerView_textColor);
            heightOfElementsAndTextSize = a.getInteger(R.styleable.PageScrollerView_heightOfElementsAndTextSize, HEIGHT_AND_TEXT_SIZE_DEFAULT);
            textChange = a.getString(R.styleable.PageScrollerView_textChange);
            numbersFading = a.getInteger(R.styleable.PageScrollerView_numbersFading, NUMBERS_FADING_DEFAULT);
            animationSpeed = a.getInteger(R.styleable.PageScrollerView_animationSpeed, ANIMATION_SPEED_DEFAULT);
        } finally {
            a.recycle();
        }
    }

    /**
     * Method which is called after the views binding has happened
     */
    @AfterViews
    public void init() {
        final ViewGroup nullParent = null;

        //Inflating header and footer layout
        View footerAndHeaderView = ((LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE))
                .inflate(R.layout.page_scroller_view_header_and_footer_view, nullParent, false);

        checkCustomAttributesAndSetValues(footerAndHeaderView, heightOfElementsAndTextSize, textColor, textChange, numbersFading, animationSpeed);

        //Adding header and footer to ListView
        currentPageListView.addFooterView(footerAndHeaderView);
        currentPageListView.addHeaderView(footerAndHeaderView);

        //Disabling ListView so it can not be clicked or scrolled
        currentPageListView.setEnabled(false);

        //Setting data for adapter
        pageScrollerAdapter.setPagesNumber(repositoryBean.getItems());

        //Setting adapter for ListView
        currentPageListView.setAdapter(pageScrollerAdapter);
    }

    /**
     * Set max pages
     * Passed param is number of max pages, on which is also calculated width of list view.
     * Method call also repositoryBean which have two implementations
     *
     * @param maxPage Containing the max number of elements in ListView.
     */
    public void setMaxCount(Integer maxPage) {

        setWidthOfListBasedOnDigits(maxPage);

        repositoryBean.updateItems(maxPage, currentPage);

        //Refresh items in adapter
        pageScrollerAdapter.setPagesNumber(repositoryBean.getItems());

        maxPages.setText(String.format(Locale.getDefault(), "%d", maxPage));
    }

    /**
     * Set to current Page.
     * Calculates which item in list to show based on list view height and one of elements height.
     * As well, calculating width of list based on number of digits which are set as list elements.
     *
     * @param currPage Containing value of new current page in ListView.
     */
    public void setCurrentPage(Integer currPage) {
        int h1 = currentPageListView.getHeight();
        int h2 = maxPages.getHeight();
        currentPage = currPage;

        int nDigits = getNumberOfDigits(currPage);

        int widthOfOneChar = getWidthOfCharacter(slash);

        //set width of list
        LayoutParams paramsList = (LayoutParams) currentPageListView.getLayoutParams();
        paramsList.width = (widthOfOneChar + (widthOfOneChar / 5) + widthOfOneChar / 3) * (nDigits + 1);
        currentPageListView.setLayoutParams(paramsList);

        //Scroll to requested item with animation
        currentPageListView.smoothScrollToPositionFromTop(currPage, h1 / 2 - h2 / 2, animationSpeed);
    }


    /**
     * Set height and text size by custom attribute
     *
     * @param heightOfElementsAndTextSize Containing value of height and text size of View elements.
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
        paramsList.height = heightOfElementsAndTextSize * 3;
        paramsList.width = heightOfElementsAndTextSize / 2 + heightOfElementsAndTextSize / 3;
        currentPageListView.setFadingEdgeLength(heightOfElementsAndTextSize + heightOfElementsAndTextSize / 3 + heightOfElementsAndTextSize / 10);
        currentPageListView.setLayoutParams(paramsList);

        pageScrollerAdapter.setHeightOfElementsAndTextSize(heightOfElementsAndTextSize);

        //Remeasure and redraw layouts
        invalidate();
        requestLayout();
    }

    /**
     * Set color of elements by custom attribute
     *
     * @param colorCode A color value in the form 0xAARRGGBB that is represent as an Integer.
     */
    private void setTextColor(Integer colorCode) {
        maxPages.setTextColor(colorCode);
        page.setTextColor(colorCode);
        slash.setTextColor(colorCode);
        pageScrollerAdapter.setTextColor(colorCode);
    }

    /**
     * Get width of character or more characters from TextView
     *
     * @param textView A TextView element of which width will be calculated.
     * @return Width of character or more characters of a TextView
     */
    private int getWidthOfCharacter(TextView textView) {
        Rect bounds = new Rect();
        Paint textPaint = textView.getPaint();
        textPaint.getTextBounds(textView.getText().toString(), 0, textView.getText().length(), bounds);
        return bounds.width();
    }

    /**
     * Check if custom attribute is set and apply his value if set
     *
     * @param footerAndHeaderView         Footer and Header elements of a ListView.
     * @param heightOfElementsAndTextSize Height of View and text size of elements on a Custom View.
     * @param textColor                   String representation of a text color of View elements.
     * @param textChange                  String value of TextView page element.
     * @param numbersFading               Number of fading pixels.
     * @param animationSpeed              Number of animation speed when scrolling items in ListView expressed in milliseconds.
     */
    private void checkCustomAttributesAndSetValues(View footerAndHeaderView, Integer heightOfElementsAndTextSize, String textColor, String textChange,
                                                   Integer numbersFading, Integer animationSpeed) {

        //If custom attribute for height and text size is set, accept changes
        if (heightOfElementsAndTextSize != HEIGHT_AND_TEXT_SIZE_DEFAULT) {

            heightOfElementsAndTextSize = getHeightOfElementsAndTextSizeInScope(heightOfElementsAndTextSize);

            //scaling text and height of elements based on device density
            DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
            float scaledDensity = displayMetrics.scaledDensity;
            float heightDensity = heightOfElementsAndTextSize * scaledDensity;
            textSize = (float) (heightOfElementsAndTextSize - heightOfElementsAndTextSize / 4);
            heightOfElementsAndTextSize = (int) heightDensity;

            setHeightOfElementsAndTextSize(heightOfElementsAndTextSize);

            //set height of footer and header with custom attributes
            footerAndHeaderView.setLayoutParams(new ListView.LayoutParams(LayoutParams.MATCH_PARENT, heightOfElementsAndTextSize));
        }

        this.animationSpeed = getAnimationSpeedInScope(animationSpeed);

        if (textColor != null) {
            setTextColor(Color.parseColor(textColor));
        }

        if (textChange != null) {
            page.setText(textChange);
        }

        if (numbersFading != NUMBERS_FADING_DEFAULT) {
            currentPageListView.setFadingEdgeLength(numbersFading);
        }
    }

    /**
     * Set width of list based on number digits as item
     *
     * @param maxPage Max page number that is shown in ListView.
     */
    private void setWidthOfListBasedOnDigits(Integer maxPage) {

        int widthOfOneChar = getWidthOfCharacter(slash);

        LayoutParams paramsList = (LayoutParams) currentPageListView.getLayoutParams();
        if (currentPage > maxPage) {
            int nDigitsMax = getNumberOfDigits(maxPage);
            paramsList.width = (widthOfOneChar + (widthOfOneChar / 5) + widthOfOneChar / 3) * (nDigitsMax + 1);
        } else if (currentPage == 0) {
            paramsList.width = (widthOfOneChar + (widthOfOneChar / 5) + widthOfOneChar / 3) * (1 + 1);
            currentPage = 1;
        } else {
            int nDigitsCurrentPage = getNumberOfDigits(currentPage);
            paramsList.width = (widthOfOneChar + (widthOfOneChar / 5) + widthOfOneChar / 3) * (nDigitsCurrentPage + 1);
        }
        currentPageListView.setLayoutParams(paramsList);
    }

    /**
     * Limits for custom animation speed attribute
     *
     * @param animationSpeed Number of animation speed when scrolling items in ListView expressed in milliseconds.
     * @return Limited animation speed (scope is from 150 to 280).
     */
    private int getAnimationSpeedInScope(Integer animationSpeed) {
        return (animationSpeed > ANIMATION_SPEED_MAX) ?
                ANIMATION_SPEED_MAX : (animationSpeed < ANIMATION_SPEED_MIN) ?
                ANIMATION_SPEED_MIN : animationSpeed;
    }

    /**
     * Limits for custom height and text size attribute.
     *
     * @param heightOfElementsAndTextSize Height of View and text size of elements on a Custom View.
     * @return Limited height and text size (scope is from 16 to 50)
     */
    private int getHeightOfElementsAndTextSizeInScope(Integer heightOfElementsAndTextSize) {
        return (heightOfElementsAndTextSize > HEIGHT_AND_TEXT_SIZE_MAX) ?
                HEIGHT_AND_TEXT_SIZE_MAX : (heightOfElementsAndTextSize < HEIGHT_AND_TEXT_SIZE_MIN) ?
                HEIGHT_AND_TEXT_SIZE_MIN : heightOfElementsAndTextSize;
    }

    /**
     * Return number of digits for character place in a ListView.
     *
     * @param currPage Containing value of new current page in ListView.
     * @return Number of digits for character place.
     */
    private int getNumberOfDigits(Integer currPage) {
        return (int) (Math.floor(Math.log10(Math.abs(currPage))) + 1);
    }

    /**
     * Implementation of OnPageChangedListener callback method, for changing current pages in ListView.
     *
     * @param newPageValue New value of current page.
     */
    @Override
    public void pageChanged(Integer newPageValue) {
        setCurrentPage(newPageValue);
    }
}
