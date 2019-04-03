package study.tthy.com.study.ui.main.home.entity;

/**
 * Created by Administrator on 2019/3/22.
 */

public class HomeMenuBean {
    private String title;
    private String imgUrl;
    //0本地，1网络
    private int imgType;
    private int menuID;

    public int getMenuID() {
        return menuID;
    }

    public void setMenuID(int menuID) {
        this.menuID = menuID;
    }

    public int getImgType() {
        return imgType;
    }

    public void setImgType(int imgType) {
        this.imgType = imgType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public HomeMenuBean(String title, String imgUrl,int menuID) {
        this.title = title;
        this.imgUrl = imgUrl;
        this.menuID=menuID;
    }

    public HomeMenuBean() {
    }
}
