package android.dmi.pmf.novica.myapplication.activity;

import android.dmi.pmf.novica.myapplication.R;
import android.dmi.pmf.novica.myapplication.adapter.DataAdapter;
import android.dmi.pmf.novica.myapplication.dao.Repository;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    @ViewById
    public ListView listView;

    @Bean
    DataAdapter dataAdapter;

    @Bean
    Repository repositoryBean;

    //after injected views != null
    @AfterViews
    void init(){
        dataAdapter.setDataItems(repositoryBean.getAllItems());

        listView.setAdapter(dataAdapter);
    }


}
