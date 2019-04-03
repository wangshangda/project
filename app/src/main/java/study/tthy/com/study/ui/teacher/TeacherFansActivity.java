package study.tthy.com.study.ui.teacher;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

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

/**
 * Created by Administrator on 2019/3/29.
 */

public class TeacherFansActivity extends BaseActivity {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.srl_teacher_fans)
    SmartRefreshLayout srlTeacherFans;
    private List<TeacherResultBean> teacherResultBeanList = new ArrayList<TeacherResultBean>();
    private FansAdapter fansAdapter=new FansAdapter();

    @Override
    protected void init(Bundle savedInstanceState) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_teacher_fans_layout;
    }

    @Override
    protected void initView() {
        setToolBarTitle("粉丝",true);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false));
        recyclerView.addItemDecoration(new DividerItemDecoration(mContext,DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(fansAdapter);
    }

    @Override
    protected void initData() {
        teacherResultBeanList.add(new TeacherResultBean(AppContentContants.TEST_HEAD_1,"张三","",1,3.5f,0));
        teacherResultBeanList.add(new TeacherResultBean(AppContentContants.TEST_HEAD_1,"李四","",0,3.5f,1));
        fansAdapter.notifyDataSetChanged();
    }

    @Override
    protected void initListenerAddData() {

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


    public class FansAdapter extends BaseQuickAdapter<TeacherResultBean, BaseViewHolder> {
        public FansAdapter() {
            super(R.layout.teacher_fans_item, teacherResultBeanList);
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
            ImageView imageView = viewHolder.getView(R.id.iv_teacher_head);
            ImageLoaderHelper.getInstance().load(mContext, item.getHead(), imageView);
        }
    }
}
