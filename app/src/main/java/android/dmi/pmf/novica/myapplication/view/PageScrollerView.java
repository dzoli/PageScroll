package android.dmi.pmf.novica.myapplication.view;

import android.content.Context;
import android.dmi.pmf.novica.myapplication.R;
import android.dmi.pmf.novica.myapplication.activity.MainActivity;
import android.dmi.pmf.novica.myapplication.adapter.DataAdapter;
import android.dmi.pmf.novica.myapplication.dao.Repository;
import android.util.AttributeSet;
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
//        ArrayAdapter<Integer> arrAdapter;
//        arrAdapter = new ArrayAdapter<Integer>(getContext(),
//                                                android.R.layout.simple_list_item_1,
//                                                repositoryBean.getAllItems());

        dataAdapter.setDataItems(repositoryBean.getAllItems());
        currentPageListView.setAdapter(dataAdapter);
    }

    public void setMaxPages(Integer max) {
        maxPages.setText(max.toString()); // check scope
    }

    public void setCurrPage(Integer currPage) {
//        currentPageListView.setSelection(currPage - 1); // check scope
        if((currPage < 0) || (currPage > 10)){
            Toast.makeText(getContext(), " == " + "Out of bound", Toast.LENGTH_SHORT).show();
            return;
        }
        currentPageListView.smoothScrollToPosition(currPage - 1);
    }

}
