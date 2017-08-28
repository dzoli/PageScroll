package android.dmi.pmf.novica.myapplication.view;

import android.content.Context;
import android.content.Intent;
import android.dmi.pmf.novica.myapplication.R;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

/**
 * Created by Novica on 8/28/2017.
 */

@EViewGroup(R.layout.item_view_number)
public class NumberItemView extends LinearLayout {

    @ViewById
    public TextView numberTv;

    public NumberItemView(Context context) {
        super(context);
    }

    public void bind(Integer num){
        numberTv.setText(num.toString());
    }
}
