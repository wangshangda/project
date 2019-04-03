package study.tthy.com.study.base;

import android.content.Context;

public interface IBaseView {

    Context getContext();

    /**
     * 网络请求错误,弹框提示
     * @param msg
     * @param code
     */
    void showRequestError(String msg,String code);

    /**
     * 显示Dialog
     */
    void showHUD(String msg);

    /**
     * 关闭Dialog
     */
    void dismissHUD();

    //----------------------------下面用来显示空界面---------------------------//
    /**
     * showNormal 页面
     */
    void showNormal();

    /**
     * Show loading 页面
     */
    void showLoading();

    void dismissLoading();
    /**
     * Show EmptyView 页面
     */
    void showEmptyView();
    /**
     * Show error 页面
     */
    void showError();
}