package top.hooya.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.hooya.shop.common.pojo.UserLoginToken;
import top.hooya.shop.common.result.Result;
import top.hooya.shop.pojo.SysMenu;
import top.hooya.shop.pojo.extend.SysMenuExtend;
import top.hooya.shop.service.SysMenuService;

import java.util.List;

/**
 * @author juzi9
 * @date 2020-03-17 12:39
 */
@RestController
@RequestMapping("/api/menu")
public class SysMenuController {
    @Autowired
    private SysMenuService sysMenuService;

    @UserLoginToken
    @GetMapping("/{keyWord}")
    public Result getMenu(@PathVariable("keyWord") String keyWord){
        List<SysMenuExtend> sysMenuList = sysMenuService.getMenu(keyWord);
        return Result.success(sysMenuList);
    }
    @UserLoginToken
    @PostMapping("/delete")
    public Result deleteMenu(Integer id){
        int count = sysMenuService.deleteMenuById(id);
        return count>0?Result.success(count):Result.error("删除失败");
    }
    @UserLoginToken
    @PostMapping("/save")
    public Result saveMenu(SysMenu sysMenu){
        int count = sysMenuService.saveMenu(sysMenu);
        return count>0?Result.success(count):Result.error("操作失败");
    }
}
