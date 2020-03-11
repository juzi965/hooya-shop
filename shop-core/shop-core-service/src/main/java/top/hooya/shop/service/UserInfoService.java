package top.hooya.shop.service;

import top.hooya.shop.common.pojo.LoginVo;
import top.hooya.shop.common.pojo.PasswordVo;
import top.hooya.shop.pojo.UserInfo;

public interface UserInfoService {
	UserInfo getUserInfoByLoginVo(LoginVo vo);

	int saveUserInfo(UserInfo userInfo);

	int changePasswordById(PasswordVo vo);

	UserInfo getUserInfoById(Integer id);
}
