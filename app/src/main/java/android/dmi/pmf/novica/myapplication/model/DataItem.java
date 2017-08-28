package android.dmi.pmf.novica.myapplication.model;

/**
 * Created by Novica on 8/28/2017.
 */

public class DataItem {

    private String name;

    public DataItem(String name) {
        this.name = name;
    }

    public DataItem() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
