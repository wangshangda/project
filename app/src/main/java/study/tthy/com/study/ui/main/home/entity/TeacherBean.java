package study.tthy.com.study.ui.main.home.entity;

/**
 * Created by Administrator on 2019/3/25.
 */

public class TeacherBean {
    private String head;
    private String name;
    private float rating;
    private int isChecked;
    private String evaluation;

    public TeacherBean(String head, String name, float rating, int isChecked, String evaluation) {
        this.head = head;
        this.name = name;
        this.rating = rating;
        this.isChecked = isChecked;
        this.evaluation = evaluation;
    }

    public TeacherBean() {

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

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(int isChecked) {
        this.isChecked = isChecked;
    }

    public String getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(String evaluation) {
        this.evaluation = evaluation;
    }
}
