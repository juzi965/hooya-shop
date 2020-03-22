package top.hooya.shop.service;

import top.hooya.shop.pojo.AddressInfo;

import java.util.List;

/**
 * @author juzi9
 * @date 2020-03-01 12:13
 */
public interface AddressInfoService {
    AddressInfo getAddressInfoById(Integer id);

    List<AddressInfo> getAddressInfoByUserId(Integer userId);

    int changeAddressInfoById(AddressInfo addressInfo);

    int addAddressInfoByUserId(AddressInfo addressInfo);
}
