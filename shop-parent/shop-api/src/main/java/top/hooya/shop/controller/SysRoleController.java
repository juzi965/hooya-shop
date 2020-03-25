package top.hooya.shop.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.hooya.shop.common.pojo.SysRoleVo;
import top.hooya.shop.common.pojo.UserLoginToken;
import top.hooya.shop.common.result.Result;
import top.hooya.shop.service.SysRoleService;


import java.util.List;

/**
 * @author juzi9
 * @date 2020-03-17 12:39
 */
@RestController
@RequestMapping("/api/role")
public class SysRoleController {
    @Autowired
    private SysRoleService sysRoleService;

    @UserLoginToken
    @GetMapping("/{keyWord}/{pageNum}/{pageSize}")
    public Result getRole(@PathVariable("keyWord") String keyWord, @PathVariable("pageNum") Integer pageNum, @PathVariable("pageSize") Integer pageSize){

        PageHelper.startPage(pageNum,pageSize);
        List<SysRoleVo> sysRoleList = sysRoleService.getRole(keyWord);
        PageInfo<SysRoleVo> pageInfo = new PageInfo<>(sysRoleList);

        return Result.success(pageInfo);
    }
    @UserLoginToken
    @PostMapping("/save")
    public Result saveRoleMenu(SysRoleVo sysRoleVo){

        int count = sysRoleService.saveRoleMenu(sysRoleVo);

        return count>0?Result.success(count):Result.error("操作失败");
    }
}
