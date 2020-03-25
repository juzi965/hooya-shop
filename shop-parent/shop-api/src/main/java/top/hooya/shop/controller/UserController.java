package top.hooya.shop.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.hooya.shop.common.pojo.*;
import top.hooya.shop.common.result.Result;
import top.hooya.shop.common.utils.JwtUtil;
import top.hooya.shop.pojo.UserInfo;
import top.hooya.shop.pojo.extend.UserInfoExtend;
import top.hooya.shop.service.UserInfoService;

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
	public Result login(@Validated LoginVo vo){
		UserInfo userInfo = userInfoService.getUserInfoByLoginVo(vo);
		if (userInfo==null){
			return Result.error("账号或密码错误");
		}
		UserInfoToken userInfoToken = new UserInfoToken(userInfo);
		userInfoToken.setToken(JwtUtil.getToken(userInfo));
		userInfoToken.setMenuTree(userInfoService.getMenuListByUserId(userInfo.getId()));
		return Result.success(userInfoToken);
	}

	@UserLoginToken
	@PostMapping("/saveUserInfo")
    public Result saveUserInfo(@Validated UserInfo userInfo){
        int count = userInfoService.saveUserInfo(userInfo);
        if(count>0){
        	UserInfo userInfoTemp = userInfoService.getUserInfoById(userInfo.getId());
			UserInfoToken userInfoToken = new UserInfoToken(userInfoTemp);
			userInfoToken.setToken(JwtUtil.getToken(userInfoTemp));
			userInfoToken.setMenuTree(userInfoService.getMenuListByUserId(userInfoTemp.getId()));
			return Result.success(userInfoToken);
		}
         return Result.error("保存信息失败");
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

		PageHelper.startPage(pageNum,pageSize);
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

	@PassToken
	@PostMapping("/register")
	public Result register(UserInfo userInfo){
		int count  = userInfoService.register(userInfo);
		return count>0?Result.success(count):Result.error("注册失败");
	}

	@PassToken
	@PostMapping("/validateUserName")
	public Result validateUserName(Integer id,String userName){
		Long count =  userInfoService.validate(id,userName);
		return count>0?Result.error("用户名已被注册"):Result.success("该用户名可以使用");
	}
	@PassToken
	@PostMapping("/validateEmail")
	public Result validateEmail(Integer id,String email){
		Long count =  userInfoService.validate(id,email);
		return count>0?Result.error("邮箱已被注册"):Result.success("该邮箱可以使用");
	}
	@PassToken
	@PostMapping("/validatePhone")
	public Result validatePhone(Integer id,String phoneNum){
		Long count =  userInfoService.validate(id,phoneNum);
		return count>0?Result.error("手机号已被注册"):Result.success("该手机号可以使用");
	}


}
