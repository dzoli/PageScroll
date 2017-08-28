package android.dmi.pmf.novica.myapplication.fragment;

import android.dmi.pmf.novica.myapplication.R;
import android.dmi.pmf.novica.myapplication.adapter.DataAdapter;
import android.dmi.pmf.novica.myapplication.dao.Repository;
import android.support.v4.app.Fragment;
import android.widget.EditText;
import android.widget.TextView;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

/**
 * Created by Novica on 8/28/2017.
 */

@EFragment(R.layout.fragment_page_counter)
public class FragmentPageCounter extends Fragment {

    @ViewById
    public TextView currentPage;

    @ViewById
    public TextView maxPages;

    @ViewById
    public EditText skipToPage;

    @Bean
    public Repository repositoryBean;

    @Bean
    public DataAdapter dataAdapter;

    @AfterInject
    public void init(){
        currentPage.setText("1");
        maxPages.setText(repositoryBean.getCount());

    }



}
