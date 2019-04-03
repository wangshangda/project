package study.tthy.com.study.ui.teacher;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import study.tthy.com.study.R;
import study.tthy.com.study.base.BaseActivity;
import study.tthy.com.study.base.BasePresenter;
import study.tthy.com.study.base.baseControl.AppContentContants;
import study.tthy.com.study.help.ImageLoaderHelper;
import study.tthy.com.study.ui.teacher.bean.EvaluatePicBean;
import study.tthy.com.study.util.ObjectUtils;
import study.tthy.com.study.util.ToastUtils;

/**
 * Created by Administrator on 2019/3/29.
 */

public class TeacherEvaluateActivity extends BaseActivity {
    @BindView(R.id.iv_head)
    ImageView ivHead;
    @BindView(R.id.btn_evaluate)
    Button btnEvaluate;
    @BindView(R.id.tv_evaluate_name)
    TextView tvEvaluateName;
    @BindView(R.id.ratingbar)
    RatingBar ratingbar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.iv_evaluate_close)
    ImageView ivEvaluateClose;
    @BindView(R.id.ed_evaluate)
    EditText edEvaluate;
    @BindView(R.id.tv_evaluation_num)
    TextView tvEvaluationNum;


    private List<LocalMedia> evaluatePicBeanList = new ArrayList<LocalMedia>();
    private PicAdapter picAdapter;

    @Override
    protected void init(Bundle savedInstanceState) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_evaluate_layout;
    }

    @Override
    protected void initView() {
        setBaseStatusBar(true);
        setToolBarVisiable(false);
        picAdapter = new PicAdapter();
        recyclerView.setLayoutManager(new GridLayoutManager(mContext, 4));
        recyclerView.setAdapter(picAdapter);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListenerAddData() {
        evaluatePicBeanList.add(new EvaluatePicBean(0, ""));
        evaluatePicBeanList.get(0).setPosition(-1);
        picAdapter.notifyDataSetChanged();
        ImageLoaderHelper.getInstance().loadHead(mContext, AppContentContants.TEST_HEAD_1, ivHead);
        ivHead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<LocalMedia> localMedias = new ArrayList<LocalMedia>();
                LocalMedia localMedia = new LocalMedia();
                localMedia.setPath(AppContentContants.TEST_HEAD_1);
                localMedias.add(localMedia);
                PictureSelector.create(mActivity).themeStyle(R.style.picture_default_style).openExternalPreview(0, localMedias);
            }
        });
        ivEvaluateClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        edEvaluate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length()>200){
                    ToastUtils.showLong(mContext,"评论限制200字");
                }
                tvEvaluationNum.setText(s.length()+"/200");
            }
        });

        edEvaluate.setFilters(new InputFilter[]{new InputFilter.LengthFilter(200)});


    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    /**
     * 图片选择
     */
    private void pictureSelector() {
        evaluatePicBeanList.remove(evaluatePicBeanList.size() - 1);
        List<LocalMedia> mediaArrayList = evaluatePicBeanList;
        PictureSelector.create(mActivity).openGallery(PictureMimeType.ofImage()).selectionMedia(mediaArrayList).imageSpanCount(4).previewImage(true).compress(true).cropCompressQuality(90).selectionMode(PictureConfig.MULTIPLE)
                .maxSelectNum(6).forResult(PictureConfig.CHOOSE_REQUEST);

    }

    //图片预览
    private void prePics(int position) {
        evaluatePicBeanList.remove(evaluatePicBeanList.size() - 1);
        List<LocalMedia> mediaArrayList = evaluatePicBeanList;
        PictureSelector.create(mActivity).themeStyle(R.style.picture_default_style).openExternalPreview(position, mediaArrayList);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    public class PicAdapter extends BaseQuickAdapter<LocalMedia, BaseViewHolder> {
        public PicAdapter() {
            super(R.layout.teacher_evaluate_publish_item, evaluatePicBeanList);
        }

        @Override
        protected void convert(BaseViewHolder viewHolder, LocalMedia item) {
            if (item.getPosition() != -1) {
                viewHolder.setVisible(R.id.rl_evaluate_pic, true);
                viewHolder.setGone(R.id.ll_addpic, false);
                ImageView imageView = viewHolder.getView(R.id.iv_evaluate_pic);
                ImageLoaderHelper.getInstance().load(mContext, item.getCompressPath(), imageView);
                viewHolder.setOnClickListener(R.id.iv_evaluate_close, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        evaluatePicBeanList.remove(item);
                        notifyDataSetChanged();
                    }
                });
                viewHolder.setOnClickListener(R.id.iv_evaluate_pic, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        prePics(viewHolder.getAdapterPosition());
                    }
                });
            } else {
                //添加按钮
                viewHolder.setGone(R.id.rl_evaluate_pic, false);
                viewHolder.setVisible(R.id.ll_addpic, true);
                viewHolder.getView(R.id.ll_addpic).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        pictureSelector();
                    }
                });
            }

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片、视频、音频选择结果回调
                    List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                    if (!ObjectUtils.isEmpty(selectList)) {
                        evaluatePicBeanList.clear();
                        evaluatePicBeanList.addAll(selectList);
                        LocalMedia add = new LocalMedia();
                        add.setPosition(-1);
                        evaluatePicBeanList.add(add);
                        // 例如 LocalMedia 里面返回三种path
                        // 1.media.getPath(); 为原图path
                        // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true  注意：音视频除外
                        // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true  注意：音视频除外
                        // 如果裁剪并压缩了，以取压缩路径为准，因为是先裁剪后压缩的
                        picAdapter.notifyDataSetChanged();
                    }
                    break;
            }
        }
    }
}
