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
        pageScrollerView.setMaxCount(120);

        numberPicker2.setMaxValue(pageScrollerView.getNumberOfPages());
        numberPicker2.setMinValue(1);
        numberPicker2.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                pageScrollerView.setCurrPage(numberPicker2.getValue());
            }
        });

    }

    @Click
    void addElements() {
        pageScrollerView.setMaxCount(2);
        numberPicker2.setMaxValue(pageScrollerView.getNumberOfPages());
        /*pageScrollerCustomView.addElements(3);
        numberPicker2.setMaxValue(pageScrollerCustomView.getNumberOfPages());*/
       // pageScrollerView.setmHeightOfElementsAndTextSize(60);

       /* Integer testInt = 100;
        float pixelSize;

        Log.d("H", "Height INT:" + testInt);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        pixelSize = (int)testInt * dm.scaledDensity;

        Log.d("H", "pixelSize:" + pixelSize);*/
    }

    @Click
    void deleteElements() {
        /*pageScrollerCustomView.deleteElements(4);
        numberPicker2.setMaxValue(pageScrollerCustomView.getNumberOfPages());*/
        pageScrollerView.setMaxCount(8);
        numberPicker2.setMaxValue(pageScrollerView.getNumberOfPages());
        //pageScrollerView.setmHeightOfElementsAndTextSize(40);
    }

    @Click
    void deleteElementsList() {
       /* List<Integer> numberList = Arrays.asList(3, 6);
        pageScrollerCustomView.deleteElementsList(numberList);
        numberPicker2.setMaxValue(pageScrollerCustomView.getNumberOfPages());*/
        pageScrollerView.setMaxCount(323);
        numberPicker2.setMaxValue(pageScrollerView.getNumberOfPages());
        //pageScrollerView.setmHeightOfElementsAndTextSize(20);
    }

}
