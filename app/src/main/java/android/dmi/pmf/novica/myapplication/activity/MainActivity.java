package android.dmi.pmf.novica.myapplication.activity;

import android.dmi.pmf.novica.myapplication.R;

import android.support.v7.app.AppCompatActivity;
import android.widget.NumberPicker;

import com.makaji.aleksej.pagescroller.PageScrollerView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.Arrays;
import java.util.List;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    @ViewById
    public PageScrollerView pageScrollerView;

    @ViewById
    public NumberPicker numberPicker2;

    @AfterViews
    void init() {

        // pageScrollerCustomView.init();
        pageScrollerView.setMaxCount(12);

        numberPicker2.setMaxValue(12);
        numberPicker2.setMinValue(1);
        numberPicker2.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                pageScrollerView.setCurrPage(numberPicker2.getValue());
            }
        });

    }

    @Click
    void setMax1() {
        pageScrollerView.setMaxCount(1);
        numberPicker2.setMaxValue(1);
    }

    @Click
    void setMax2() {
        pageScrollerView.setMaxCount(2);
        numberPicker2.setMaxValue(2);
    }

    @Click
    void setMax3() {
        pageScrollerView.setMaxCount(3);
        numberPicker2.setMaxValue(3);
    }

    @Click
    void setMax123() {
        pageScrollerView.setMaxCount(123);
        numberPicker2.setMaxValue(123);
    }
}
