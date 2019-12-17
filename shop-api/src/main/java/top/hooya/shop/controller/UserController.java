package top.hooya.shop.controller;

import org.springframework.web.bind.annotation.*;
import top.hooya.shop.common.pojo.LoginVo;
import top.hooya.shop.common.result.Result;
import top.hooya.shop.common.utils.PropertiesUtil;

@RequestMapping("/api/user")
@RestController
public class UserController {

	@PostMapping("/login")
	public Result login(@RequestParam(required = false) LoginVo vo){

		return new Result(PropertiesUtil.SUCCESS_CODE,null);
	}

}
