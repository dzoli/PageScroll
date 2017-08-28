package android.dmi.pmf.novica.myapplication.activity;

import android.dmi.pmf.novica.myapplication.R;
import android.dmi.pmf.novica.myapplication.adapter.DataAdapter;
import android.dmi.pmf.novica.myapplication.dao.Repository;
import android.dmi.pmf.novica.myapplication.view.PageScrollerView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.androidannotations.annotations.AfterTextChange;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.TextChange;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    @ViewById
    public EditText setPage;

    @ViewById
    public PageScrollerView pageScrollerCustomView;

    @Bean
    public Repository repositoryBean;

    //after injected views != null
    @AfterViews
    void init(){
        pageScrollerCustomView.initFor();
        setPage.setText("12s");

        pageScrollerCustomView.setMaxPages(repositoryBean.getCount());
    }

    @AfterTextChange
    void setPageAfterTextChanged(TextView setPage) {
//        Toast.makeText(this, " == " + setPage.getText().toString(), Toast.LENGTH_SHORT).show();
        try {
            Integer newPage = Integer.parseInt(setPage.getText().toString());
            pageScrollerCustomView.setCurrPage(newPage);
        } catch (Exception e) {
            Toast.makeText(this, "== err ==", Toast.LENGTH_SHORT).show();
            Log.d("=== MainActivity ", "NumberFormatException");
        }
    }

}
