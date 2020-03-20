package top.hooya.shop.pojo.extend;

import top.hooya.shop.pojo.SysMenu;

import java.util.List;

/**
 * @author juzi9
 * @date 2020-03-17 13:06
 */
public class SysMenuExtend extends SysMenu {
    private List<SysMenu> children;


    public SysMenuExtend(SysMenu sysMenu){
        super();
        super.setId(sysMenu.getId());
        super.setMenuName(sysMenu.getMenuName());
        super.setIcon(sysMenu.getIcon());
        super.setPath(sysMenu.getPath());
        super.setParentId(sysMenu.getParentId());
        super.setCreateTime(sysMenu.getCreateTime());
        super.setDelFlag(sysMenu.getDelFlag());
    }
    public List<SysMenu> getChildren() {
        return children;
    }

    public void setChildren(List<SysMenu> children) {
        this.children = children;
    }
}
