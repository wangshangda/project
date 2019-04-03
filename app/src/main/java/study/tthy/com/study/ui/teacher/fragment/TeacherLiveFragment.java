package study.tthy.com.study.ui.teacher.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import study.tthy.com.study.R;
import study.tthy.com.study.base.IPresenter;
import study.tthy.com.study.base.baseControl.AppContentContants;
import study.tthy.com.study.base.fragment.BaseFragment;
import study.tthy.com.study.help.ImageLoaderHelper;
import study.tthy.com.study.ui.teacher.bean.TeacherLiveBean;
import study.tthy.com.study.util.CommonUtils;

/**
 * Created by Administrator on 2019/3/29.
 */

public class TeacherLiveFragment extends BaseFragment {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.srl_live)
    SmartRefreshLayout srlLive;
    Unbinder unbinder;
    private List<TeacherLiveBean> teacherLiveBeanList = new ArrayList<TeacherLiveBean>();
    private LiveAdapter liveAdapter;

    @Override
    protected IPresenter getPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.frament_teacher_live_layout;
    }

    @Override
    protected void initView(View rootView) {
        recyclerView.setLayoutManager(new GridLayoutManager(mContext,2));
        liveAdapter=new LiveAdapter();
        recyclerView.setAdapter(liveAdapter);
        setEmptyView(liveAdapter,recyclerView,R.mipmap.icon_no_live,"TA暂时还没有任何直播哦~");
    }

    @Override
    protected void initListenerAddData() {

    }

    @Override
    protected void initData() {
        teacherLiveBeanList.add(new TeacherLiveBean(AppContentContants.TEST_IMG_3, "红色研学第一场", AppContentContants.TEST_HEAD_1, 23455, "2019-5-1", 1));
        teacherLiveBeanList.add(new TeacherLiveBean(AppContentContants.TEST_IMG_4, "红色研学第一场", AppContentContants.TEST_HEAD_1, 23455, "2019-5-1", 0));
        liveAdapter.notifyDataSetChanged();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    public class LiveAdapter extends BaseQuickAdapter<TeacherLiveBean, BaseViewHolder> {
        public LiveAdapter() {
            super(R.layout.teacher_live_item, teacherLiveBeanList);
        }

        @Override
        protected void convert(BaseViewHolder viewHolder, TeacherLiveBean item) {
            viewHolder.setText(R.id.tv_living_title, item.getTitle());
            viewHolder.setText(R.id.tv_living_hot, CommonUtils.getShowNumbers(item.getHotNum()));
            viewHolder.setText(R.id.tv_living_date, item.getDate());
            ImageView imageView = viewHolder.getView(R.id.iv_living_bg);
            ImageView head = viewHolder.getView(R.id.iv_living_head);
            ImageLoaderHelper.getInstance().LoadCircleRet(mContext, item.getImgUrl(), imageView, 4);
            if (item.getStatus() == 0) {
                viewHolder.getView(R.id.tv_live_status).setSelected(false);
            } else {
                viewHolder.getView(R.id.tv_live_status).setSelected(true);
            }
            ImageLoaderHelper.getInstance().loadHead(mContext, item.getHead(), head);
        }
    }

}
