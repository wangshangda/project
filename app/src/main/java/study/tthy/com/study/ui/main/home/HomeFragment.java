package study.tthy.com.study.ui.main.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.luck.picture.lib.decoration.GridSpacingItemDecoration;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import study.tthy.com.study.R;
import study.tthy.com.study.base.baseControl.AppContentContants;
import study.tthy.com.study.base.fragment.BaseFragment;
import study.tthy.com.study.help.ImageLoaderHelper;
import study.tthy.com.study.ui.main.home.entity.HomeMenuBean;
import study.tthy.com.study.ui.main.home.entity.TeacherBean;
import study.tthy.com.study.ui.main.home.entity.TravelUserBean;
import study.tthy.com.study.ui.teacher.TeacherSearchActivity;
import study.tthy.com.study.util.ActivityUtils;
import study.tthy.com.study.util.CommonUtils;
import study.tthy.com.study.util.ObjectUtils;
import study.tthy.com.study.util.ToastUtils;
import study.tthy.com.study.widget.BannerIndicatorView;

/**
 * @author tome
 * @date 2019/1/25  10:43
 * @describe ${TODO}
 */
public class HomeFragment extends BaseFragment<HomePresenter> implements HomeContract.View {
    Unbinder unbinder;
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.ed_search_content)
    EditText edSearchContent;
    @BindView(R.id.iv_green_search)
    ImageView ivGreenSearch;
    @BindView(R.id.ll_serarch)
    LinearLayout llSerarch;
    @BindView(R.id.iv_home_threedot)
    ImageView ivHomeThreedot;
    //菜单recyclerview
    @BindView(R.id.rv_home_menu)
    RecyclerView rvHomeMenu;
    @BindView(R.id.rv_home_card)
    RecyclerView rvHomeCard;
    @BindView(R.id.indicator_view)
    BannerIndicatorView indicatorView;
    @BindView(R.id.tv_live)
    TextView tvLive;
    @BindView(R.id.tv_live_more)
    TextView tvLiveMore;
    @BindView(R.id.iv_living_one)
    ImageView ivLivingOne;
    @BindView(R.id.iv_living_one_head)
    ImageView ivLivingOneHead;
    @BindView(R.id.tv_living_one_title)
    TextView tvLivingOneTitle;
    @BindView(R.id.iv_living_one_state)
    ImageView ivLivingOneState;
    @BindView(R.id.tv_living_one_hot)
    TextView tvLivingOneHot;
    @BindView(R.id.tv_living_one_date)
    TextView tvLivingOneDate;
    @BindView(R.id.iv_living_two)
    ImageView ivLivingTwo;
    @BindView(R.id.iv_living_two_head)
    ImageView ivLivingTwoHead;
    @BindView(R.id.tv_living_two_title)
    TextView tvLivingTwoTitle;
    @BindView(R.id.iv_living_two_state)
    ImageView ivLivingTwoState;
    @BindView(R.id.tv_living_two_hot)
    TextView tvLivingTwoHot;
    @BindView(R.id.tv_living_two_date)
    TextView tvLivingTwoDate;
    @BindView(R.id.rl_living)
    RelativeLayout rlLiving;
    @BindView(R.id.tv_interactive_more)
    TextView tvInteractiveMore;
    @BindView(R.id.interactive_view)
    BannerIndicatorView interactiveView;
    @BindView(R.id.rl_interactive)
    RelativeLayout rlInteractive;
    @BindView(R.id.tv_teacher_more)
    TextView tvTeacherMore;
    @BindView(R.id.ry_teacher)
    RecyclerView ryTeacher;
    @BindView(R.id.tv_teacher)
    TextView tvTeacher;
    @BindView(R.id.tv_line)
    TextView tvLine;
    @BindView(R.id.tv_line_more)
    TextView tvLineMore;
    @BindView(R.id.iv_line_one)
    ImageView ivLineOne;
    @BindView(R.id.iv_line_one_head)
    ImageView ivLineOneHead;
    @BindView(R.id.tv_line_one_title)
    TextView tvLineOneTitle;
    @BindView(R.id.iv_line_two)
    ImageView ivLineTwo;
    @BindView(R.id.iv_line_two_head)
    ImageView ivLineTwoHead;
    @BindView(R.id.tv_line_two_title)
    TextView tvLineTwoTitle;
    @BindView(R.id.rl_line)
    RelativeLayout rlLine;
    @BindView(R.id.tv_travel)
    TextView tvTravel;
    @BindView(R.id.tv_travel_more)
    TextView tvTravelMore;
    @BindView(R.id.rv_travel)
    RecyclerView rvTravel;
    //轮播图list
    private List<String> images;
    //菜单列表
    private List<HomeMenuBean> homeMenuBeanList = new ArrayList<HomeMenuBean>();
    private QuickAdapter menuAdapter;

    private List<HomeMenuBean> homeCardBeanList = new ArrayList<HomeMenuBean>();
    private CardAdapter cardAdapter;

    //导师
    private List<TeacherBean> teacherBeanList = new ArrayList<TeacherBean>();
    private TeacherAdapter teacherAdapter;
    //游记
    private List<TravelUserBean> travelUserBeanList = new ArrayList<TravelUserBean>();
    private TravelAdapter travelAdapter;

    //popupwindo
    private PopupWindow mPopupWindow;

    @Override
    protected HomePresenter getPresenter() {
        return new HomePresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.study_fragment_home;
    }

    @Override
    protected void initView(View rootView) {
        lodaData();
        banner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                ImageLoaderHelper.getInstance().load(context, String.valueOf(path), imageView);
            }
        });
        banner.setBannerStyle(BannerConfig.NOT_INDICATOR);//设置圆形指示器与标题
        banner.setIndicatorGravity(BannerConfig.CENTER);//设置指示器位置
        banner.setDelayTime(2000);//设置轮播时间
        banner.setImages(images);//设置图片源
        banner.start();
        banner.startAutoPlay();
        //发布动态
        View contentView = LayoutInflater.from(mContext).inflate(
                R.layout.home_dialog_release, null);
        mPopupWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);

        mPopupWindow.setTouchable(true);

        mPopupWindow.setTouchInterceptor(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                Log.i("mengdd", "onTouch : ");

                return false;
                // 这里如果返回true的话，touch事件将被拦截
                // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
            }
        });

        // 如果不设置PopupWindow的背景，无论是点击外部区域还是Back键都无法dismiss弹框
        // 我觉得这里是API的一个bug
