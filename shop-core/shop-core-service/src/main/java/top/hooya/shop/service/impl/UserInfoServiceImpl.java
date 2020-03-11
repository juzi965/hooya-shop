package top.hooya.shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import top.hooya.shop.common.pojo.LoginVo;
import top.hooya.shop.common.pojo.PasswordVo;
import top.hooya.shop.dao.UserInfoDAO;
import top.hooya.shop.pojo.UserInfo;
import top.hooya.shop.pojo.UserInfoExample;
import top.hooya.shop.pojo.UserInfoExample.Criteria;
import top.hooya.shop.service.UserInfoService;

import java.util.List;

@Service
public class UserInfoServiceImpl implements UserInfoService {

	@Autowired
	private UserInfoDAO userInfoDAO;

	@Override
	public UserInfo getUserInfoByLoginVo(LoginVo vo) {

		UserInfoExample example = new UserInfoExample();
		Criteria criteriaByEmail = example.createCriteria();	/*邮箱登录*/
		Criteria criteriaByPhoneNum = example.createCriteria();	/*手机号登录*/
		vo.setPassword(DigestUtils.md5DigestAsHex(vo.getPassword().getBytes())); /*对密码进行加密*/
		criteriaByEmail.andEmailEqualTo(vo.getAccountName()).andPasswordEqualTo(vo.getPassword());
		criteriaByPhoneNum.andPhoneNumEqualTo(vo.getAccountName()).andPasswordEqualTo(vo.getPassword());
		example.or(criteriaByPhoneNum);
		List<UserInfo> userInfos = userInfoDAO.selectByExample(example);
		if (userInfos!=null&&!userInfos.isEmpty()){
			return userInfos.get(0);
		}

		return null;
	}

	@Override
	public int saveUserInfo(UserInfo userInfo) {
		return userInfoDAO.updateByPrimaryKeySelective(userInfo);
	}

	@Override
	public int changePasswordById(PasswordVo vo) {
		UserInfoExample example = new UserInfoExample();
		vo.setOldPassword(DigestUtils.md5DigestAsHex(vo.getOldPassword().getBytes()));
		vo.setNewPassword(DigestUtils.md5DigestAsHex(vo.getNewPassword().getBytes()));
		example.createCriteria().andIdEqualTo(vo.getUserId()).andPasswordEqualTo(vo.getOldPassword());
		UserInfo userInfo = new UserInfo();
		userInfo.setPassword(vo.getNewPassword());
		return userInfoDAO.updateByExampleSelective(userInfo,example);
	}

	@Override
	public UserInfo getUserInfoById(Integer id) {
		return userInfoDAO.selectByPrimaryKey(id);
	}
}
