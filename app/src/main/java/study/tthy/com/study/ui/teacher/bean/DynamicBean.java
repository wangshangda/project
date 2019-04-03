package study.tthy.com.study.ui.teacher.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

/**
 * Created by Administrator on 2019/3/28.
 */

public class DynamicBean implements MultiItemEntity {
    public static final int IMG = 1;
    public static final int VIDEO = 2;
    private String head;
    private int itemType;//1图片，2视频
    private String name;
    private String content;
    private String date;
    private int authent;//0是否认证
    private List<String> pic;
    private int forwardNum;
    private int evaluationNum;
    private int praiseNum;
    private int isPrise;
    private String videoUrl;


    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public DynamicBean(int itemType,String head, String name, String content, String date, int authent, int forwardNum, int evaluationNum, int praiseNum) {
        this.head = head;
        this.name = name;
        this.content = content;
        this.date = date;
        this.authent = authent;
        this.forwardNum = forwardNum;
        this.evaluationNum = evaluationNum;
        this.praiseNum = praiseNum;
        this.itemType = itemType;
    }

    public DynamicBean() {

    }

    public int getIsPrise() {
        return isPrise;
    }

    public void setIsPrise(int isPrise) {
        this.isPrise = isPrise;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getAuthent() {
        return authent;
    }

    public void setAuthent(int authent) {
        this.authent = authent;
    }

    public List<String> getPic() {
        return pic;
    }

    public void setPic(List<String> pic) {
        this.pic = pic;
    }

    public int getForwardNum() {
        return forwardNum;
    }

    public void setForwardNum(int forwardNum) {
        this.forwardNum = forwardNum;
    }

    public int getEvaluationNum() {
        return evaluationNum;
    }

    public void setEvaluationNum(int evaluationNum) {
        this.evaluationNum = evaluationNum;
    }

    public int getPraiseNum() {
        return praiseNum;
    }

    public void setPraiseNum(int praiseNum) {
        this.praiseNum = praiseNum;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    @Override
    public int getItemType() {
        return itemType;
    }
}
