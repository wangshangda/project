package study.tthy.com.study.ui.teacher;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import study.tthy.com.study.R;
import study.tthy.com.study.base.BaseActivity;
import study.tthy.com.study.base.BasePresenter;
import study.tthy.com.study.base.baseControl.AppContentContants;
import study.tthy.com.study.help.ImageLoaderHelper;
import study.tthy.com.study.ui.teacher.bean.TeacherResultBean;
import study.tthy.com.study.ui.teacher.fragment.TeacherDynamicFragment;
import study.tthy.com.study.ui.teacher.fragment.TeacherEvaluateFragment;
import study.tthy.com.study.ui.teacher.fragment.TeacherIntroduceFragment;
import study.tthy.com.study.ui.teacher.fragment.TeacherLiveFragment;
import study.tthy.com.study.ui.teacher.fragment.TeacherRouteFragment;

/**
 * Created by Administrator on 2019/3/28.
 */


public class TeacherDetailActivity extends BaseActivity {
    @BindView(R.id.iv_teacher_back)
    ImageView ivTeacherBack;
    @BindView(R.id.ed_search_content)
    EditText edSearchContent;
    @BindView(R.id.iv_search_close)
    ImageView ivSearchClose;
    @BindView(R.id.iv_green_search)
    ImageView ivGreenSearch;
    @BindView(R.id.ll_serarch)
    LinearLayout llSerarch;
    @BindView(R.id.ll_top_serarch)
    LinearLayout llTopSerarch;
    @BindView(R.id.iv_head)
    ImageView ivHead;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_follow)
    TextView tvFollow;
    @BindView(R.id.v_center)
    View vCenter;
    @BindView(R.id.tv_fans)
    TextView tvFans;
    @BindView(R.id.rv_top)
    RelativeLayout rvTop;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.ll_content)
    LinearLayout llContent;
    @BindView(R.id.cdl_teacher)
    RelativeLayout cdlTeacher;
    @BindView(R.id.tpi_tab)
    MagicIndicator tpiTab;
    @BindView(R.id.iv_teacher_user_sex)
    ImageView ivTeacherUserSex;
    @BindView(R.id.ratingbar)
    RatingBar ratingbar;
    @BindView(R.id.ll_tpi_tab)
    LinearLayout llTpiTab;
    @BindView(R.id.ll_teacher_follw)
    LinearLayout llTeacherFollw;
    @BindView(R.id.ll_teacher_private)
    LinearLayout llTeacherPrivate;
    @BindView(R.id.ll_teacher_comment)
    LinearLayout llTeacherComment;
    @BindView(R.id.tv_teacher_follw)
    TextView tvTeacherFollw;
    @BindView(R.id.ll_teacher_cancle_follw)
    TextView llTeacherCancleFollw;
    @BindView(R.id.ll_teacher_cancle)
    LinearLayout llTeacherCancle;
    @BindView(R.id.ll_teacher_share)
    LinearLayout llTeacherShare;
    private DetailPagerAdapter detailPagerAdapter;

    public List<Fragment> fs = new ArrayList<Fragment>();

    private static final String[] CONTENT = new String[]{"动态", "介绍", "路线", "直播", "评价"};

    private Fragment dynamicFragment;
    private Fragment introduceFragment;
    private Fragment routeFragment;
    private Fragment liveFragment;
    private Fragment evaluateFragment;

    private TeacherResultBean mTeacherResultBean;

    private Boolean showFollw = false;


    @Override
    protected void init(Bundle savedInstanceState) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_teacher_detail_layout;
    }

    @Override
    protected void initView() {
        setToolBarVisiable(false);
//        setBaseStatusBar(true);
        dynamicFragment = new TeacherDynamicFragment();
        introduceFragment = new TeacherIntroduceFragment();
        routeFragment = new TeacherRouteFragment();
        liveFragment = new TeacherLiveFragment();
        evaluateFragment = new TeacherEvaluateFragment();
        settpi();
        fs.add(dynamicFragment);
        fs.add(introduceFragment);
        fs.add(routeFragment);
        fs.add(liveFragment);
        fs.add(evaluateFragment);
        detailPagerAdapter = new DetailPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(detailPagerAdapter);
        viewPager.setOffscreenPageLimit(5);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewPager.setCurrentItem(0);
        ImageLoaderHelper.getInstance().loadHead(mContext, AppContentContants.TEST_HEAD_1, ivHead);
    }

    @Override
    protected void initData() {
        mTeacherResultBean = new TeacherResultBean();
        mTeacherResultBean.setAuthent(1);
        mTeacherResultBean.setIschecked(0);
        mTeacherResultBean.setFollw(0);
        if (mTeacherResultBean.getIsFollw() == 0) {
            tvTeacherFollw.setSelected(true);
            tvTeacherFollw.setText("关注");
        } else {
            tvTeacherFollw.setSelected(false);
            tvTeacherFollw.setText("已关注");
        }

    }

    //指示器
    private void settpi() {
        CommonNavigator commonNavigator = new CommonNavigator(this);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {

            @Override
            public int getCount() {
                return CONTENT == null ? 0 : CONTENT.length;
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                ColorTransitionPagerTitleView colorTransitionPagerTitleView = new ColorTransitionPagerTitleView(context);
                colorTransitionPagerTitleView.setNormalColor(getResources().getColor(R.color.color66));
                colorTransitionPagerTitleView.setSelectedColor(getResources().getColor(R.color.color06BD));
                colorTransitionPagerTitleView.setText(CONTENT[index]);
                colorTransitionPagerTitleView.setGravity(Gravity.CENTER);
                colorTransitionPagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        viewPager.setCurrentItem(index);
                    }
                });
                return colorTransitionPagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setColors(getResources().getColor(R.color.color06BD));
                indicator.setMode(LinePagerIndicator.MODE_WRAP_CONTENT);
                return indicator;
            }
        });
        commonNavigator.setAdjustMode(true);
        tpiTab.setNavigator(commonNavigator);
        ViewPagerHelper.bind(tpiTab, viewPager);
    }

    @Override
    protected void initListenerAddData() {
        //粉丝和关注
        tvFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, TeacherFansActivity.class));
            }
        });
        tvFans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, TeacherFansActivity.class));
            }
        });
        //评价
        llTeacherComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, TeacherEvaluateActivity.class));
            }
        });
        //搜索
        edSearchContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, TeacherDynamicSearchActivity.class));
            }
        });
        ivTeacherBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        ivSearchClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, TeacherDynamicSearchActivity.class));
            }
        });
        //关注
        llTeacherFollw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mTeacherResultBean.getIsFollw() == 0) {
                    //关注
                    tvTeacherFollw.setSelected(false);
                    llTeacherCancle.setVisibility(View.GONE);
                    mTeacherResultBean.setIsFollw(1);
                } else {
                    if (showFollw) {
                        llTeacherCancle.setVisibility(View.GONE);
                        showFollw = false;
                    } else {
                        showFollw = true;
                        llTeacherCancle.setVisibility(View.VISIBLE);
                    }
                }
            }
        });
        llTeacherCancleFollw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //取消关注
                llTeacherCancle.setVisibility(View.GONE);
                mTeacherResultBean.setIsFollw(0);
                tvTeacherFollw.setSelected(true);
            }
        });
        llTeacherShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext,TeacherQrShareActivity.class));
            }
        });

    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    public class DetailPagerAdapter extends FragmentPagerAdapter {

        public DetailPagerAdapter(FragmentManager fm) {
            super(fm);
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
