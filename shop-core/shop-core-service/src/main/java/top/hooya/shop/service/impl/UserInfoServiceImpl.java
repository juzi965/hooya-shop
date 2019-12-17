package top.hooya.shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.hooya.shop.common.pojo.LoginVo;
import top.hooya.shop.dao.UserInfoDAO;
import top.hooya.shop.pojo.UserInfo;
import top.hooya.shop.pojo.UserInfoExample;
import top.hooya.shop.service.UserInfoService;

import java.util.List;

@Service
public class UserInfoServiceImpl implements UserInfoService {

	@Autowired
	private UserInfoDAO userInfoDAO;

	@Override
	public UserInfo getUserInfoByLoginVo(LoginVo vo) {
		// 邮箱登录
		UserInfoExample exampleEmail = new UserInfoExample();
		exampleEmail.createCriteria().andEmailEqualTo(vo.getAccountName()).andPasswordEqualTo(vo.getPassword());
		List<UserInfo> userInfosByEmail = userInfoDAO.selectByExample(exampleEmail);
		if (userInfosByEmail!=null&&!userInfosByEmail.isEmpty()){
			return userInfosByEmail.get(0);
		}
		// 手机号登录
		UserInfoExample examplePhoneNum = new UserInfoExample();
		examplePhoneNum.createCriteria().andPhoneNumEqualTo(vo.getAccountName()).andPasswordEqualTo(vo.getPassword());
		List<UserInfo> userInfosByPhoneNum = userInfoDAO.selectByExample(examplePhoneNum);
		if (userInfosByPhoneNum!=null&&!userInfosByPhoneNum.isEmpty()){
			return userInfosByPhoneNum.get(0);
		}
		return null;
	}
}
