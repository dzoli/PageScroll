package android.dmi.pmf.novica.myapplication;

import android.support.v4.content.ContextCompat;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

//import com.squareup.otto.Subscribe;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

//import ch.ringler.drtaxpocket.R;
//import ch.ringler.drtaxpocket.eventbus.event.ImageLocalStorageChangedEvent;
//import ch.ringler.drtaxpocket.file.ImageFileDao;
//import ch.ringler.drtaxpocket.utility.ViewUtils;
//import ch.ringler.drtaxpocket.view.PageScrollView;

/**
 * Created by Aleksandar Kahriman on 8/24/17.
 */

//@EFragment(R.layout.fragment_page_counter)
public class PageCounterFragment {
//        extends BaseFragment

//    @Bean
//    ImageFileDao imageFileDao;
//
//    @ViewById
//    PageScrollView pageNumberScroller;
//
//    @ViewById
//    TextView pageLabel;
//
//    @AfterViews
//    @UiThread
//    public void setup() {
//
//        final int size = imageFileDao.getImages().size();
//
//        pageLabel.setText(String.format("/ %d", size));
//
//        final LinearLayout linearContainerPageNo = new LinearLayout(getActivity());
//        linearContainerPageNo.setLayoutParams(new ViewGroup.LayoutParams(
//                ViewGroup.LayoutParams.MATCH_PARENT,
//                ViewGroup.LayoutParams.MATCH_PARENT));
//        linearContainerPageNo.setOrientation(LinearLayout.VERTICAL);
//
//        for (int i = 0; i <= imageFileDao.getImages().size() + 1; i++) {
//            final TextView number = new TextView(getActivity());
//            number.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
//
//            number.setTextColor(ContextCompat.getColor(getActivity(), R.color.pageLabelGrey));
//            if (i != 0 && i != imageFileDao.getImages().size() + 1) {
//                number.setText(String.valueOf(i));
//            }
//
//            final RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams
//                    (RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
//            layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
//
//            number.setLayoutParams(layoutParams);
//            linearContainerPageNo.addView(number);
//        }
//
//        pageNumberScroller.removeAllViews();
//        pageNumberScroller.addView(linearContainerPageNo);
//        pageNumberScroller.setEnabled(false);
//        pageNumberScroller.setClickable(false);
//
//        waitForPageScroller();
//    }
//
//    @UiThread(delay = 100)
//    void waitForPageScroller() {
//        pageNumberScroller.scrollTo(0, ViewUtils.dpToPx(4, getActivity()));
//    }
//
//    public void pageChanged(int currentPageFocused) {
//        int pageNumberHeight = ((LinearLayout) pageNumberScroller.getChildAt(0)).getChildAt(0).getHeight();
//        int offset = ViewUtils.dpToPx(4, getActivity());
//        pageNumberScroller.smoothScrollTo(0, pageNumberHeight * currentPageFocused + offset);
//    }
//
//    @Subscribe
//    @UiThread
//    public void imageLocalStorageChanged(ImageLocalStorageChangedEvent imageLocalStorageChangedEvent) {
//        setup();
//    }
}
