package top.hooya.shop.pojo.extend;

import top.hooya.shop.pojo.SysMenu;
import top.hooya.shop.pojo.SysRole;

import java.util.List;

/**
 * @author juzi9
 * @date 2020-03-18 10:43
 */
public class SysRoleExtend extends SysRole {
    private List<SysMenu> menus;

    public List<SysMenu> getMenus() {
        return menus;
    }

    public void setMenus(List<SysMenu> menus) {
        this.menus = menus;
    }
}
