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

import com.shawnlin.numberpicker.NumberPicker;

import org.androidannotations.annotations.AfterTextChange;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.TextChange;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    @ViewById
    public PageScrollerView pageScrollerCustomView;

    @Bean
    public Repository repositoryBean;

    @ViewById
    public NumberPicker number_picker;

    //after injected views != null
    @AfterViews
    void init(){
        pageScrollerCustomView.initFor();
        pageScrollerCustomView.setMaxPages(repositoryBean.getCount());
        number_picker.setMaxValue(repositoryBean.getCount());

        number_picker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal)
            {
                pageScrollerCustomView.setCurrPage(number_picker.getValue());
            }
        });
    }

}
