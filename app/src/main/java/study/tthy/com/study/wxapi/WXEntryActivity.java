package study.tthy.com.study.wxapi;

import android.content.Intent;

import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.weixin.view.WXCallbackActivity;

/**
 * Created by Administrator on 2019/4/2.
 */

public class WXEntryActivity extends WXCallbackActivity {

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }
}
