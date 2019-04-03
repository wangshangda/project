package study.tthy.com.study.ui.teacher.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import study.tthy.com.study.R;
import study.tthy.com.study.base.IPresenter;
import study.tthy.com.study.base.fragment.BaseFragment;
import study.tthy.com.study.help.ImageLoaderHelper;

/**
 * Created by Administrator on 2019/3/29.
 */

public class TeacherIntroduceFragment extends BaseFragment {
    @BindView(R.id.iv_introduce_bg)
    ImageView ivIntroduceBg;
    @BindView(R.id.tv_introduce_content)
    TextView tvIntroduceContent;
    Unbinder unbinder;

    @Override
    protected IPresenter getPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.frament_teacher_introduce_layout;
    }

    @Override
    protected void initView(View rootView) {
        ImageLoaderHelper.getInstance().load(mContext,"http://img1.imgtn.bdimg.com/it/u=3937164894,1552579067&fm=26&gp=0.jpg",ivIntroduceBg);
    }

    @Override
    protected void initListenerAddData() {

    }

    @Override
    protected void initData() {

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }




}