//        mPopupWindow.setBackgroundDrawable(getResources().getDrawable(
//                R.drawable.selectmenu_bg_downward));

    }

    private void lodaData() {
        images = new ArrayList<String>();
        images.add("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1586697019,2893742167&fm=26&gp=0.jpg");
        images.add("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3350702307,1620266272&fm=26&gp=0.jpg");
        images.add("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3987297451,840043590&fm=26&gp=0.jpg");
        images.add("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=3866946258,3274036718&fm=26&gp=0.jpg");
    }


    @Override
    protected void initListenerAddData() {
        ivHomeThreedot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPopupWindow.isShowing()) {
                    mPopupWindow.dismiss();
                } else {
                    mPopupWindow.showAsDropDown(v, 0, 0);
                }
            }
        });
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                ToastUtils.showLong(getActivity(), position + "");
            }
        });
        banner.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                indicatorView.setCurrentPosition(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected void initData() {
        testData();
        menuAdapter = new QuickAdapter();
        rvHomeMenu.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        rvHomeMenu.addItemDecoration(new GridSpacingItemDecoration(4, 35, false) {
        });
        rvHomeMenu.setAdapter(menuAdapter);
        cardAdapter = new CardAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvHomeCard.setLayoutManager(linearLayoutManager);
        rvHomeCard.setAdapter(cardAdapter);
        teacherAdapter = new TeacherAdapter();
        ryTeacher.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        ryTeacher.setAdapter(teacherAdapter);
        travelAdapter = new TravelAdapter();
        rvTravel.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        rvTravel.setAdapter(travelAdapter);
    }

    private void testData() {
        homeMenuBeanList.add(new HomeMenuBean("导师", R.mipmap.education + "", AppContentContants.MENU_TEACHER));
        homeMenuBeanList.add(new HomeMenuBean("直播", R.mipmap.focus + "", AppContentContants.MENU_FOCUS));
        homeMenuBeanList.add(new HomeMenuBean("游记", R.mipmap.pilot + "", AppContentContants.MENU_PILOT));
        homeMenuBeanList.add(new HomeMenuBean("话题", R.mipmap.topic + "", AppContentContants.MENU_TOPIC));
        homeMenuBeanList.add(new HomeMenuBean("足迹打卡", R.mipmap.footprint + "", AppContentContants.MENU_FOOTPRINT));
        homeMenuBeanList.add(new HomeMenuBean("研学主题", R.mipmap.exam + "", AppContentContants.MENU_EXAM));
        homeMenuBeanList.add(new HomeMenuBean("互动打call", R.mipmap.flower + "", AppContentContants.MENU_INTERACTIVE));

        homeCardBeanList.add(new HomeMenuBean("推荐", R.color.color1B + "", AppContentContants.MENU_TEACHER));
        homeCardBeanList.add(new HomeMenuBean("跨年去哪里", R.color.colorF339 + "", AppContentContants.MENU_TEACHER));
        homeCardBeanList.add(new HomeMenuBean("去森林", R.color.colorFFDC + "", AppContentContants.MENU_TEACHER));
        homeCardBeanList.add(new HomeMenuBean("去草地", R.color.color37A9 + "", AppContentContants.MENU_TEACHER));

        teacherBeanList.add(new TeacherBean("http://img2.imgtn.bdimg.com/it/u=3846895839,2711067435&fm=26&gp=0.jpg", "周老师", 3.5f, 1, "好评"));
        teacherBeanList.add(new TeacherBean("http://img2.imgtn.bdimg.com/it/u=3846895839,2711067435&fm=26&gp=0.jpg", "李老师", 4f, 1, "好评"));
        teacherBeanList.add(new TeacherBean("http://img2.imgtn.bdimg.com/it/u=3846895839,2711067435&fm=26&gp=0.jpg", "王老师", 0.5f, 0, "差评"));

        travelUserBeanList.add(new TravelUserBean("", "shiningshell", "2019-5-21", "英国游记 / 十天九夜五城", 12222, 2222));
        travelUserBeanList.add(new TravelUserBean("", "shiningshell", "2019-5-21", "一半烟雨一半晴，带上从未看过大海的你，感受春暖花开", 12222, 2222));
        List<String> mImgList = new ArrayList<String>();
        mImgList.add("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1586697019,2893742167&fm=26&gp=0.jpg");
        mImgList.add("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3350702307,1620266272&fm=26&gp=0.jpg");
        mImgList.add("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=3866946258,3274036718&fm=26&gp=0.jpg");
        travelUserBeanList.get(0).setImgUrl(mImgList);
        rvTravel.setAdapter(travelAdapter);
        rvTravel.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    //菜单
    public class QuickAdapter extends BaseQuickAdapter<HomeMenuBean, BaseViewHolder> {
        public QuickAdapter() {
            super(R.layout.home_menu_recycle_item, homeMenuBeanList);
        }

        @Override
        protected void convert(BaseViewHolder viewHolder, HomeMenuBean item) {
            viewHolder.setText(R.id.tv_menu_text, item.getTitle());
            ImageView imageView = viewHolder.itemView.findViewById(R.id.iv_menu_icon);
            viewHolder.setOnClickListener(R.id.iv_menu_icon, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickItem(item);
                }
            });
            ImageLoaderHelper.getInstance().load(getActivity(), Integer.parseInt(item.getImgUrl()), imageView);
        }
    }

    private void clickItem(HomeMenuBean item) {
        switch (item.getMenuID()) {
            case AppContentContants.MENU_TEACHER:
                startActivity(new Intent(mContext,TeacherSearchActivity.class));
                break;
            case AppContentContants.MENU_FOCUS:
                break;
            case AppContentContants.MENU_PILOT:
                break;
            case AppContentContants.MENU_TOPIC:
                break;
            case AppContentContants.MENU_FOOTPRINT:
                break;
            case AppContentContants.MENU_EXAM:
                break;
            case AppContentContants.MENU_INTERACTIVE:
                break;
            default:
                break;
        }
    }

    //推荐
    public class CardAdapter extends BaseQuickAdapter<HomeMenuBean, BaseViewHolder> {
        public CardAdapter() {
            super(R.layout.home_card_recycle_item, homeCardBeanList);
        }

        @Override
        protected void convert(BaseViewHolder viewHolder, HomeMenuBean item) {
            viewHolder.setText(R.id.iv_card_icon, item.getTitle());
            ImageView imageView = viewHolder.itemView.findViewById(R.id.iv_menu_icon);
            ImageLoaderHelper.getInstance().load(getActivity(), Integer.parseInt(item.getImgUrl()), imageView);
        }
    }

    //导师
    public class TeacherAdapter extends BaseQuickAdapter<TeacherBean, BaseViewHolder> {
        public TeacherAdapter() {
            super(R.layout.home_teacher_item, teacherBeanList);
        }

        @Override
        protected void convert(BaseViewHolder viewHolder, TeacherBean item) {
            int pos = teacherBeanList.indexOf(item);
            viewHolder.setText(R.id.tv_name, item.getName());
            viewHolder.setText(R.id.tv_evaluation, item.getEvaluation());
            if (item.getIsChecked() == 1) {
                viewHolder.setChecked(R.id.btn_foucs, true);
            } else {
                viewHolder.setChecked(R.id.btn_foucs, false);
            }
            ImageView imageView = viewHolder.itemView.findViewById(R.id.iv_user_head);
            viewHolder.setRating(R.id.ratingbar, item.getRating());
            ImageLoaderHelper.getInstance().load(getActivity(), item.getHead(), imageView);
            viewHolder.setOnCheckedChangeListener(R.id.btn_foucs, new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        item.setIsChecked(0);
                    } else {
                        item.setIsChecked(1);
                    }
                }
            });
        }
    }

    //游记
    public class TravelAdapter extends BaseQuickAdapter<TravelUserBean, BaseViewHolder> {

        public TravelAdapter() {
            super(R.layout.home_travel_item, travelUserBeanList);
        }

        @Override
        protected void convert(BaseViewHolder viewHolder, TravelUserBean item) {
            viewHolder.setText(R.id.tv_travel_name, item.getName());
            viewHolder.setText(R.id.tv_travel_date, item.getDate());
            viewHolder.setText(R.id.tv_travel_content, item.getEvaluation());
            viewHolder.setText(R.id.tv_travel_watch, CommonUtils.getShowNumbers(item.getWatchNum()));
            viewHolder.setText(R.id.tv_travel_evaluation, CommonUtils.getShowNumbers(item.getEvaluationNum()));
            List<String> mlist = item.getImgUrl();
            if (!ObjectUtils.isEmpty(mlist)) {
                if (mlist.size() > 0) {
                    viewHolder.setVisible(R.id.iv_travel_pic1, true);
                    ImageLoaderHelper.getInstance().load(mContext, mlist.get(0), (ImageView) viewHolder.getView(R.id.iv_travel_pic1));
                } else {
                    viewHolder.setGone(R.id.ll_travel_pic, false);
                }
                if (mlist.size() > 1) {
                    viewHolder.setVisible(R.id.iv_travel_pic2, true);
                    ImageLoaderHelper.getInstance().load(mContext, mlist.get(1), (ImageView) viewHolder.getView(R.id.iv_travel_pic2));
                } else {
                    viewHolder.setVisible(R.id.iv_travel_pic2, false);
                }
                if (mlist.size() > 2) {
                    viewHolder.setVisible(R.id.iv_travel_pic3, true);
                    ImageLoaderHelper.getInstance().load(mContext, mlist.get(2), (ImageView) viewHolder.getView(R.id.iv_travel_pic3));
                } else {
                    viewHolder.setVisible(R.id.iv_travel_pic3, false);
                }


            } else {
                viewHolder.setGone(R.id.ll_travel_pic, false);
            }
        }

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        banner.stopAutoPlay();
        unbinder.unbind();
    }
}
