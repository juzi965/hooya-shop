package top.hooya.shop.common.utils;

import java.util.UUID;

/**
 * @author juzi9
 * @date 2020-03-04 15:41
 */
public class MyUtils {
    public static String UUID(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }
}
