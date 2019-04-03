package study.tthy.com.study.ui.teacher.bean;

import study.tthy.com.study.ui.main.home.entity.TeacherBean;

/**
 * Created by Administrator on 2019/3/26.
 */

public class TeacherResultBean {
    private String head;
    private String name;
    private String evaluation;
    private int ischecked;
    private float rating;
    private int sex;//0男1女
    private int follw;
    private int fans;
    private int isFollw;//0未关注，1已关注
    private int authent;

    public int getFollw() {
        return follw;
    }

    public void setFollw(int follw) {
        this.follw = follw;
    }

    public int getFans() {
        return fans;
    }

    public void setFans(int fans) {
        this.fans = fans;
    }

    public int getIsFollw() {
        return isFollw;
    }

    public void setIsFollw(int isFollw) {
        this.isFollw = isFollw;
    }

    public int getAuthent() {
        return authent;
    }

    public void setAuthent(int authent) {
        this.authent = authent;
    }

    public TeacherResultBean(String head, String name, String evaluation, int ischecked, float rating, int sex) {
        this.head = head;
        this.name = name;
        this.evaluation = evaluation;
        this.ischecked = ischecked;
        this.rating = rating;
        this.sex = sex;
    }
    public TeacherResultBean(){

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

    public String getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(String evaluation) {
        this.evaluation = evaluation;
    }

    public int getIschecked() {
        return ischecked;
    }

    public void setIschecked(int ischecked) {
        this.ischecked = ischecked;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }
}
