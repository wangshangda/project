package study.tthy.com.study.ui.main.home;


import study.tthy.com.study.base.IBaseView;
import study.tthy.com.study.base.IPresenter;

/**
 * @Created by TOME .
 * @时间 2018/5/4 11:15
 * @描述 ${TODO}
 */

public interface HomeContract {

    interface View extends IBaseView {

    }

    interface Presenter extends IPresenter<View> {
        //发布动态
        void relaseDynamic();
        //发布游记
        void relase_route();
        //招募导师
        void recruiting();

    }
}
