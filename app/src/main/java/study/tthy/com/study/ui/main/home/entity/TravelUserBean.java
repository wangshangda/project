package study.tthy.com.study.ui.main.home.entity;

import java.util.List;

/**
 * Created by Administrator on 2019/3/26.
 */

public class TravelUserBean {
    private String head;
    private String name;
    private String date;
    private String evaluation;
    private int watchNum;
    private int evaluationNum;
    private List<String> ImgUrl;

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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(String evaluation) {
        this.evaluation = evaluation;
    }

    public int getWatchNum() {
        return watchNum;
    }

    public void setWatchNum(int watchNum) {
        this.watchNum = watchNum;
    }

    public int getEvaluationNum() {
        return evaluationNum;
    }

    public void setEvaluationNum(int evaluationNum) {
        this.evaluationNum = evaluationNum;
    }

    public List<String> getImgUrl() {
        return ImgUrl;
    }

    public void setImgUrl(List<String> imgUrl) {
        ImgUrl = imgUrl;
    }

    public TravelUserBean(String head, String name, String date, String evaluation, int watchNum, int evaluationNum) {
        this.head = head;
        this.name = name;
        this.date = date;
        this.evaluation = evaluation;
        this.watchNum = watchNum;
        this.evaluationNum = evaluationNum;
    }
}
