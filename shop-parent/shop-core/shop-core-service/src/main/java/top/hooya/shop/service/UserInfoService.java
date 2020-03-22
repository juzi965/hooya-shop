package top.hooya.shop.service;

import top.hooya.shop.common.pojo.LoginVo;
import top.hooya.shop.common.pojo.PasswordVo;
import top.hooya.shop.common.pojo.RoleUserVo;
import top.hooya.shop.pojo.SysMenu;
import top.hooya.shop.pojo.UserInfo;
import top.hooya.shop.pojo.extend.SysMenuExtend;
import top.hooya.shop.pojo.extend.UserInfoExtend;

import java.util.List;

public interface UserInfoService {
	UserInfo getUserInfoByLoginVo(LoginVo vo);

	int saveUserInfo(UserInfo userInfo);

	int changePasswordById(PasswordVo vo);

	UserInfo getUserInfoById(Integer id);

	List<UserInfoExtend> getUser(String keyWord);

	int resetPasswordByuserId(Integer userId);

    int allocationRole(RoleUserVo vo);

	int revokeRole(RoleUserVo vo);

    List<SysMenuExtend> getMenuListByUserId(Integer userId);

    int register(UserInfo userInfo);

	Long validate(Integer id,String userName);
}
