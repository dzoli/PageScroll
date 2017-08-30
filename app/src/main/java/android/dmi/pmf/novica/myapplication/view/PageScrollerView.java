package android.dmi.pmf.novica.myapplication.view;

import android.content.Context;
import android.dmi.pmf.novica.myapplication.R;
import android.dmi.pmf.novica.myapplication.adapter.DataAdapter;
import android.dmi.pmf.novica.myapplication.dao.Repository;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Novica on 8/28/2017.
 */


@EViewGroup(R.layout.page_counter)
public class PageScrollerView extends RelativeLayout {

    @ViewById
    public ListView currentPageListView;

    @ViewById
    public TextView maxPages;

    @Bean
    public Repository repositoryBean;

    @Bean
    public DataAdapter dataAdapter;

    List<Integer> itemList = new ArrayList<>();

    Integer maxItems = 0;

    public PageScrollerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setMaxCount(Integer max) {

        for (int i = 0; i <= max+1; i++) {
            itemList.add(i);
        }

        maxItems = max;

        dataAdapter.setPagesNumber(itemList);

        currentPageListView.setAdapter(dataAdapter);

        maxPages.setText(max.toString());
    }

    public void setCurrPage(final Integer currPage) {
        int h1 = currentPageListView.getHeight();
        int h2 = maxPages.getHeight();

        currentPageListView.smoothScrollToPositionFromTop(currPage, h1 / 2 - h2 / 2, 350);
    }

    public void addElements(Integer numberElements) {
        for (int i = 2; i <= numberElements+1; i++) {
            itemList.add(maxItems+i);
        }
        maxItems += numberElements;
        dataAdapter.notifyDataSetChanged();
        maxPages.setText(maxItems.toString());

    }

    public void deleteElements (Integer numberElements) {

        for (int i = 1; i <= numberElements; i++) {
            itemList.remove(maxItems);
            maxItems --;
        }

        dataAdapter.notifyDataSetChanged();
        maxPages.setText(maxItems.toString());
    }

    public void deleteElementsList (List<Integer> numberElementsList) {

        for (Integer el :
                numberElementsList) {
            itemList.remove(el);
            maxItems --;
        }

        dataAdapter.notifyDataSetChanged();
        maxPages.setText(maxItems.toString());
    }

}