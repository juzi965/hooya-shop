package top.hooya.shop.service;

import top.hooya.shop.pojo.SysMenu;
import top.hooya.shop.pojo.extend.SysMenuExtend;

import java.util.List;

/**
 * @author juzi9
 * @date 2020-03-17 12:41
 */
public interface SysMenuService {
    List<SysMenuExtend> getMenu(String keyWord);

    int deleteMenuById(Integer id);

    int saveMenu(SysMenu sysMenu);
}
