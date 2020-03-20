package top.hooya.shop.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.hooya.shop.common.pojo.*;
import top.hooya.shop.common.result.Result;
import top.hooya.shop.common.utils.JwtUtil;
import top.hooya.shop.pojo.SysMenu;
import top.hooya.shop.pojo.SysRole;
import top.hooya.shop.pojo.UserInfo;
import top.hooya.shop.pojo.extend.SysMenuExtend;
import top.hooya.shop.pojo.extend.UserInfoExtend;
import top.hooya.shop.service.UserInfoService;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/api/user")
@RestController
public class UserController {

	@Autowired
	private UserInfoService userInfoService;


	@UserLoginToken
	@GetMapping("")
	public Result getUserInfo(@CurrentUser UserInfo userInfo){
		return Result.success(userInfo);
	}

	@PassToken
	@PostMapping("/login")
	public Result login(LoginVo vo){
		UserInfo userInfo = userInfoService.getUserInfoByLoginVo(vo);
		if (userInfo==null){
			return Result.error("账号或密码错误");
		}
		UserInfoToken userInfoToken = new UserInfoToken();
		userInfoToken.setId(userInfo.getId());
		userInfoToken.setUserName(userInfo.getUserName());
		userInfoToken.setBirthday(userInfo.getBirthday());
		userInfoToken.setGender(userInfo.getGender());
		userInfoToken.setEmail(userInfo.getEmail());
		userInfoToken.setPhoneNum(userInfo.getPhoneNum());
		userInfoToken.setCreateTime(userInfo.getCreateTime());
		userInfoToken.setToken(JwtUtil.getToken(userInfo));
		userInfoToken.setMenuTree(userInfoService.getMenuListByUserId(userInfo.getId()));
		return Result.success(userInfoToken);
	}

	@UserLoginToken
	@PostMapping("/saveUserInfo")
    public Result saveUserInfo(UserInfo userInfo){
        int count = userInfoService.saveUserInfo(userInfo);
        return count > 0 ? Result.success(count) : Result.error("保存信息失败");
    }

	@UserLoginToken
	@PostMapping("/changePassword")
	public Result changePassword(@CurrentUser UserInfo userInfo , PasswordVo vo){
		vo.setUserId(userInfo.getId());
		int count = userInfoService.changePasswordById(vo);
		return count > 0 ? Result.success(count) : Result.error("原密码错误，请重新输入");
	}
	@UserLoginToken
	@PostMapping("/resetPwd")
	public Result resetPassword(Integer userId){
		int count = userInfoService.resetPasswordByuserId(userId);
		return count > 0 ? Result.success(count) : Result.error("重置失败");
	}
	@UserLoginToken
	@GetMapping("/{keyWord}/{pageNum}/{pageSize}")
	public Result getUser(@PathVariable("keyWord") String keyWord, @PathVariable("pageNum") Integer pageNum, @PathVariable("pageSize") Integer pageSize){

		PageHelper.startPage(pageNum,pageSize,"id asc");
		List<UserInfoExtend> userInfoExtendList = userInfoService.getUser(keyWord);
		PageInfo<UserInfoExtend> pageInfo = new PageInfo<>(userInfoExtendList);

		return Result.success(pageInfo);
	}
	@UserLoginToken
	@PostMapping("/allocation")
	public Result allocationRole(RoleUserVo vo){
  		int count = userInfoService.allocationRole(vo);
		return count>0?Result.success(count):Result.error("分配失败");
	}
	@UserLoginToken
	@PostMapping("/revoke")
	public Result revokeRole(RoleUserVo vo){
		int count = userInfoService.revokeRole(vo);
		return count>0?Result.success(count):Result.error("撤销失败");
	}

	@PostMapping("/register")
	public Result register(UserInfo userInfo){
		int count  = userInfoService.register(userInfo);
		return count>0?Result.success(count):Result.error("注册失败");
	}


}
