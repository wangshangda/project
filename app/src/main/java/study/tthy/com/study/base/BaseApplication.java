package study.tthy.com.study.base;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.support.multidex.MultiDex;

import com.gyf.barlibrary.ImmersionBar;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;

import study.tthy.com.study.BuildConfig;
import study.tthy.com.study.base.baseControl.ActivityControl;
import study.tthy.com.study.widget.head.DefaultHead;

/**
 * Created by Administrator on 2019/3/19.
 */

public class BaseApplication extends Application{
    public static BaseApplication mApplication;
    public static boolean IS_DEBUG = BuildConfig.DEBUG ;
    public static boolean isDebug = true ;
    public static String APP_NAME  ;

    //内存泄露
    private RefWatcher refWatcher;
    //Activity管理
    public ActivityControl mActivityControl;


    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                // layout.setPrimaryColorsId(R
                // .color.colorPrimary, android.R.color.white);//全局设置主题颜色
                return new DefaultHead(context);//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
            }
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                //指定为经典Footer，默认是 BallPulseFooter
                return new ClassicsFooter(context).setDrawableSize(20);
            }
        });
    }



    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        mApplication=this;
        MultiDex.install(this);

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mActivityControl=new ActivityControl();
        initLeakCanary();

        // 初始化Logger工具
        Logger.addLogAdapter(new AndroidLogAdapter(){
            @Override
            public boolean isLoggable(int priority, String tag) {
                return BuildConfig.DEBUG;
            }
        });
        Logger.i("当前是否为debug模式："+IS_DEBUG );
        UMConfigure.init(this,"5a12384aa40fa3551f0001d1"
                ,"umeng", UMConfigure.DEVICE_TYPE_PHONE,"");//58edcfeb310c93091c000be2 5965ee00734be40b580001a0
        initUM();
        UMConfigure.setLogEnabled(true);
    }

    private void initUM() {
        PlatformConfig.setWeixin("wxdc1e388c3822c80b", "3baf1193c85774b3fd9d18447d76cab0");
        //豆瓣RENREN平台目前只能在服务器端配置
        PlatformConfig.setSinaWeibo("3921700954", "04b48b094faeb16683c32669824ebdad","http://sns.whalecloud.com");
        PlatformConfig.setYixin("yxc0614e80c9304c11b0391514d09f13bf");
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
    }

    private void initLeakCanary() {
        //leakcanary初始化
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // 如果是在HeapAnalyzer进程里，则返回，因为该进程是专门用来堆内存分析的。
            return;
        }
        //调用LeakCanary.install()的方法来进行必要的初始化工作，来监听内存泄漏。
        refWatcher = LeakCanary.install(this);
    }

    public static RefWatcher getRefWatcher(Context context) {
        BaseApplication leakApplication = (BaseApplication) context.getApplicationContext();
        return leakApplication.refWatcher;
    }



    public static BaseApplication getApplication(){
        return mApplication;
    }

    public ActivityControl getActivityControl() {
        return mActivityControl;
    }


    @Override
    public void onTerminate() {
        super.onTerminate();
        exitApp();
    }


    public void exitApp() {
        mActivityControl.finishiAll();
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }
    /**
     * 低内存的时候执行
     */
    @Override
    public void onLowMemory() {
        super.onLowMemory();

    }

    // 程序在内存清理的时候执行
    /**
     * 程序在内存清理的时候执行
     */
    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
    }


}
