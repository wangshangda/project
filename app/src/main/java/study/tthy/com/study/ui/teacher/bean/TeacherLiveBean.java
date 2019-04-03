package study.tthy.com.study.ui.teacher.bean;

/**
 * Created by Administrator on 2019/3/29.
 */

public class TeacherLiveBean {
    private String imgUrl;
    private String head;
    private int hotNum;
    private String title;
    private String date;
    private int status;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public TeacherLiveBean(String imgUrl, String title,String head, int hotNum, String date, int status) {
        this.imgUrl = imgUrl;
        this.head = head;
        this.title=title;
        this.hotNum = hotNum;
        this.date = date;
        this.status = status;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public int getHotNum() {
        return hotNum;
    }

    public void setHotNum(int hotNum) {
        this.hotNum = hotNum;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
