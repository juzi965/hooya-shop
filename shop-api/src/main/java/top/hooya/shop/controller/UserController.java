package top.hooya.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.hooya.shop.common.pojo.*;
import top.hooya.shop.common.result.Result;
import top.hooya.shop.common.utils.JwtUtil;
import top.hooya.shop.pojo.UserInfo;
import top.hooya.shop.service.UserInfoService;

@RequestMapping("/api/user")
@RestController
public class UserController {

	@Autowired
	private UserInfoService userInfoService;

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
		return Result.success(userInfoToken);
	}

	@GetMapping("")
	@UserLoginToken
	public Result getUserInfo(@CurrentUser UserInfo userInfo){
		return Result.success(userInfo);
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

}
