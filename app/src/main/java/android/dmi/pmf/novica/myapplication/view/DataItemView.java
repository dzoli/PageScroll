package android.dmi.pmf.novica.myapplication.view;

import android.content.Context;
import android.dmi.pmf.novica.myapplication.R;
import android.dmi.pmf.novica.myapplication.model.DataItem;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

/**
 * Created by Novica on 8/28/2017.
 */


@EViewGroup(R.layout.item_view_data)
public class DataItemView  extends RelativeLayout {

    @ViewById
    TextView name;

    public DataItemView(Context context) {
        super(context);
    }

    public void bind(DataItem dataItem){
        name.setText(dataItem.getName());
    }
}
