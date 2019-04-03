package study.tthy.com.study.help;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.scwang.smartrefresh.layout.util.DensityUtil;

import java.io.File;
import java.security.MessageDigest;

import study.tthy.com.study.R;

import static com.bumptech.glide.load.resource.bitmap.VideoDecoder.FRAME_OPTION;

/**
 * 参考设置：http://www.tuicool.com/articles/3Af6Zby
 * DiskCacheStrategy.NONE:什么都不缓存
 * DiskCacheStrategy.SOURCE:仅缓存原图(全分辨率的图片)
 * DiskCacheStrategy.RESULT:仅缓存最终的图片,即修改了尺寸或者转换后的图片
 * DiskCacheStrategy.ALL:缓存所有版本的图片,默认模式
 * <p>
 * url =    url.replaceAll("http:/", "http://");
 */
public class ImageLoaderHelper {  //待封装

    public static int IMG_LOADING = R.mipmap.ic_image_loading;
    public static int IMG_ERROR = R.mipmap.ic_empty_picture;

    private static volatile ImageLoaderHelper instance;

    private ImageLoaderHelper() {

    }

    public static ImageLoaderHelper getInstance() {

        if (instance == null) {
            synchronized (ImageLoaderHelper.class) {
                if (instance == null) {
                    instance = new ImageLoaderHelper();
                }
            }
        }

        return instance;
    }

    private int optionsInit(int flag) {
        int sourceId = 0;
        if (flag == 0) {
            sourceId = R.mipmap.ic_image_loading;
        }
        return sourceId;
    }

    public void load(Context context, String url, ImageView iv) {

        if (iv != null && context != null && url != null) {
            RequestOptions options = new RequestOptions()
                    .placeholder(optionsInit(0))  //加载中显示的图片
                    .centerCrop()            //图像则位于视图的中央
                    // .override(1090, 1090*3/4)
                    .error(IMG_ERROR) //加载失败时显示的图片centerCrop().
                    .diskCacheStrategy(DiskCacheStrategy.ALL);  //图片缓存
            Glide.with(context).load(url).apply(options).into(iv);
        }

    }

    //头像

    public void loadHead(Context context, String url, ImageView iv) {
        Glide.with(context).load(url).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(iv);
    }

    //矩形
    public void LoadCircleRet(Context context, String url, ImageView iv, int roundingRadius) {
        RoundedCorners roundedCorners = new RoundedCorners(DensityUtil.dp2px(roundingRadius));
        //通过RequestOptions扩展功能,override:采样率,因为ImageView就这么大,可以压缩图片,降低内存消耗
        RequestOptions options = RequestOptions.bitmapTransform(roundedCorners).override(300, 300);
        Glide.with(context).load(url).apply(options).into(iv);
    }


    public void loadV2(Context context, String url, ImageView iv) {


        if (iv != null && context != null && url != null) {
            RequestOptions options = new RequestOptions()
                    .placeholder(IMG_LOADING)
                    .diskCacheStrategy(DiskCacheStrategy.ALL);
            Glide.with(context).load(url).apply(options).into(iv);
        }

    }

    public void load(Context context, Uri url, ImageView iv) {


        if (iv != null) {
            RequestOptions options = new RequestOptions()
                    .placeholder(IMG_LOADING)
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL);
            Glide.with(context).load(url).apply(options).into(iv);
        }

    }

    public void load(Context context, String url, ImageView iv, int radius) {

        if (iv != null) {
            RequestOptions options = new RequestOptions()
                    .placeholder(IMG_LOADING)
                    .transform(new GlideRoundTransform(context))
//                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL);

            Glide.with(context)
                    .load(url)
                    .transition(new DrawableTransitionOptions().crossFade(200))
                    .apply(options).into(iv);
        }

    }

    public void load(Context context, File fileName, ImageView iv) {

        if (iv != null) {
            RequestOptions options = new RequestOptions()
                    .placeholder(IMG_LOADING)
                    .centerCrop()//centerCrop是利用图片图填充ImageView设置的大小
                    .diskCacheStrategy(DiskCacheStrategy.ALL);
            Glide.with(context).load(fileName).apply(options).into(iv);
        }

    }

    /**
     * 加载资源图片
     *
     * @param context
     * @param resourceId
     * @param iv
     */
    public void load(Context context, Integer resourceId, ImageView iv) {

        if (iv != null) {
            RequestOptions options = new RequestOptions()
                    .placeholder(IMG_LOADING)
                    .diskCacheStrategy(DiskCacheStrategy.ALL);
            Glide.with(context).load(resourceId).apply(options).into(iv);
        }

    }


    /**
     * 加载视频第一帧图片
     * @param context
     * @param uri
     * @param imageView
     * @param frameTimeMicros
     */
    public void loadVideoScreenshot(final Context context, String uri, ImageView imageView, long frameTimeMicros) {
        RequestOptions requestOptions = RequestOptions.frameOf(frameTimeMicros);
        requestOptions.set(FRAME_OPTION, MediaMetadataRetriever.OPTION_CLOSEST);
        requestOptions.transform(new BitmapTransformation() {
            @Override
            protected Bitmap transform(@NonNull BitmapPool pool, @NonNull Bitmap toTransform, int outWidth, int outHeight) {
                return toTransform;
            }

            @Override
            public void updateDiskCacheKey(MessageDigest messageDigest) {
                try {
                    messageDigest.update((context.getPackageName() + "RotateTransform").getBytes("utf-8"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        Glide.with(context).load(uri).apply(requestOptions).into(imageView);
    }


}
