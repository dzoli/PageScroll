package android.dmi.pmf.novica.myapplication.view;

import android.content.Context;
import android.dmi.pmf.novica.myapplication.R;
import android.dmi.pmf.novica.myapplication.activity.MainActivity;
import android.dmi.pmf.novica.myapplication.adapter.DataAdapter;
import android.dmi.pmf.novica.myapplication.dao.Repository;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EView;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.RootContext;
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
        List<Integer> data = repositoryBean.getAllItems();
        data.add(0, 0);
        data.add(data.size(), data.size());
        dataAdapter.setDataItems(data);
        currentPageListView.setAdapter(dataAdapter);
    }

    public void setMaxPages(Integer max) {
        maxPages.setText(max.toString());
    }

    public void setCurrPage(final Integer currPage) {
//        currentPageListView.smoothScrollToPosition(currPage-1);
//        smoothScrollToPositionFromTop(currentPageListView, currPage - 1);
//        currentPageListView.setSelection(currPage - 1);

        int h1 = currentPageListView.getHeight();
        int h2 = maxPages.getHeight();

        currentPageListView.smoothScrollToPositionFromTop(currPage, h1/2 - h2/2, 350);
    }


}
