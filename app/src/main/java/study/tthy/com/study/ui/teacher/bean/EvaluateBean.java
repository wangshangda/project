package study.tthy.com.study.ui.teacher.bean;

import java.util.List;

/**
 * Created by Administrator on 2019/3/28.
 */

public class EvaluateBean {
    private String head;
    private String name;
    private String content;
    private String date;
    private String replay;
    private int authent;//0是否认证
    private List<String> pic;
    private float rating;


    public EvaluateBean(String head, String name, String content, String date, String replay, float rating) {
        this.head = head;
        this.name = name;
        this.content = content;
        this.date = date;
        this.replay = replay;
        this.rating = rating;
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

    public String getReplay() {
        return replay;
    }

    public void setReplay(String replay) {
        this.replay = replay;
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

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
