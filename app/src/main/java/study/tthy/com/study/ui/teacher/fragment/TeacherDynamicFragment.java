package study.tthy.com.study.ui.teacher.fragment;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import study.tthy.com.study.R;
import study.tthy.com.study.base.IPresenter;
import study.tthy.com.study.base.fragment.BaseFragment;
import study.tthy.com.study.help.ImageLoaderHelper;
import study.tthy.com.study.ui.main.home.entity.TravelUserBean;
import study.tthy.com.study.ui.teacher.bean.DynamicBean;
import study.tthy.com.study.util.CommonUtils;
import study.tthy.com.study.util.ObjectUtils;

/**
 * Created by Administrator on 2019/3/28.
 */

public class TeacherDynamicFragment extends BaseFragment {


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    Unbinder unbinder;


    private List<DynamicBean> dynamicBeanList = new ArrayList<DynamicBean>();
    private DynamicAdapter dynamicAdapter = new DynamicAdapter();

    @Override
    protected void init(Bundle savedInstanceState) {

    }

    @Override
    protected IPresenter getPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.frament_teacher_dynamic_layout;
    }

    @Override
    protected void initView(View rootView) {

    }

    @Override
    protected void initListenerAddData() {

    }

    @Override
    protected void initData() {
        dynamicBeanList.add(new DynamicBean(DynamicBean.IMG,"https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1586697019,2893742167&fm=26&gp=0.jpg", "shiningshell", "测试测试", "2019-3-24", 1, 121, 2323, 4412));
        dynamicBeanList.add(new DynamicBean(DynamicBean.IMG,"https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1586697019,2893742167&fm=26&gp=0.jpg", "shiningshell", "测试测试", "2019-3-24", 1, 121, 2323, 4412));
        List<String> mImgList = new ArrayList<String>();
        mImgList.add("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1586697019,2893742167&fm=26&gp=0.jpg");
        mImgList.add("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3350702307,1620266272&fm=26&gp=0.jpg");
        mImgList.add("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=3866946258,3274036718&fm=26&gp=0.jpg");
        dynamicBeanList.get(0).setPic(mImgList);
        dynamicBeanList.get(1).setPic(mImgList);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(dynamicAdapter);
        setEmptyView(dynamicAdapter,recyclerView,R.mipmap.dynamic_nodata_bg,"TA暂时还没有发布动态哦~");
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

    //游记
    public class DynamicAdapter extends BaseQuickAdapter<DynamicBean, BaseViewHolder> {

        public DynamicAdapter() {
            super(R.layout.teacher_recycle_dynamic_item, dynamicBeanList);
        }

        @Override
        protected void convert(BaseViewHolder viewHolder, DynamicBean item) {
            viewHolder.setText(R.id.tv_dynamic_name, item.getName());
            viewHolder.setText(R.id.tv_dynamic_date, item.getDate());
            viewHolder.setText(R.id.tv_dynamic_content, item.getContent());
            viewHolder.setText(R.id.tv_message_num, CommonUtils.getShowNumbers(item.getEvaluationNum()));
            viewHolder.setText(R.id.tv_forward_num, CommonUtils.getShowNumbers(item.getForwardNum()));
            viewHolder.setText(R.id.cb_praise_num, CommonUtils.getShowNumbers(item.getPraiseNum()));
            List<String> mlist = item.getPic();
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
        }

    }


}
