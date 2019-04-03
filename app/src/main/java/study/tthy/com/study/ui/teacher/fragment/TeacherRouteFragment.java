package study.tthy.com.study.ui.teacher.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
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
import study.tthy.com.study.ui.teacher.bean.RouteBean;

/**
 * Created by Administrator on 2019/3/29.
 */

public class TeacherRouteFragment extends BaseFragment {


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.srl_route)
    SmartRefreshLayout srlRoute;
    Unbinder unbinder;
    private List<RouteBean> routeBeanList = new ArrayList<RouteBean>();
    private RouteAdapter routeAdapter;

    @Override
    protected IPresenter getPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.frament_teacher_route_layout;
    }

    @Override
    protected void initView(View rootView) {
        routeAdapter = new RouteAdapter();
        recyclerView.setLayoutManager(new GridLayoutManager(mContext,2));
        recyclerView.setAdapter(routeAdapter);
        setEmptyView(routeAdapter,recyclerView,R.mipmap.icon_no_route,"TA正在规划路线哦~");
    }

    @Override
    protected void initListenerAddData() {

    }

    @Override
    protected void initData() {
        routeBeanList.add(new RouteBean(AppContentContants.TEST_IMG_1, AppContentContants.TEST_HEAD_1, "邂逅卢瓦尔河谷体验法兰西浪漫的“公主摆..."));
        routeBeanList.add(new RouteBean(AppContentContants.TEST_IMG_2, AppContentContants.TEST_HEAD_1, "邂逅卢瓦尔河谷体验法兰西浪漫的“公主摆..."));
        routeAdapter.notifyDataSetChanged();
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

    public class RouteAdapter extends BaseQuickAdapter<RouteBean, BaseViewHolder> {
        public RouteAdapter() {
            super(R.layout.teacher_route_item, routeBeanList);
        }

        @Override
        protected void convert(BaseViewHolder viewHolder, RouteBean item) {
            viewHolder.setText(R.id.tv_route_title, item.getContent());
            ImageView imageView = viewHolder.getView(R.id.iv_route);
            ImageView head = viewHolder.getView(R.id.iv_route_head);
            ImageLoaderHelper.getInstance().load(mContext, item.getImgUrl(), imageView);
            ImageLoaderHelper.getInstance().loadHead(mContext, item.getHead(), head);
        }
    }


}
