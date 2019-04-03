package study.tthy.com.study.ui.teacher;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.umeng.socialize.UMShareAPI;

import butterknife.BindView;
import butterknife.ButterKnife;
import study.tthy.com.study.R;
import study.tthy.com.study.base.BaseActivity;
import study.tthy.com.study.base.BasePresenter;
import study.tthy.com.study.help.ShareHelper;

/**
 * Created by Administrator on 2019/4/2.
 */
//教师二维码分享
public class TeacherQrShareActivity extends BaseActivity {
    @BindView(R.id.iv_share_close)
    ImageView ivShareClose;
    @BindView(R.id.iv_teacher_share_head)
    ImageView ivTeacherShareHead;
    @BindView(R.id.iv_authentication)
    ImageView ivAuthentication;
    @BindView(R.id.tv_teacher_share_name)
    TextView tvTeacherShareName;
    @BindView(R.id.rl_teacher_name)
    RelativeLayout rlTeacherName;
    @BindView(R.id.ratingbar_teacher)
    RatingBar ratingbarTeacher;
    @BindView(R.id.tv_teacher_share_dynamic_num)
    TextView tvTeacherShareDynamicNum;
    @BindView(R.id.tv_teacher_share_follow_num)
    TextView tvTeacherShareFollowNum;
    @BindView(R.id.tv_teacher_share_fans_num)
    TextView tvTeacherShareFansNum;
    @BindView(R.id.rl_top_bg)
    RelativeLayout rlTopBg;
    @BindView(R.id.iv_teacher_qr)
    ImageView ivTeacherQr;

    @Override
    protected void init(Bundle savedInstanceState) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_teacher_qr_share_layout;
    }

    @Override
    protected void initView() {
        setToolBarVisiable(false);
        setBaseStatusBar(true);
    }


    @Override
    protected void initData() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ShareHelper.getInstance().openShare(mContext, new ShareHelper.ShareListener() {
                    @Override
                    public void shareOnSuccess() {

                    }

                    @Override
                    public void shareOnFailed() {

                    }
                });
            }
        }, 200);

    }

    @Override
    protected void initListenerAddData() {
        ivShareClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected void onDestroy() {
        UMShareAPI.get(this).release();
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
