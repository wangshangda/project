package study.tthy.com.study.api;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;

import study.tthy.com.study.R;

/**
 * Created by Administrator on 2019/3/27.
 */

public class TestActivity extends Activity {

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.test);
    }
}
