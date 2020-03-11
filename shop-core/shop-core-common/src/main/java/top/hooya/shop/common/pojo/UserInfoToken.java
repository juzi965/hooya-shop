package top.hooya.shop.common.pojo;

import top.hooya.shop.pojo.UserInfo;

/**
 * @author juzi9
 * @date 2020-03-09 16:31
 */
public class UserInfoToken extends UserInfo {

    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
