package android.dmi.pmf.novica.myapplication.activity;

import android.dmi.pmf.novica.myapplication.R;
import android.dmi.pmf.novica.myapplication.view.PageScrollerView;
import android.support.v7.app.AppCompatActivity;
import android.widget.NumberPicker;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.Arrays;
import java.util.List;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    @ViewById
    public com.makaji.aleksej.pagescroller.PageScrollerView pageScrollerCustomView;

    @ViewById
    public NumberPicker numberPicker2;

    @AfterViews
    void init() {

       // pageScrollerCustomView.init();
        pageScrollerCustomView.setMaxCount(10);

        numberPicker2.setMaxValue(pageScrollerCustomView.getNumberOfPages());
        numberPicker2.setMinValue(1);
        numberPicker2.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                pageScrollerCustomView.setCurrPage(numberPicker2.getValue());
            }
        });
    }

    @Click
    void addElements() {
        pageScrollerCustomView.setMaxCount(0);
        numberPicker2.setMaxValue(pageScrollerCustomView.getNumberOfPages());
        /*pageScrollerCustomView.addElements(3);
        numberPicker2.setMaxValue(pageScrollerCustomView.getNumberOfPages());*/
    }

    @Click
    void deleteElements() {
        /*pageScrollerCustomView.deleteElements(4);
        numberPicker2.setMaxValue(pageScrollerCustomView.getNumberOfPages());*/
        pageScrollerCustomView.setMaxCount(8);
        numberPicker2.setMaxValue(pageScrollerCustomView.getNumberOfPages());
    }

    @Click
    void deleteElementsList() {
       /* List<Integer> numberList = Arrays.asList(3, 6);
        pageScrollerCustomView.deleteElementsList(numberList);
        numberPicker2.setMaxValue(pageScrollerCustomView.getNumberOfPages());*/
        pageScrollerCustomView.setMaxCount(11);
        numberPicker2.setMaxValue(pageScrollerCustomView.getNumberOfPages());
    }

}
