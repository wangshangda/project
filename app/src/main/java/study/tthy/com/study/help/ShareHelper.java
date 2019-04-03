package study.tthy.com.study.help;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;

/**
 * Created by Administrator on 2019/4/2.
 */

public class ShareHelper {
    private Context mContext;
    private static volatile ShareHelper instance;
    private ShareListener mShareListener;

    private ShareHelper() {
    }

    public static ShareHelper getInstance() {

        if (instance == null) {
            synchronized (ShareHelper.class) {
                if (instance == null) {
                    instance = new ShareHelper();
                }
            }
        }

        return instance;
    }

    public void openShare(Context activity,ShareListener shareListener) {
        mShareListener = shareListener;
        new ShareAction((Activity) activity).withText("hello").setDisplayList(SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.SINA, SHARE_MEDIA.QQ)
                .setCallback(umShareListener).open();
    }


    public interface ShareListener {
        /**
         * 分享成功
         */
        void shareOnSuccess();

        /***
         * 分享失败
         */
        void shareOnFailed();
    }


    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onStart(SHARE_MEDIA share_media) {

        }

        @Override
        public void onResult(SHARE_MEDIA platform) {
            Log.d("plat", "platform" + platform);
            if (platform.name().equals("WEIXIN_FAVORITE")) {
            } else {
                if (mShareListener != null) {
                    mShareListener.shareOnSuccess();
                }
            }
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            if (mShareListener != null) {
                mShareListener.shareOnFailed();
            }
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            if (mShareListener != null) {
                mShareListener.shareOnFailed();
            }
        }
    };
}
