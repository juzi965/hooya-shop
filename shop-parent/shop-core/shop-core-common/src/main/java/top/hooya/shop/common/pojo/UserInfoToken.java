package top.hooya.shop.common.pojo;

import top.hooya.shop.pojo.SysMenu;
import top.hooya.shop.pojo.UserInfo;
import top.hooya.shop.pojo.extend.SysMenuExtend;

import java.util.List;

/**
 * @author juzi9
 * @date 2020-03-09 16:31
 */
public class UserInfoToken extends UserInfo {

    private String token;
    private List<SysMenuExtend>  menuTree;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


    public List<SysMenuExtend> getMenuTree() {
        return menuTree;
    }

    public void setMenuTree(List<SysMenuExtend> menuTree) {
        this.menuTree = menuTree;
    }

    public UserInfoToken(UserInfo userInfo) {
        super();
        super.setId(userInfo.getId());
        super.setUserName(userInfo.getUserName());
        super.setBirthday(userInfo.getBirthday());
        super.setEmail(userInfo.getEmail());
        super.setPhoneNum(userInfo.getPhoneNum());
        super.setGender(userInfo.getGender());
        super.setCreateTime(userInfo.getCreateTime());
        super.setDelFlag(userInfo.getDelFlag());
    }
}
