package study.tthy.com.study.base.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gyf.barlibrary.ImmersionBar;
import com.orhanobut.logger.Logger;
import com.squareup.leakcanary.RefWatcher;

import org.greenrobot.eventbus.EventBus;

import java.lang.ref.WeakReference;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import study.tthy.com.study.R;
import study.tthy.com.study.base.BaseActivity;
import study.tthy.com.study.base.BaseApplication;
import study.tthy.com.study.base.IBaseView;
import study.tthy.com.study.base.IPresenter;
import study.tthy.com.study.util.ToastUtils;

/**
 * @author tome
 * @date 2019/1/25  15:17
 * @describe ${mvp的基础 fragment}
 */
public abstract class BaseFragment<P extends IPresenter> extends Fragment implements IBaseView {

    public Activity mActivity;
    public Context mContext;
    private Unbinder unBinder;
    protected boolean regEvent;
    private BaseActivity mBaseActivity;
    public static final int REQUEST_REFRESH_KEY = 44444;
    protected WeakReference<View> mRootView;
    private boolean inited = false;
    public ImmersionBar mImmersionBar;
    protected P mPresenter;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_REFRESH_KEY && resultCode == Activity.RESULT_OK) {
            onRefresh();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();
        mContext = getContext();
        init(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View mFragmentView = null;
        if (mRootView == null || mRootView.get() == null) {
            inited = false;
            mFragmentView = inflater.inflate(getLayoutId(), container, false);
            //弱引用对象
            mRootView = new WeakReference<>(mFragmentView);
        } else {
            ViewGroup parent = (ViewGroup) mRootView.get().getParent();
            if (parent != null) {
                parent.removeView(mRootView.get());
            }
            mFragmentView = mRootView.get();
        }
        unBinder = ButterKnife.bind(this, mFragmentView);
        return mRootView.get();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (!inited) {
            initView(mRootView.get());
            initListenerAddData();
            initData();
            inited = true;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Logger.d("当前的fragment:" + getClass().getSimpleName());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (unBinder != null) {
            unBinder.unbind();
            unBinder = null;
        }
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        if (regEvent) {
            EventBus.getDefault().unregister(this);
        }
        dismissHUD();

        //leakCanary 监控
        RefWatcher refWatcher = BaseApplication.getRefWatcher(mActivity);
        refWatcher.watch(this);


    }


    protected void setEmptyView(BaseQuickAdapter baseQuickAdapter, RecyclerView recyclerView, int res, String content) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recycle_empty_view, null);
        TextView textView = view.findViewById(R.id.tv_empty_content);
        ImageView imageView = view.findViewById(R.id.iv_empty_pic);
        if (res != 0) {
            imageView.setImageResource(res);
        }
        if (!TextUtils.isEmpty(content)) {
            textView.setText(content);
        }
        baseQuickAdapter.bindToRecyclerView(recyclerView);
        baseQuickAdapter.setEmptyView(view);

    }

    protected void init(Bundle savedInstanceState) {
        //沉浸式状态栏
//        setBaseStatusBar();
        if (regEvent) {
            EventBus.getDefault().register(this);
        }
        mPresenter = getPresenter();
        if (mPresenter != null) {
            //在这个时候才attach view是因为这个时候view的初始化已经基本完成,在Presenter中调用view的域也不会为空
            mPresenter.attachView(this);
        }
    }

    protected void setBaseStatusBar() {
        if (mImmersionBar == null) {
            mImmersionBar = ImmersionBar.with(this);
            //让子类单独使用
            // mImmersionBar.init();
        }
    }

    protected abstract P getPresenter();

    protected abstract int getLayoutId();

    /**
     * mFragmentView创建完成后,初始化具体的view 只会调用一次
     */
    protected abstract void initView(View rootView);

    protected abstract void initListenerAddData();

    protected abstract void initData();

    /**
     * 请求回来刷新时调用
     */
    public void onRefresh() {

    }

    @Nullable
    @Override
    public Context getContext() {
        return super.getContext();
    }

    /**
     * 提示网络请求错误信息
     *
     * @param msg
     * @param code
     */
    @Override
    public void showRequestError(String msg, String code) {
        String mCode = "-1";
        if (mCode.equals(code)) {
            ToastUtils.showShort(mActivity, msg);
        } else {
            ToastUtils.showShort(mActivity, msg);
        }
    }

    @Override
    public void showHUD(String msg) {
        if (getActivity() != null) {
            if (mBaseActivity == null) {
                mBaseActivity = (BaseActivity) getActivity();
            }
            mBaseActivity.showHUD(msg);
        }
    }

    @Override
    public void dismissHUD() {
        if (getActivity() != null && mBaseActivity != null) {
            mBaseActivity.dismissHUD();
        }
    }


    @Override
    public void showNormal() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }

    @Override
    public void showEmptyView() {

    }

    @Override
    public void showError() {

    }


}
