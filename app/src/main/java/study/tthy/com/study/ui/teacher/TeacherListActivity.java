package study.tthy.com.study.ui.teacher;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import study.tthy.com.study.R;
import study.tthy.com.study.base.BaseActivity;
import study.tthy.com.study.base.BasePresenter;
import study.tthy.com.study.help.ImageLoaderHelper;
import study.tthy.com.study.ui.teacher.bean.TeacherResultBean;

/**
 * Created by Administrator on 2019/3/27.
 * 搜索的导师列表
 */

public class TeacherListActivity extends BaseActivity {


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private List<TeacherResultBean> teacherResultBeanList = new ArrayList<TeacherResultBean>();
    private CategoryAdapter categoryAdapter;

    @Override
    protected void init(Bundle savedInstanceState) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_teacher_list_layout;
    }

    @Override
    protected void initView() {
        //Todo 关键字变色
        setBackBtnVisiable(false);
        setCancleBtn(true, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        showFullSearchView(new OnSearchClickListener() {
            @Override
            public void onSearch(String text) {

            }

            @Override
            public void onClearSearch() {

            }
        }, PIC_LEFT);

        categoryAdapter = new CategoryAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(categoryAdapter);
        categoryAdapter.bindToRecyclerView(recyclerView);
        categoryAdapter.setEmptyView(R.layout.recycle_empty_view);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListenerAddData() {
        teacherResultBeanList.add(new TeacherResultBean("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1586697019,2893742167&fm=26&gp=0.jpg", "张三", "人称行走的百科全书", 0, 3.5f, 0));
        teacherResultBeanList.add(new TeacherResultBean("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1586697019,2893742167&fm=26&gp=0.jpg", "张三", "人称行走的百科全书", 1, 3.5f, 1));

    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }


    public class CategoryAdapter extends BaseQuickAdapter<TeacherResultBean, BaseViewHolder> {
        public CategoryAdapter() {
            super(R.layout.teacher_list_item, teacherResultBeanList);
        }

        @Override
        protected void convert(BaseViewHolder viewHolder, TeacherResultBean item) {
            viewHolder.setText(R.id.iv_teacher_name, item.getName());
            viewHolder.setText(R.id.tv_evaluation, item.getEvaluation());
            viewHolder.setRating(R.id.ratingbar, item.getRating());
            ImageView sexView = viewHolder.getView(R.id.iv_teacher_sex);
            if (item.getSex() == 0) {
                ImageLoaderHelper.getInstance().load(mContext, R.mipmap.icon_man, sexView);
            } else {
                ImageLoaderHelper.getInstance().load(mContext, R.mipmap.icon_women, sexView);
            }
            ImageView imageView = viewHolder.getView(R.id.iv_teacher_head);
            ImageLoaderHelper.getInstance().load(mContext, item.getHead(), imageView);
            viewHolder.getView(R.id.rl_content).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(mContext, TeacherDetailActivity.class));
                }
            });
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
