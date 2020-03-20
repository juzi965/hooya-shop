package top.hooya.shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.hooya.shop.common.utils.PropertiesUtil;
import top.hooya.shop.dao.SysMenuDAO;
import top.hooya.shop.pojo.SysMenu;
import top.hooya.shop.pojo.SysMenuExample;
import top.hooya.shop.pojo.SysMenuExample.Criteria;
import top.hooya.shop.pojo.extend.SysMenuExtend;
import top.hooya.shop.service.SysMenuService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author juzi9
 * @date 2020-03-17 12:41
 */
@Service
public class SysMenuServiceImpl implements SysMenuService {

    @Autowired
    private SysMenuDAO sysMenuDAO;
    @Override
    public List<SysMenuExtend> getMenu(String keyWord) {
        SysMenuExample example = new SysMenuExample();
        if ("all".equals(keyWord)){
            example.createCriteria().andDelFlagNotEqualTo(PropertiesUtil.DEL_FLAG);
        }else{
            example.createCriteria().andDelFlagNotEqualTo(PropertiesUtil.DEL_FLAG).andMenuNameLike("%"+keyWord+"%");;
        }
        List<SysMenu> sysMenuList =  sysMenuDAO.selectByExample(example);
        List<SysMenuExtend> menuTree = new ArrayList<>();
        for (SysMenu sysMenu:sysMenuList){
            if (sysMenu.getParentId()==-1){
                SysMenuExtend sysMenuExtend = new SysMenuExtend(sysMenu);
                sysMenuExtend.setChildren(getChildren(sysMenuExtend.getId(),sysMenuList));
                menuTree.add(sysMenuExtend);
            }
        }
        return menuTree;
    }

    @Override
    public int deleteMenuById(Integer id) {
        SysMenu sysMenu = new SysMenu();
        sysMenu.setDelFlag(PropertiesUtil.DEL_FLAG);
        SysMenuExample sysMenuExample = new SysMenuExample();
        Criteria criteriaById = sysMenuExample.createCriteria();
        Criteria criteriaByParentId = sysMenuExample.createCriteria();
        criteriaById.andIdEqualTo(id);
        criteriaByParentId.andParentIdEqualTo(id);
        sysMenuExample.or(criteriaByParentId);
        return sysMenuDAO.updateByExampleSelective(sysMenu,sysMenuExample);
    }

    @Override
    public int saveMenu(SysMenu sysMenu) {
        int count;
        if (sysMenu.getId()==null){
            count= sysMenuDAO.insertSelective(sysMenu);
        }else{
            count= sysMenuDAO.updateByPrimaryKeySelective(sysMenu);
        }
        return count;
    }

    private List<SysMenu> getChildren(Integer parentId,List<SysMenu> sysMenuList){
        List<SysMenu> secondMenuList = new ArrayList<>();
        for (SysMenu sysMenu: sysMenuList){
            if (sysMenu.getParentId().equals(parentId)){
                secondMenuList.add(sysMenu);
            }
        }
        return secondMenuList;
    }
}
