package study.tthy.com.study.widget.popup;

import android.app.Activity;
import android.view.View;

/**
 * Created by Administrator on 2019/4/2.
 */

public class SharePopup extends BasePopupWindow {

    public SharePopup(Activity context) {
        super(context);
    }

    @Override
    public View getPopupView() {
        return null;
    }

    @Override
    protected View getClickToDismissView() {
        return null;
    }
}
