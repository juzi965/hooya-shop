package top.hooya.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.hooya.shop.common.pojo.LoginVo;
import top.hooya.shop.common.result.Result;
import top.hooya.shop.common.utils.PropertiesUtil;
import top.hooya.shop.pojo.UserInfo;
import top.hooya.shop.service.UserInfoService;

@RequestMapping("/api/user")
@RestController
public class UserController {

	@Autowired
	private UserInfoService userInfoService;

	@PostMapping("/login")
	public Result login(LoginVo vo){
		Result result = new Result();
		UserInfo userInfo = userInfoService.getUserInfoByLoginVo(vo);
		if (userInfo!=null){
			result.setCode(PropertiesUtil.SUCCESS_CODE);
			result.setMessage(PropertiesUtil.SUCCESS_MSG);
			result.setData(userInfo);
		}else{
			result.setCode(PropertiesUtil.OTHER_ERROR_CODE);
			result.setMessage("账号或密码错误");
		}
		return result;
	}

}
