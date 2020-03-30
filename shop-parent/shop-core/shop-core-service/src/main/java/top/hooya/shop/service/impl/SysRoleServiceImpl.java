package top.hooya.shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.hooya.shop.common.pojo.SysRoleVo;
import top.hooya.shop.common.utils.PropertiesUtil;
import top.hooya.shop.dao.SysRoleDAO;
import top.hooya.shop.dao.SysRoleMenuDAO;
import top.hooya.shop.dao.extend.SysRoleExtendDAO;
import top.hooya.shop.pojo.SysRole;
import top.hooya.shop.pojo.SysRoleExample;
import top.hooya.shop.pojo.SysRoleMenu;
import top.hooya.shop.pojo.SysRoleMenuExample;
import top.hooya.shop.service.SysRoleService;

import java.util.Date;
import java.util.List;

/**
 * @author juzi9
 * @date 2020-03-17 12:42
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleDAO sysRoleDAO;

    @Autowired
    private SysRoleExtendDAO sysRoleExtendDAO;

    @Autowired
    private SysRoleMenuDAO sysRoleMenuDAO;

    @Override
    public List<SysRoleVo> getRole(String keyWord) {
        if ("all".equals(keyWord)) {
            keyWord = null;
        }

        return sysRoleExtendDAO.selectRoleMenu(keyWord);
    }

    @Override
    public int saveRoleMenu(SysRoleVo sysRoleVo) {
        SysRoleMenu sysRoleMenu = new SysRoleMenu();
        SysRole sysRole = new SysRole();
        sysRole.setRoleName(sysRoleVo.getRoleName());
        int count;
        if (sysRoleVo.getId() != null) {
            sysRole.setId(sysRoleVo.getId());
            sysRole.setDelFlag(sysRoleVo.getDelFlag());
            count = sysRoleDAO.updateByPrimaryKeySelective(sysRole);
            sysRoleMenu.setRoleId(sysRoleVo.getId());
            SysRoleMenuExample sysRoleMenuExample = new SysRoleMenuExample();
            sysRoleMenuExample.createCriteria().andRoleIdEqualTo(sysRoleVo.getId());
            sysRoleMenuDAO.deleteByExample(sysRoleMenuExample);
            if (!PropertiesUtil.DEL_FLAG.equals(sysRoleVo.getDelFlag()) && sysRoleVo.getMenuIds() != null) {
                for (int i : sysRoleVo.getMenuIds()) {
                    sysRoleMenu.setMenuId(i);
                    sysRoleMenu.setCreateTime(new Date());
                    sysRoleMenuDAO.insertSelective(sysRoleMenu);
                }
            }

        } else {
            count = sysRoleDAO.insertSelective(sysRole);
            sysRoleMenu.setRoleId(sysRole.getId());
            if (sysRoleVo.getMenuIds() != null) {
                for (int i : sysRoleVo.getMenuIds()) {
                    sysRoleMenu.setMenuId(i);
                    sysRoleMenu.setCreateTime(new Date());
                    sysRoleMenuDAO.insertSelective(sysRoleMenu);
                }
            }
        }
        return count;
    }
}
