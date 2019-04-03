package study.tthy.com.study.ui.teacher.bean;

import com.luck.picture.lib.entity.LocalMedia;

/**
 * Created by Administrator on 2019/3/29.
 */

public class EvaluatePicBean extends LocalMedia {
    private int type;//1正常图片
    private String url;

    public EvaluatePicBean(int type, String url) {
        this.type = type;
        this.url = url;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
