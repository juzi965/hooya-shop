package top.hooya.shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.hooya.shop.common.utils.PropertiesUtil;
import top.hooya.shop.dao.AddressInfoDAO;
import top.hooya.shop.pojo.AddressInfo;
import top.hooya.shop.pojo.AddressInfoExample;
import top.hooya.shop.service.AddressInfoService;

import java.util.Date;
import java.util.List;

/**
 * @author juzi9
 * @date 2020-03-01 12:14
 */
@Service
public class AddressInfoServiceImpl implements AddressInfoService {

    @Autowired
    private AddressInfoDAO addressInfoDAO;

    @Override
    public AddressInfo getAddressInfoById(Integer id) {
        return addressInfoDAO.selectByPrimaryKey(id);
    }

    @Override
    public List<AddressInfo> getAddressInfoByUserId(Integer userId) {
        AddressInfoExample example = new AddressInfoExample();
        example.createCriteria().andUserIdEqualTo(userId).andDelFlagNotEqualTo(PropertiesUtil.DEL_FLAG);
        return addressInfoDAO.selectByExample(example);
    }

    @Override
    public int changeAddressInfoById(AddressInfo addressInfo) {
        return addressInfoDAO.updateByPrimaryKeySelective(addressInfo);
    }

    @Override
    public int addAddressInfoByUserId(AddressInfo addressInfo) {
        addressInfo.setCreateTime(new Date());
        return addressInfoDAO.insertSelective(addressInfo);
    }
}
