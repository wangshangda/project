package study.tthy.com.study.ui.teacher;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import study.tthy.com.study.R;
import study.tthy.com.study.base.BaseActivity;
import study.tthy.com.study.base.BasePresenter;
import study.tthy.com.study.help.ImageLoaderHelper;
import study.tthy.com.study.ui.teacher.bean.TeacherCategoryBean;
import study.tthy.com.study.ui.teacher.bean.TeacherResultBean;
import study.tthy.com.study.widget.BannerIndicatorView;

/**
 * Created by Administrator on 2019/3/26.
 */

public class TeacherSearchActivity extends BaseActivity {

    //内容reclyeview
    RecyclerView categoryRv;
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.indicator_view)
    BannerIndicatorView indicatorView;
    @BindView(R.id.ctl_teachersearch)
    CollapsingToolbarLayout ctlTeachersearch;
    @BindView(R.id.tv_teacher_all)
    TextView tvTeacherAll;
    @BindView(R.id.ll_all)
    LinearLayout llAll;
    @BindView(R.id.tv_teacher_evaluation)
    TextView tvTeacherEvaluation;
    @BindView(R.id.ll_teacher_evaluation)
    LinearLayout llTeacherEvaluation;
    @BindView(R.id.ll_sp)
    LinearLayout llSp;
    @BindView(R.id.avl)
    AppBarLayout avl;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.sv_teacher_search)
    NestedScrollView svTeacherSearch;
    @BindView(R.id.cdl_teacher)
    CoordinatorLayout cdlT;

    //轮播图list
    private List<String> images;

    private List<TeacherResultBean> teacherResultBeanList = new ArrayList<TeacherResultBean>();

    private SearchAdapter searchAdapter;

    private PopupWindow mPopupWindow;

    private List<TeacherCategoryBean> teacherCategoryBeanList = new ArrayList<TeacherCategoryBean>();
    private CategoryAdapter categoryAdapter;


    private int selectIndex = 0;

    @Override
    protected void init(Bundle savedInstanceState) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_teachersearch_layout;
    }

    @Override
    protected void initView() {
        loadData();
        showFullSearchView(new OnSearchClickListener() {
            @Override
            public void onSearch(String text) {

            }

            @Override
            public void onClearSearch() {

            }
        }, 0);
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
        searchAdapter = new SearchAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(new DividerItemDecoration(mContext,DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(searchAdapter);
        searchAdapter.bindToRecyclerView(recyclerView);
        searchAdapter.setEmptyView(R.layout.recycle_empty_view);
        categoryAdapter = new CategoryAdapter();
        View contentView = LayoutInflater.from(mContext).inflate(
                R.layout.teacher_pop_layout, null);
        categoryRv = contentView.findViewById(R.id.rv_teacher_pop);
        categoryRv.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        categoryRv.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
        categoryRv.setAdapter(categoryAdapter);
        mPopupWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);

        mPopupWindow.setTouchable(true);

        mPopupWindow.setBackgroundDrawable(getResources().getDrawable(R.color.colorAlhfa33));
        mPopupWindow.setTouchInterceptor(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                Log.i("mengdd", "onTouch : ");

                return false;
                // 这里如果返回true的话，touch事件将被拦截
                // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
            }
        });
        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                if (selectIndex == 0) {
                    tvTeacherAll.setSelected(false);
                } else {
                    tvTeacherEvaluation.setSelected(false);
                }
                mPopupWindow.dismiss();
            }
        });

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListenerAddData() {
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
        llAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectIndex = 0;
                showSelectWindow(tvTeacherAll, true);
            }
        });
        llTeacherEvaluation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectIndex = 1;
                showSelectWindow(tvTeacherEvaluation, true);
            }
        });
        //搜索框跳转
        getSearchEdit().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, TeacherListActivity.class));
            }


        });

    }

    //是否展示选中框
    private void showSelectWindow(View view, boolean select) {
        if (select) {
            int y = llSp.getTop();
            avl.setExpanded(false, false);
            view.setSelected(true);
            if (mPopupWindow.isShowing()) {
//            mPopupWindow.dismiss();
                if (!select) {
                    mPopupWindow.dismiss();
                }
            } else {
                if (select) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mPopupWindow.showAsDropDown(llAll, 0, 0);
                        }
                    }, 100);


                }
            }
        } else {
            view.setSelected(false);
            mPopupWindow.dismiss();
        }

    }


    private void loadData() {
        images = new ArrayList<String>();
        images.add("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1586697019,2893742167&fm=26&gp=0.jpg");
        images.add("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3350702307,1620266272&fm=26&gp=0.jpg");
        images.add("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3987297451,840043590&fm=26&gp=0.jpg");
        images.add("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=3866946258,3274036718&fm=26&gp=0.jpg");
        teacherResultBeanList.add(new TeacherResultBean("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1586697019,2893742167&fm=26&gp=0.jpg", "张三", "人称行走的百科全书", 0, 3.5f, 0));
        teacherResultBeanList.add(new TeacherResultBean("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1586697019,2893742167&fm=26&gp=0.jpg", "张三", "人称行走的百科全书", 0, 3.5f, 0));
        teacherResultBeanList.add(new TeacherResultBean("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1586697019,2893742167&fm=26&gp=0.jpg", "张三", "人称行走的百科全书", 0, 3.5f, 0));
        teacherResultBeanList.add(new TeacherResultBean("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1586697019,2893742167&fm=26&gp=0.jpg", "张三", "人称行走的百科全书", 0, 3.5f, 0));
        teacherResultBeanList.add(new TeacherResultBean("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1586697019,2893742167&fm=26&gp=0.jpg", "李四", "人称行走的百科全书", 1, 3.5f, 1));
        teacherResultBeanList.add(new TeacherResultBean("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1586697019,2893742167&fm=26&gp=0.jpg", "李四", "人称行走的百科全书", 1, 3.5f, 1));
        teacherResultBeanList.add(new TeacherResultBean("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1586697019,2893742167&fm=26&gp=0.jpg", "李四", "人称行走的百科全书", 1, 3.5f, 1));
        teacherResultBeanList.add(new TeacherResultBean("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1586697019,2893742167&fm=26&gp=0.jpg", "李四", "人称行走的百科全书", 1, 3.5f, 1));
        teacherResultBeanList.add(new TeacherResultBean("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1586697019,2893742167&fm=26&gp=0.jpg", "李四", "人称行走的百科全书", 1, 3.5f, 1));
        teacherCategoryBeanList.add(new TeacherCategoryBean(1, "分类一"));
        teacherCategoryBeanList.add(new TeacherCategoryBean(1, "分类二"));
    }


    //推荐
    public class SearchAdapter extends BaseQuickAdapter<TeacherResultBean, BaseViewHolder> {
        public SearchAdapter() {
            super(R.layout.teacher_recycle_item, teacherResultBeanList);
        }

        @Override
        protected void convert(BaseViewHolder viewHolder, TeacherResultBean item) {
            viewHolder.setText(R.id.iv_teacher_name, item.getName());
            viewHolder.setText(R.id.tv_evaluation, item.getEvaluation());
            viewHolder.setRating(R.id.ratingbar, item.getRating());
            if (item.getIschecked() == 1) {
                viewHolder.setChecked(R.id.cb_foucs, true);
            } else {
                viewHolder.setChecked(R.id.cb_foucs, false);
            }
            ImageView sexView = viewHolder.getView(R.id.iv_teacher_sex);
            if (item.getSex() == 0) {
                ImageLoaderHelper.getInstance().load(mContext, R.mipmap.icon_man, sexView);
            } else {
                ImageLoaderHelper.getInstance().load(mContext, R.mipmap.icon_women, sexView);
            }
            ImageView imageView = viewHolder.getView(R.id.iv_teacher_head);
            ImageLoaderHelper.getInstance().load(mContext, item.getHead(), imageView);
        }
    }


    //分类适配器

    public class CategoryAdapter extends BaseQuickAdapter<TeacherCategoryBean, BaseViewHolder> {
        public CategoryAdapter() {
            super(R.layout.teacher_sp_drop_item, teacherCategoryBeanList);
        }

        @Override
        protected void convert(BaseViewHolder viewHolder, TeacherCategoryBean item) {
            viewHolder.setText(R.id.tv_teacher_drop, item.getSelectName());
            viewHolder.setOnClickListener(R.id.tv_teacher_drop, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (selectIndex == 0) {
                        tvTeacherAll.setSelected(false);
                        tvTeacherAll.setText(item.getSelectName());
                    } else {
                        tvTeacherEvaluation.setSelected(false);
                        tvTeacherEvaluation.setText(item.getSelectName());
                    }

                    mPopupWindow.dismiss();
                }
            });
        }
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

    @Override
    protected void onDestroy() {
        banner.stopAutoPlay();
        super.onDestroy();
    }
}
