package top.hooya.shop.service;

import top.hooya.shop.common.pojo.SysRoleVo;
import top.hooya.shop.pojo.SysRole;

import java.util.List;

/**
 * @author juzi9
 * @date 2020-03-17 12:42
 */
public interface SysRoleService {
    List<SysRoleVo> getRole(String keyWord);

    int saveRoleMenu(SysRoleVo sysRoleVo);
}
