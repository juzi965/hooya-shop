package top.hooya.shop.common.pojo;

/**
 * @author juzi9
 * @date 2020-03-18 12:52
 */
public class RoleUserVo {
    private Integer roleId;
    private Integer[] userIds;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer[] getUserIds() {
        return userIds;
    }

    public void setUserIds(Integer[] userIds) {
        this.userIds = userIds;
    }
}
