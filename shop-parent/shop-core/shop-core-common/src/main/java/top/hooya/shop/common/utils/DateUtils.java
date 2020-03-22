package top.hooya.shop.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author juzi9
 * @date 2020-03-14 11:50
 */
public class DateUtils {

    /**
     * 把日期转换为字符串
     */
    public static String dateToString(Date date, String format) {
        String result = "";
        SimpleDateFormat formater = new SimpleDateFormat(format);
        try {
            result = formater.format(date);
        } catch (Exception e) {
        }
        return result;
    }

    /**
     * 获取当前时间的指定格式
     */
    public static String getCurrDate(String format) {
        return dateToString(new Date(), format);
    }

}
