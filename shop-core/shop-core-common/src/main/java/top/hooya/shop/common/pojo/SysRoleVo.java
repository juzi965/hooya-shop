package top.hooya.shop.common.pojo;

import top.hooya.shop.pojo.SysRole;

/**
 * @author juzi9
 * @date 2020-03-17 22:58
 */
public class SysRoleVo extends SysRole {
    private int[] menuIds;

    public int[] getMenuIds() {
        return menuIds;
    }

    public void setMenuIds(int[] menuIds) {
        this.menuIds = menuIds;
    }
}
