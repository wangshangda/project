package study.tthy.com.study.ui.teacher.fragment;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
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
import study.tthy.com.study.ui.teacher.bean.EvaluateBean;
import study.tthy.com.study.util.ObjectUtils;

/**
 * Created by Administrator on 2019/3/29.
 */

public class TeacherEvaluateFragment extends BaseFragment {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.srl_evalute)
    SmartRefreshLayout srlEvalute;
    Unbinder unbinder;
    private List<EvaluateBean> evaluateBeanList = new ArrayList<EvaluateBean>();
    private EvaluateAdapter evaluateAdapter;


    @Override
    protected IPresenter getPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.frament_teacher_evaluate_layout;
    }

    @Override
    protected void initView(View rootView) {
        evaluateAdapter = new EvaluateAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(evaluateAdapter);
    }

    @Override
    protected void initListenerAddData() {

    }

    @Override
    protected void initData() {
        evaluateBeanList.add(new EvaluateBean(AppContentContants.TEST_HEAD_1, "shiningshell", "我正在参加了不起的研学株洲站活动", "2019-5-12", "", 3.5f));
        evaluateBeanList.add(new EvaluateBean(AppContentContants.TEST_HEAD_1, "shiningshell", "我正在参加了不起的研学株洲站活动", "2019-5-12", "谢谢喜欢哈哈", 3.5f));
        evaluateBeanList.add(new EvaluateBean(AppContentContants.TEST_HEAD_1, "shiningshell", "我正在参加了不起的研学株洲站活动", "2019-5-12", "谢谢喜欢哈哈谢谢喜欢哈哈谢谢喜欢哈哈谢谢喜欢哈哈谢谢喜欢哈哈谢谢喜欢哈哈谢谢喜欢哈哈谢谢喜欢哈哈谢谢喜欢哈哈谢谢喜欢哈哈谢谢喜欢哈哈谢谢喜欢哈哈谢谢喜欢哈哈谢谢喜欢哈哈谢谢喜欢哈哈谢谢喜欢哈哈谢谢喜欢哈哈谢谢喜欢哈哈谢谢喜欢哈哈谢谢喜欢哈哈谢谢喜欢哈哈谢谢喜欢哈哈谢谢喜欢哈哈谢谢喜欢哈哈谢谢喜欢哈哈谢谢喜欢哈哈谢谢喜欢哈哈谢谢喜欢哈哈谢谢喜欢哈哈谢谢喜欢哈哈谢谢喜欢哈哈谢谢喜欢哈哈谢谢喜欢哈哈谢谢喜欢哈哈谢谢喜欢哈哈谢谢喜欢哈哈", 3.5f));
        List<String> pics = new ArrayList<String>();
        pics.add(AppContentContants.TEST_IMG_5);
        pics.add(AppContentContants.TEST_IMG_6);
        pics.add(AppContentContants.TEST_IMG_7);
        evaluateBeanList.get(0).setPic(pics);
        setEmptyView(evaluateAdapter, recyclerView, R.mipmap.icon_no_message, "赶紧发布第一条对TA的评价吧~");
        evaluateAdapter.notifyDataSetChanged();
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

    public class EvaluateAdapter extends BaseQuickAdapter<EvaluateBean, BaseViewHolder> {

        public EvaluateAdapter() {
            super(R.layout.teacher_recycle_evaluate_item, evaluateBeanList);
        }

        @Override
        protected void convert(BaseViewHolder viewHolder, EvaluateBean item) {
            viewHolder.setText(R.id.tv_evaluate_name, item.getName());
            viewHolder.setText(R.id.tv_evaluate_date, item.getDate());
            viewHolder.setText(R.id.tv_evaluate_content, item.getContent());
            viewHolder.setText(R.id.iv_reply_content, item.getReplay());
            viewHolder.setRating(R.id.ratingbar,item.getRating());
            ImageLoaderHelper.getInstance().loadHead(mContext, item.getHead(), viewHolder.getView(R.id.iv_head));
            List<String> mlist = item.getPic();
            if (!ObjectUtils.isEmpty(mlist)) {
                if (mlist.size() > 0) {
                    viewHolder.setVisible(R.id.iv_evaluate_pic1, true);
                    ImageLoaderHelper.getInstance().load(mContext, mlist.get(0), (ImageView) viewHolder.getView(R.id.iv_evaluate_pic1));
                } else {
                    viewHolder.setGone(R.id.ll_evaluate_pic, false);
                }
                if (mlist.size() > 1) {
                    viewHolder.setVisible(R.id.iv_evaluate_pic2, true);
                    ImageLoaderHelper.getInstance().load(mContext, mlist.get(1), (ImageView) viewHolder.getView(R.id.iv_evaluate_pic2));
                } else {
                    viewHolder.setVisible(R.id.iv_evaluate_pic2, false);
                }
                if (mlist.size() > 2) {
                    viewHolder.setVisible(R.id.iv_evaluate_pic3, true);
                    ImageLoaderHelper.getInstance().load(mContext, mlist.get(2), (ImageView) viewHolder.getView(R.id.iv_evaluate_pic3));
                } else {
                    viewHolder.setVisible(R.id.iv_evaluate_pic3, false);
                }
            } else {
                viewHolder.setGone(R.id.ll_evaluate_pic, false);
            }

            if (!TextUtils.isEmpty(item.getReplay())) {
                viewHolder.setText(R.id.iv_reply_content, "\t\t\t\t" + item.getReplay());
            } else {
                viewHolder.setGone(R.id.ll_teacher_Reply, false);
            }
        }

    }

}
