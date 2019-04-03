package study.tthy.com.study.base;

import android.content.Context;

import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import study.tthy.com.study.base.baseControl.IDisposablePool;

public abstract class BasePresenter<V extends IBaseView> implements IDisposablePool {

    /**
     * 管理事件流订阅的生命周期CompositeDisposable
     */
    private CompositeDisposable compositeDisposable;

    private V mProxyView;
    private WeakReference<V> weakReference;

    /**
     * 绑定View
     */
    @SuppressWarnings("unchecked")
    public void attachView(V view) {
        weakReference = new WeakReference<>(view);
        mProxyView = (V) Proxy.newProxyInstance(
                view.getClass().getClassLoader(),
                view.getClass().getInterfaces(),
                new MvpViewHandler(weakReference.get()));
    }

    /**
     * 解绑View
     */
    public void detachView() {
        if (isViewAttached()) {
            weakReference.clear();
            weakReference = null;
        }
        clearPool();
    }

    /**
     * 是否与View建立连接
     */
    protected boolean isViewAttached() {
        return weakReference != null && weakReference.get() != null;
    }

    protected V getView() {
        return mProxyView;
    }

    @Override
    public void addDisposable(Disposable disposable) {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(disposable);
    }


    /**
     * 取消订阅关系
     */
    @Override
    public void clearPool() {
        if (compositeDisposable != null) {
            compositeDisposable.clear();
            compositeDisposable = null;
        }
    }


    public Context getContext() {
        return mProxyView.getContext();
    }

    protected void showLoading() {
        getView().showLoading();
    }

    protected void dismissLoading() {
        getView().dismissLoading();
    }


    /**
     * 通过该方法创建Module
     */

    /**
     * 初始化方法
     */


    /**
     * View代理类  防止 页面关闭P异步操作调用V 方法 空指针问题
     */
    private class MvpViewHandler implements InvocationHandler {

        private IBaseView mvpView;

        MvpViewHandler(IBaseView mvpView) {
            this.mvpView = mvpView;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            //如果V层没被销毁, 执行V层的方法.
            if (isViewAttached()) {
                return method.invoke(mvpView, args);
            } //P层不需要关注V层的返回值
            return null;
        }
    }
}