package study.tthy.com.study.ui.teacher.bean;

/**
 * Created by Administrator on 2019/3/27.
 */

public class TeacherCategoryBean {
    private int selectId;
    private String selectName;

    public int getSelectId() {
        return selectId;
    }

    public void setSelectId(int selectId) {
        this.selectId = selectId;
    }

    public String getSelectName() {
        return selectName;
    }

    public void setSelectName(String selectName) {
        this.selectName = selectName;
    }

    public TeacherCategoryBean(int selectId, String selectName) {
        this.selectId = selectId;

        this.selectName = selectName;
    }
}
