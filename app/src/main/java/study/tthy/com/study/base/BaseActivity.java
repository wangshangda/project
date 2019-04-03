package study.tthy.com.study.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gyf.barlibrary.ImmersionBar;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import study.tthy.com.study.R;
import study.tthy.com.study.ui.main.presenter.MainContract;
import study.tthy.com.study.util.HUDFactory;
import study.tthy.com.study.util.StringUtils;
import study.tthy.com.study.util.ToastUtils;

/**
 * @author wsd
 * @date 2019/1/25  11:37
 * @describe ${activity公共基类}
 */
public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements IBaseView {
    /**
     * 是否启用换肤的功能，为true时baseActivity会去注册SkinManager,不考虑换肤功能请设置成false
     */
    public static final boolean IS_SKIN = false;
    protected Activity mActivity;
    public Context mContext;
    ImageView ivToolbarBack;
    ImageView ivGrayFound;
    ImageView ivGreenFound;
    LinearLayout llSerarch;
    TextView tvToolbarTitle;
    TextView tvToolbarRight;
    ImageView ivSearchClose;
    EditText edSearch;
    @BindView(R.id.toolbar)
    LinearLayout toolbar;
    private Unbinder unBinder;
    public static final int REQUEST_REFRESH_KEY = 55555;
    protected P mPresenter;
    protected boolean regEvent;

    private Boolean mAllowFullScreen = false;
    private View rootView;

    /**
     * 是否沉浸状态栏
     **/
    private boolean isSetStatusBar = true;
    protected boolean isDestory = false;
    public KProgressHUD kProgressHUD;
    public ImmersionBar mImmersionBar;
    private LinearLayout parentLinearLayout;

    protected static final int NO_PIC = 0;
    protected static final int PIC_LEFT = 1;
    protected static final int PIC_RIGHT = 2;


    /**
     * view填充之前 过去Intent数据  绑定Presenter等
     * 注意:获取intent的数据需要在super之前,否则如果创建Presenter使用到这些数据的话,这些数据在使用时还未被赋值
     *
     * @param savedInstanceState
     */
    protected abstract void init(Bundle savedInstanceState);

    protected abstract int getLayoutId();

    protected abstract void initView();

    protected abstract void initData();

    protected abstract void initListenerAddData();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = this;
        mContext = this;
        if (IS_SKIN) {
            //这里初始化换肤功能
        }
        //加入activity管理
        BaseApplication.getApplication().getActivityControl().addActivity(this);
        initContentView(R.layout.activity_layout_base);
        baseInit();
        init(savedInstanceState);
        initView();
        initListenerAddData();
        initData();
        ivToolbarBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        if (regEvent) {
            EventBus.getDefault().register(this);
        }
        mPresenter = getPresenter();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
        if (mAllowFullScreen) {
            this.getWindow().setFlags(                  WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
            requestWindowFeature(Window.FEATURE_NO_TITLE);
        }
        if (isSetStatusBar) {
            steepStatusBar();
        }

    }

    private void steepStatusBar() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        Logger.d("当前的activity:" + getClass().getSimpleName());
    }

    public void showToast(String msg) {
        ToastUtils.showLong(this, msg);
    }


    protected abstract P getPresenter();

    @Override
    public Context getContext() {
        return this;
    }

    public void setAllowFullScreen(boolean allowFullScreen) {
        this.mAllowFullScreen = allowFullScreen;
    }

    public void setSteepStatusBar(boolean isSetStatusBar) {
        this.isSetStatusBar = isSetStatusBar;
    }

    /**
     * 初始化contentview
     *
     * @param layoutResID
     */
    private void initContentView(@LayoutRes int layoutResID) {
        ViewGroup viewGroup = (ViewGroup) findViewById(android.R.id.content);
        viewGroup.removeAllViews();
        parentLinearLayout = new LinearLayout(this);
        parentLinearLayout.setOrientation(LinearLayout.VERTICAL);
        //  add parentLinearLayout in viewGroup
        viewGroup.addView(parentLinearLayout);
        //  add the layout of BaseActivity in parentLinearLayout
        rootView = LayoutInflater.from(this).inflate(layoutResID, parentLinearLayout, true);
        tvToolbarTitle = rootView.findViewById(R.id.tv_toolbar_title);
        ivToolbarBack = rootView.findViewById(R.id.iv_toolbar_back);
        llSerarch = rootView.findViewById(R.id.ll_toor_serarch);
        tvToolbarRight = rootView.findViewById(R.id.tv_toolbar_right);
        ivSearchClose = rootView.findViewById(R.id.iv_toolbar_close);
        edSearch = rootView.findViewById(R.id.ed_toolbar_content);
        ivGrayFound = rootView.findViewById(R.id.iv_gray_found);
        ivGreenFound = rootView.findViewById(R.id.iv_green_search);

    }


    /**
     * 基础初始化 绑定黄油刀,  使用binding的复写 不绑
     */
    protected void baseInit() {
        LayoutInflater.from(this).inflate(getLayoutId(), parentLinearLayout, true);
        try {
            unBinder = ButterKnife.bind(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void setToolBarVisiable(Boolean visiable) {
        if (visiable) {
            toolbar.setVisibility(View.VISIBLE);
        } else {
            toolbar.setVisibility(View.GONE);
        }
    }

    protected void setBackBtnVisiable(Boolean visiable) {
        ivToolbarBack.setVisibility(visiable ? View.VISIBLE : View.GONE);
    }

    protected void setCancleBtn(Boolean visiable, View.OnClickListener clickListener) {
        tvToolbarRight.setVisibility(visiable ? View.VISIBLE : View.GONE);
        tvToolbarRight.setOnClickListener(clickListener);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_REFRESH_KEY && resultCode == Activity.RESULT_OK) {
            onRefresh();
        }
    }

    public void hideKeyBoard() {
        View view = getWindow().peekDecorView();
        if (view != null) {
            InputMethodManager inputmanger = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputmanger.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
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


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //2018.10.15加入返回隐藏键盘
        hideKeyBoard();
    }

    /**
     * 请求回来刷新时调用
     */
    public void onRefresh() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unBinder != null) {
            unBinder.unbind();
        }
        //移除类
        BaseApplication.getApplication().getActivityControl().removeActivity(this);
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        if (regEvent) {
            EventBus.getDefault().unregister(this);
        }
        isDestory = true;
        dismissHUD();
        // 必须调用该方法，防止内存泄漏
        ImmersionBar.with(this).destroy();
        overridePendingTransition(R.anim.up_in,R.anim.fade_out);

    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        ImmersionBar.with(this).init();
    }

    /**
     * 沉浸式状态栏
     */
    protected void setBaseStatusBar() {
        mImmersionBar = ImmersionBar.with(this);
        //所有子类都将继承这些相同的属性,请在设置界面之后设置,指定标题栏view
        mImmersionBar.fitsSystemWindows(true).statusBarColor(R.color.colorToolBar).init();
    }

    protected void setBaseStatusBar(boolean trans) {
        mImmersionBar = ImmersionBar.with(this);
        //所有子类都将继承这些相同的属性,请在设置界面之后设置,指定标题栏view
        ImmersionBar.with(this).transparentStatusBar().init();
    }
    public LinearLayout getToolbar() {
        return (LinearLayout) findViewById(R.id.toolbar);
    }

    /**
     * toolbar操作
     *
     * @param title
     */
    public void setToolBarTitle(CharSequence title, boolean show) {
        tvToolbarTitle.setVisibility(show ? View.VISIBLE : View.GONE);
        if (tvToolbarTitle != null) {
            tvToolbarTitle.setText(title);
        } else {
            tvToolbarTitle = findViewById(R.id.tv_toolbar_title);
        }
    }


    public interface OnSearchClickListener {
        void onSearch(String text);

        void onClearSearch();
    }


    public interface OnTextChangedListener {
        void onChanged(String text);
    }

    protected EditText getSearchEdit() {
        return edSearch;
    }

    /**
     * 显示搜索框
     *
     * @param onSearchListener 搜索监听
     * @date 2016年8月20日09:52:26
     * @author YangZhenshan
     */
    public void showFullSearchView(final OnSearchClickListener onSearchListener, int showBtn) {
        switch (showBtn) {
            case NO_PIC:
                break;
            case PIC_LEFT:
                ivGrayFound.setVisibility(View.VISIBLE);
                break;
            case PIC_RIGHT:
                ivGreenFound.setVisibility(View.VISIBLE);
                break;
            default:
                break;

        }

        if (llSerarch == null) {
            llSerarch = rootView.findViewById(R.id.ll_toor_serarch);
        }
        llSerarch.setVisibility(View.VISIBLE);
        final EditText editText = (EditText) findViewById(R.id.ed_toolbar_content);
        editText.clearFocus();
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH || actionId == EditorInfo.IME_ACTION_SEND || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                    String search = editText.getText().toString().trim();
                    if (onSearchListener != null) {
                        onSearchListener.onSearch(search);
                    }
                    return true;
                }
                return false;
            }
        });
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(s.toString())) {
                    onSearchListener.onClearSearch();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


    /**
     * 显示搜索框
     *
     * @param onTextChangedListener 搜索监听
     * @date 2016年8月20日09:52:26
     * @author
     */
    public void showFullSearchView(final OnTextChangedListener onTextChangedListener) {
        if (llSerarch == null) {
            llSerarch = rootView.findViewById(R.id.ll_toor_serarch);
        }
        llSerarch.setVisibility(View.VISIBLE);
        final EditText editText = findViewById(R.id.ed_toolbar_content);
        editText.clearFocus();
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (onTextChangedListener != null) {
                    onTextChangedListener.onChanged(s.toString().trim());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
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
        }
    }

    @Override
    public void showHUD(String msg) {
        if (isDestory) {
            return;
        }
        if (kProgressHUD == null) {
            kProgressHUD = HUDFactory.getInstance().creatHUD(this);
        }
        kProgressHUD.setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                // .setLabel(getString(R.string.loading))
                .setLabel(TextUtils.isEmpty(msg) ? getString(R.string.loading) : msg)
                // .setLabel(null)
                .setCancellable(true)
                .setAnimationSpeed(2)
                .setDimAmount(0.3f).show();
    }

    @Override
    public void dismissHUD() {
        if (null != kProgressHUD && kProgressHUD.isShowing()) {
            kProgressHUD.dismiss();
        }
    }

    /*----------------------空页面显示----------------------*/

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
