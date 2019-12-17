package top.hooya.shop.service;

import top.hooya.shop.common.pojo.LoginVo;
import top.hooya.shop.pojo.UserInfo;

public interface UserInfoService {
	UserInfo getUserInfoByLoginVo(LoginVo vo);
}
