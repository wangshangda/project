package study.tthy.com.study.ui.teacher;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.jzvd.Jzvd;
import cn.jzvd.JzvdStd;
import study.tthy.com.study.R;
import study.tthy.com.study.base.BaseActivity;
import study.tthy.com.study.base.BasePresenter;
import study.tthy.com.study.base.baseControl.AppContentContants;
import study.tthy.com.study.help.ImageLoaderHelper;
import study.tthy.com.study.media.JZMediaIjk;
import study.tthy.com.study.ui.teacher.bean.DynamicBean;
import study.tthy.com.study.util.CommonUtils;
import study.tthy.com.study.util.ObjectUtils;
import study.tthy.com.study.widget.LoadingView;

/**
 * Created by Administrator on 2019/3/26.
 */
//动态搜索界面
public class TeacherDynamicSearchActivity extends BaseActivity {


    @BindView(R.id.tv_search_num)
    TextView tvSearchNum;
    @BindView(R.id.ll_serarch_tips)
    LinearLayout llSerarchTips;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.srl_dynamic)
    SmartRefreshLayout srlDynamic;
    private List<DynamicBean> dynamicBeanList = new ArrayList<DynamicBean>();
    private DynamicAdapter dynamicAdapter;
    LoadingView loadingView;

    @Override
    protected void init(Bundle savedInstanceState) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_teacher_dynamic_search_layout;
    }

    @Override
    protected void initView() {
        setCancleBtn(true, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        setBackBtnVisiable(false);
        showFullSearchView(new OnSearchClickListener() {
            @Override
            public void onSearch(String text) {

            }

            @Override
            public void onClearSearch() {

            }
        }, PIC_LEFT);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
        dynamicAdapter = new DynamicAdapter();
        recyclerView.setAdapter(dynamicAdapter);
        setEmptyView(dynamicAdapter, recyclerView, R.mipmap.dynamic_nodata_bg, "TA暂时还没有发布动态哦~");
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListenerAddData() {
        dynamicBeanList.add(new DynamicBean(DynamicBean.VIDEO, AppContentContants.TEST_HEAD_1, "shiningshell", "测试测试", "2019-3-24", 1, 121, 2323, 4412));
        dynamicBeanList.add(new DynamicBean(DynamicBean.IMG, AppContentContants.TEST_HEAD_1, "shiningshell", "测试测试", "2019-3-24", 1, 121, 2323, 4412));
        dynamicBeanList.add(new DynamicBean(DynamicBean.VIDEO, AppContentContants.TEST_HEAD_1, "shiningshell", "测试测试", "2019-3-24", 1, 121, 2323, 4412));
        dynamicBeanList.add(new DynamicBean(DynamicBean.VIDEO, AppContentContants.TEST_HEAD_1, "shiningshell", "测试测试", "2019-3-24", 1, 121, 2323, 4412));
        dynamicBeanList.get(0).setVideoUrl(AppContentContants.TEST_VIDEO_1);
        List<String> mImgList = new ArrayList<String>();
        mImgList.add("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1586697019,2893742167&fm=26&gp=0.jpg");
        mImgList.add("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3350702307,1620266272&fm=26&gp=0.jpg");
        mImgList.add("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=3866946258,3274036718&fm=26&gp=0.jpg");
        dynamicBeanList.get(1).setPic(mImgList);
        dynamicBeanList.get(2).setVideoUrl(AppContentContants.TEST_VIDEO_2);
        dynamicBeanList.get(3).setVideoUrl(AppContentContants.TEST_VIDEO_4);
        dynamicAdapter.notifyDataSetChanged();
        recyclerView.addOnChildAttachStateChangeListener(new RecyclerView.OnChildAttachStateChangeListener() {
            @Override
            public void onChildViewAttachedToWindow(View view) {

            }

            @Override
            public void onChildViewDetachedFromWindow(View view) {
                Jzvd jzvd = view.findViewById(R.id.videoplayer);
                if (jzvd != null && Jzvd.CURRENT_JZVD != null &&
                        jzvd.jzDataSource.containsTheUrl(Jzvd.CURRENT_JZVD.jzDataSource.getCurrentUrl())) {
                    if (Jzvd.CURRENT_JZVD != null && Jzvd.CURRENT_JZVD.currentScreen != Jzvd.SCREEN_WINDOW_FULLSCREEN) {
                        Jzvd.resetAllVideos();
                    }
                }
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

    public class DynamicAdapter extends BaseMultiItemQuickAdapter<DynamicBean, BaseViewHolder> {
        List<String> mVideoPathList;
        List<BaseViewHolder> mViewHolderList;

        public DynamicAdapter() {
            super(dynamicBeanList);
            addItemType(DynamicBean.VIDEO, R.layout.teacher_recycle_dynamic_videoitem);
            addItemType(DynamicBean.IMG, R.layout.teacher_recycle_dynamic_item);
        }

        void stopAll() {
            for (BaseViewHolder vh : mViewHolderList) {
            }
        }

        @Override
        protected void convert(BaseViewHolder viewHolder, DynamicBean item) {
            viewHolder.setText(R.id.tv_dynamic_name, item.getName());
            viewHolder.setText(R.id.tv_dynamic_date, item.getDate());
            viewHolder.setText(R.id.tv_dynamic_content, item.getContent());
            viewHolder.setText(R.id.tv_message_num, CommonUtils.getShowNumbers(item.getEvaluationNum()));
            viewHolder.setText(R.id.tv_forward_num, CommonUtils.getShowNumbers(item.getForwardNum()));
            viewHolder.setText(R.id.cb_praise_num, CommonUtils.getShowNumbers(item.getPraiseNum()));
            ImageLoaderHelper.getInstance().loadHead(mContext,item.getHead(),viewHolder.getView(R.id.iv_head));
            List<String> mlist = item.getPic();
            if (item.getIsPrise()==0){
                viewHolder.getView(R.id.cb_praise_num).setSelected(false);
                viewHolder.setOnClickListener(R.id.cb_praise_num, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        item.setIsPrise(1);
                        notifyDataSetChanged();
                    }
                });
            }else {
                viewHolder.getView(R.id.cb_praise_num).setSelected(true);
                viewHolder.setOnClickListener(R.id.cb_praise_num, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        item.setIsPrise(0);
                        notifyDataSetChanged();
                    }
                });
            }

            switch (viewHolder.getItemViewType()) {
                case DynamicBean.VIDEO:

                    JzvdStd jzvd = viewHolder.getView(R.id.videoplayer);
//                    ImageLoaderHelper.getInstance().loadVideoScreenshot(mContext, item.getVideoUrl(), jzvd.thumbImageView, 0);
                    jzvd.setUp(
                            item.getVideoUrl(), "测试视频", Jzvd.SCREEN_NORMAL, new JZMediaIjk(jzvd));
                    ImageLoaderHelper.getInstance().load(mContext, AppContentContants.TEST_IMG_2, jzvd.thumbImageView);
                    break;
                case DynamicBean.IMG:
                    if (!ObjectUtils.isEmpty(mlist)) {
                        if (mlist.size() > 0) {
                            viewHolder.setVisible(R.id.iv_dynamic_pic1, true);
                            ImageLoaderHelper.getInstance().load(mContext, mlist.get(0), (ImageView) viewHolder.getView(R.id.iv_dynamic_pic1));
                        } else {
                            viewHolder.setGone(R.id.ll_dynamic_pic, false);
                        }
                        if (mlist.size() > 1) {
                            viewHolder.setVisible(R.id.iv_dynamic_pic2, true);
                            ImageLoaderHelper.getInstance().load(mContext, mlist.get(1), (ImageView) viewHolder.getView(R.id.iv_dynamic_pic2));
                        } else {
                            viewHolder.setVisible(R.id.iv_dynamic_pic2, false);
                        }
                        if (mlist.size() > 2) {
                            viewHolder.setVisible(R.id.iv_dynamic_pic3, true);
                            ImageLoaderHelper.getInstance().load(mContext, mlist.get(2), (ImageView) viewHolder.getView(R.id.iv_dynamic_pic3));
                        } else {
                            viewHolder.setVisible(R.id.iv_dynamic_pic3, false);
                        }

                    } else {
                        viewHolder.setGone(R.id.ll_travel_pic, false);
                    }
                    break;
            }

        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        Jzvd.resetAllVideos();
    }

    @Override
    protected void onDestroy() {
        recyclerView.setAdapter(null);
        super.onDestroy();
    }


    @Override
    public void onBackPressed() {
        if (Jzvd.backPress()) {
            return;
        }
        super.onBackPressed();
    }

}
