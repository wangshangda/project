package study.tthy.com.study.base;

import android.content.Context;

/**
 * Created by tome.
 * Description : 顶级Presenter接口
 */

public interface IPresenter<V extends IBaseView> {

    /**
     * 创建view时调用  调用在initView 之后
     * @param view
     */
    void attachView(V view);

    void detachView();

    Context getContext();

}
