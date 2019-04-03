package study.tthy.com.study.ui.teacher.bean;

/**
 * Created by Administrator on 2019/3/29.
 */

public class RouteBean {
    private String imgUrl;
    private String head;
    private String content;
    private String name;

    public RouteBean(){

    }

    public RouteBean(String imgUrl, String head, String content) {
        this.imgUrl = imgUrl;
        this.head = head;
        this.content = content;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
