package study.tthy.com.study.ui.main;

import android.content.Context;
import android.os.Bundle;
import android.os.Process;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hjq.permissions.OnPermission;
import com.hjq.permissions.Permission;
import com.hjq.permissions.XXPermissions;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import study.tthy.com.study.R;
import study.tthy.com.study.base.BaseActivity;
import study.tthy.com.study.base.BaseApplication;
import study.tthy.com.study.ui.main.home.HomeFragment;
import study.tthy.com.study.ui.main.presenter.MainContract;
import study.tthy.com.study.ui.main.presenter.MainPresenter;
import study.tthy.com.study.util.ToastUtils;
import study.tthy.com.study.widget.NoScrollViewPager;

public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View, View.OnClickListener {
    @BindView(R.id.viewPager)
    NoScrollViewPager viewPager;
    @BindView(R.id.fl_content)
    LinearLayout flContent;
    @BindView(R.id.rb_home)
    TextView rbHome;
    @BindView(R.id.rb_route)
    TextView rbRoute;
    @BindView(R.id.rb_dynamic)
    TextView rbDynamic;
    @BindView(R.id.tv_tab_dynamic_num)
    TextView tvTabDynamicNum;
    @BindView(R.id.rl_dynamic)
    RelativeLayout rlDynamic;
    @BindView(R.id.rb_message)
    TextView rbMessage;
    @BindView(R.id.tv_tab_chat_num)
    TextView tvTabChatNum;
    @BindView(R.id.rl_message)
    RelativeLayout rlMessage;
    @BindView(R.id.rb_self)
    TextView rbSelf;
    @BindView(R.id.rg_tab_container1)
    LinearLayout rgTabContainer1;
    @BindView(R.id.layout_home_frag)
    LinearLayout layoutHomeFrag;
    private long exitTime = 0;
    private List<Fragment> mFragments;
    public Fragment mHomeFragment;
    public Fragment mRouteFragment;
    public Fragment mDynamicFrament;
    public Fragment mMessageFrament;
    public Fragment mSelfFrament;
    public FragmentTransaction transaction;
    private Context mainContext;
    private MainPagerAdapter pagerAdapter;
    //底部栏
    private List<TextView> rbList = new ArrayList<>();
    ;


    @Override
    protected void init(Bundle savedInstanceState) {
        mainContext = mContext;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        mFragments = new ArrayList<>();
        pagerAdapter = new MainPagerAdapter(getSupportFragmentManager());
        //radiobutton监听
        //选择主页tab
//        setBaseStatusBar();
        setToolBarVisiable(false);
        rbList.add(rbHome);
        rbList.add(rbRoute);
        rbList.add(rbDynamic);
        rbList.add(rbMessage);
        rbList.add(rbSelf);
        mHomeFragment = new HomeFragment();
        mRouteFragment = new HomeFragment();
        mDynamicFrament = new HomeFragment();
        mMessageFrament = new HomeFragment();
        mSelfFrament = new HomeFragment();

        pagerAdapter.fs.add(mHomeFragment);
        pagerAdapter.fs.add(mRouteFragment);
        pagerAdapter.fs.add(mDynamicFrament);
        pagerAdapter.fs.add(mMessageFrament);
        pagerAdapter.fs.add(mSelfFrament);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setOffscreenPageLimit(5);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                refreshFootTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewPager.setCurrentItem(0);
        refreshFootTab(0);
        //权限申请
        XXPermissions.with(this)
                .permission(Permission.Group.STORAGE)
                .request(new OnPermission() {

                    @Override
                    public void hasPermission(List<String> granted, boolean isAll) {

                    }

                    @Override
                    public void noPermission(List<String> denied, boolean quick) {

                    }
                });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListenerAddData() {
        rbHome.setOnClickListener(this);
        rbRoute.setOnClickListener(this);
        rbSelf.setOnClickListener(this);
        rlMessage.setOnClickListener(this);
        rlDynamic.setOnClickListener(this);
    }


    @Override
    protected MainPresenter getPresenter() {
        return new MainPresenter();
    }

    //底部菜单切换
    private void refreshFootTab(int position) {
        for (TextView tv :
                rbList) {
            if (tv.getId() == rbList.get(position).getId()) {
                tv.setSelected(true);
            } else {
                tv.setSelected(false);
            }
        }
    }


    /**
     * 退出应用
     */
    public void exitApp() {
        BaseApplication.getApplication().getActivityControl().finishiAll();
        //用于杀掉当前进程
        Process.killProcess(Process.myPid());
        //参数0和1代表退出的状态，0表示正常退出，1表示异常退出
        System.exit(0);
    }

    @Override
    public void onBackPressed() {
        //退出提示
        if (System.currentTimeMillis() - exitTime > 2000) {
            ToastUtils.showShort(this, "再按一次退出程序");
            exitTime = System.currentTimeMillis();
        } else {
            exitApp();
            super.onBackPressed();
        }
    }


    /**
     * 隐藏掉所有的Fragment
     */
    private void hideFragments(FragmentTransaction transaction) {
        for (Fragment fragment : mFragments) {
            if (fragment != null && !fragment.isHidden()) {
                transaction.hide(fragment);
            }
        }
    }


    @Override
    public void onClick(View v) {
        hideFragments(transaction);
        switch (v.getId()) {
            case R.id.rb_home:
                viewPager.setCurrentItem(0);
                refreshFootTab(0);
                break;
            case R.id.rb_route:
                viewPager.setCurrentItem(1);
                refreshFootTab(1);
                break;
            case R.id.rb_self:
                viewPager.setCurrentItem(4);
                refreshFootTab(4);
                break;
            case R.id.rl_dynamic:
                viewPager.setCurrentItem(2);
                refreshFootTab(2);
                break;
            case R.id.rl_message:
                viewPager.setCurrentItem(3);
                refreshFootTab(3);
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    public static class MainPagerAdapter extends FragmentPagerAdapter {
        public List<Fragment> fs;

        public MainPagerAdapter(FragmentManager fm) {
            super(fm);
            fs = new ArrayList<Fragment>();
        }

        @Override
        public Fragment getItem(int arg0) {
            return fs != null ? fs.get(arg0) : null;
        }

        @Override
        public int getCount() {
            return fs != null ? fs.size() : 0;
        }

    }


}
