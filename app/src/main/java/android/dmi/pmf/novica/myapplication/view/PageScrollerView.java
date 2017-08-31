package android.dmi.pmf.novica.myapplication.view;

import android.content.Context;
import android.dmi.pmf.novica.myapplication.R;
import android.dmi.pmf.novica.myapplication.adapter.DataAdapter;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.RootContext;
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
    public DataAdapter dataAdapter;

    List<Integer> itemList = new ArrayList<>();

    Integer currentPage = 0;

    public PageScrollerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void initWithMaxPages(Integer max) {
        for (int i = 0; i <= max+1; i++) {
            itemList.add(i);
        }

        View footerAndHeaderView = ((LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE))
                .inflate(R.layout.page_scroller_view_header_and_foother_view, null, false);
        currentPageListView.addFooterView(footerAndHeaderView);
        currentPageListView.addHeaderView(footerAndHeaderView);

        currentPageListView.setEnabled(false);
        dataAdapter.setPagesNumber(itemList);
        currentPageListView.setAdapter(dataAdapter);
        maxPages.setText(max.toString());
    }

    @Background
    public void setCurrentPage(final Integer currPage) {
        int h1 = currentPageListView.getHeight();
        int h2 = maxPages.getHeight();

        currentPage = currPage;
        currentPageListView.smoothScrollToPositionFromTop(currPage, h1 / 2 - h2 / 2, 200);
    }

    public void addElements(Integer numberElements) {
        for (int i = 1; i <= numberElements; i++) {
            itemList.add(itemList.size());
        }

        dataAdapter.notifyDataSetChanged();
        maxPages.setText(Integer.toString(itemList.size()-2));
    }

    public void deleteElements (Integer numberElements) {
        itemList.subList(itemList.size() - numberElements, itemList.size()).clear();
        dataAdapter.notifyDataSetChanged();
        maxPages.setText(Integer.toString(itemList.size()-2));
    }

    public void deleteElementsList (List<Integer> numberElementsList) {
        for (Integer el : numberElementsList) {
            if (currentPage >= el) {
                itemList.remove(el);
                updateList();
                setCurrentPage(currentPage-1);
            } else {
                itemList.remove(el);
                updateList();
            }
        }

        dataAdapter.notifyDataSetChanged();
        maxPages.setText(Integer.toString(itemList.size()-2));
    }

    public int getNumberOfPages(){
        return itemList.size()-2;
    }

    private void updateList() {
        for (int i = 0; i <= itemList.size()-1; i++) {
            itemList.set(i, i);
        }
    }
}