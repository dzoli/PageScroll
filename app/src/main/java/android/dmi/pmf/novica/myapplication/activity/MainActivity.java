package android.dmi.pmf.novica.myapplication.activity;

import android.dmi.pmf.novica.myapplication.R;
import android.dmi.pmf.novica.myapplication.dao.Repository;
import android.dmi.pmf.novica.myapplication.view.PageScrollerView;
import android.support.v7.app.AppCompatActivity;
import android.widget.NumberPicker;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    @ViewById
    public PageScrollerView pageScrollerCustomView;

    @Bean
    public Repository repositoryBean;

    @ViewById
    public NumberPicker numberPicker2;


    @AfterViews
    void init(){

        pageScrollerCustomView.setMaxCount(10);


        numberPicker2.setMaxValue(repositoryBean.getCount());
        numberPicker2.setMinValue(1);
        numberPicker2.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal)
            {
                pageScrollerCustomView.setCurrPage(numberPicker2.getValue());
            }
        });
    }

    @Click
    void addElements() {
       pageScrollerCustomView.addElements(3);
    }

    @Click
    void deleteElements() {
        pageScrollerCustomView.deleteElements(4);
    }

    @Click
    void deleteElementsList() {
        List<Integer> numberList = Arrays.asList(2,5,7);
        pageScrollerCustomView.deleteElementsList(numberList);
    }

}
