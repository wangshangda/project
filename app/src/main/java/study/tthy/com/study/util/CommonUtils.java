package study.tthy.com.study.util;

/**
 * Created by Administrator on 2019/3/26.
 */

public class CommonUtils {

    //超过1w的转成w
    public static String getShowNumbers(int i) {
        String s = "";
        if (i < 10000) {
            return String.valueOf(i);
        }
        int w = 0;
        int y = 0;
        y = i % 10000;
        w = i / 10000;
        try {
            String l = String.valueOf(i % 10000);
            y = Integer.parseInt(l.substring(0, 1));
        } catch (Exception e) {
            return w + "w";
        }
        if (y > 0) {
            s = w + "." + y + "w";
        } else {
            s = w + "w";
        }
        return s;
    }

}
