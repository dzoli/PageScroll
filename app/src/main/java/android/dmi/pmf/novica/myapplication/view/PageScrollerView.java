package android.dmi.pmf.novica.myapplication.view;

import android.content.Context;
import android.dmi.pmf.novica.myapplication.R;
import android.dmi.pmf.novica.myapplication.adapter.DataAdapter;
import android.dmi.pmf.novica.myapplication.dao.Repository;
import android.util.AttributeSet;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

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

    public PageScrollerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void initFor() {
        List<Integer> data = dataAdapter.getDataItems();

        data.add(0, 0);
        data.add(data.size(), data.size());
        dataAdapter.setPagesNumber(data);

        currentPageListView.setAdapter(dataAdapter);
    }

    public void setMaxCount(Integer max) {
        maxPages.setText(max.toString());
    }

    public void setCurrPage(final Integer currPage) {
        int h1 = currentPageListView.getHeight();
        int h2 = maxPages.getHeight();

        currentPageListView.smoothScrollToPositionFromTop(currPage, h1 / 2 - h2 / 2, 350);
    }

}