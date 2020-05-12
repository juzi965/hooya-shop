package top.hooya.shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import top.hooya.shop.common.pojo.LoginVo;
import top.hooya.shop.common.pojo.PasswordVo;
import top.hooya.shop.common.pojo.RoleUserVo;
import top.hooya.shop.common.utils.PropertiesUtil;
import top.hooya.shop.dao.SysRoleUserDAO;
import top.hooya.shop.dao.UserInfoDAO;
import top.hooya.shop.dao.extend.UserInfoExtendDAO;
import top.hooya.shop.pojo.*;
import top.hooya.shop.pojo.UserInfoExample.Criteria;
import top.hooya.shop.pojo.extend.SysMenuExtend;
import top.hooya.shop.pojo.extend.UserInfoExtend;
import top.hooya.shop.service.UserInfoService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class UserInfoServiceImpl implements UserInfoService {

	@Autowired
	private UserInfoDAO userInfoDAO;

	@Autowired
	private UserInfoExtendDAO userInfoExtendDao;

	@Autowired
	private SysRoleUserDAO  sysRoleUserDAO;

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
		userInfo.setPassword(null);
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

	@Override
	public List<UserInfoExtend> getUser(String keyWord) {
		if ("all".equals(keyWord)){
			keyWord = null;
		}
		return userInfoExtendDao.selectUserInfoByKeyWord(keyWord);
	}

	@Override
	public List<UserInfoExtend> getEmployee(String keyWord) {
		if ("all".equals(keyWord)){
			keyWord = null;
		}
		return userInfoExtendDao.selectEmployeeByKeyWord(keyWord);
	}

	@Override
	public int resetPasswordByUserId(Integer userId) {
		UserInfo userInfo = new UserInfo();
		userInfo.setId(userId);
		userInfo.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
		return userInfoDAO.updateByPrimaryKeySelective(userInfo);
	}

	@Override
	public int allocationRole(RoleUserVo vo) {

		int count = 0;
		for (Integer userId:vo.getUserIds()){
			SysRoleUser sysRoleUser = new SysRoleUser();
			sysRoleUser.setDelFlag(PropertiesUtil.DEL_FLAG);
			SysRoleUserExample sysRoleUserExample = new SysRoleUserExample();
			sysRoleUserExample.createCriteria().andUserIdEqualTo(userId).andDelFlagNotEqualTo(PropertiesUtil.DEL_FLAG);
			sysRoleUserDAO.updateByExampleSelective(sysRoleUser,sysRoleUserExample);
		}
		for (Integer userId:vo.getUserIds()){
			SysRoleUser sysRoleUser = new SysRoleUser();
			sysRoleUser.setRoleId(vo.getRoleId());
			sysRoleUser.setUserId(userId);
			sysRoleUser.setCreateTime(new Date());
			count += sysRoleUserDAO.insertSelective(sysRoleUser);
		}

		return count;
	}

	@Override
	public int revokeRole(RoleUserVo vo) {

		int count = 0;
		for (Integer userId:vo.getUserIds()){
			SysRoleUser sysRoleUser = new SysRoleUser();
			sysRoleUser.setDelFlag(PropertiesUtil.DEL_FLAG);
			SysRoleUserExample sysRoleUserExample = new SysRoleUserExample();
			sysRoleUserExample.createCriteria().andUserIdEqualTo(userId).andDelFlagNotEqualTo(PropertiesUtil.DEL_FLAG);
			count+= sysRoleUserDAO.updateByExampleSelective(sysRoleUser,sysRoleUserExample);
		}
		return count;
	}

	@Override
	public List<SysMenuExtend> getMenuListByUserId(Integer userId) {
		List<SysMenu> sysMenuList  = userInfoExtendDao.selectMenuListByUserId(userId);
		sysMenuList.removeAll(Collections.singleton(null));
		List<SysMenuExtend> menuTree = new ArrayList<>();
			for (SysMenu sysMenu:sysMenuList){
				if (sysMenu.getParentId()==-1){
					SysMenuExtend sysMenuExtend = new SysMenuExtend(sysMenu);
					sysMenuExtend.setChildren(getChildren(sysMenuExtend.getId(),sysMenuList));
					menuTree.add(sysMenuExtend);
				}
			}
		return menuTree;

	}

	@Override
	public int register(UserInfo userInfo) {
		userInfo.setCreateTime(new Date());
		userInfo.setPassword(DigestUtils.md5DigestAsHex(userInfo.getPassword().getBytes()));
		userInfoDAO.insertSelective(userInfo);
		SysRoleUser sysRoleUser = new SysRoleUser();
		sysRoleUser.setUserId(userInfo.getId());
		sysRoleUser.setRoleId(PropertiesUtil.ROLE_BUYER);
		return sysRoleUserDAO.insertSelective(sysRoleUser);
	}

	@Override
	public Long validate(Integer id,String keyWord) {
		Long count;
		if(id==null){
			UserInfoExample example = new UserInfoExample();
			Criteria criteriaByUsername = example.createCriteria(); /*用户名*/
			Criteria criteriaByEmail = example.createCriteria();	/*邮箱*/
			Criteria criteriaByPhoneNum = example.createCriteria();	/*手机号*/
			criteriaByUsername.andUserNameEqualTo(keyWord);
			criteriaByEmail.andEmailEqualTo(keyWord);
			criteriaByPhoneNum.andPhoneNumEqualTo(keyWord);
			example.or(criteriaByEmail);
			example.or(criteriaByPhoneNum);
			count = userInfoDAO.countByExample(example);
		}else{
			UserInfoExample example = new UserInfoExample();
			Criteria criteriaByUsername = example.createCriteria(); /*用户名*/
			Criteria criteriaByEmail = example.createCriteria();	/*邮箱*/
			Criteria criteriaByPhoneNum = example.createCriteria();	/*手机号*/
			criteriaByUsername.andUserNameEqualTo(keyWord).andIdNotEqualTo(id);
			criteriaByEmail.andEmailEqualTo(keyWord).andIdNotEqualTo(id);
			criteriaByPhoneNum.andPhoneNumEqualTo(keyWord).andIdNotEqualTo(id);
			example.or(criteriaByEmail);
			example.or(criteriaByPhoneNum);
			count = userInfoDAO.countByExample(example);
		}
		return count;
	}

	private List<SysMenu> getChildren(Integer parentId,List<SysMenu> sysMenuList){
		List<SysMenu> secondMenuList = new ArrayList<>();
		for (SysMenu sysMenu: sysMenuList){
			if (sysMenu.getParentId().equals(parentId)){
				secondMenuList.add(sysMenu);
			}
		}
		return secondMenuList;
	}
}
