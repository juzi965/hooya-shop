package top.hooya.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.hooya.shop.common.pojo.CurrentUser;
import top.hooya.shop.common.pojo.UserLoginToken;
import top.hooya.shop.common.result.Result;
import top.hooya.shop.common.utils.PropertiesUtil;
import top.hooya.shop.pojo.AddressInfo;
import top.hooya.shop.pojo.UserInfo;
import top.hooya.shop.service.AddressInfoService;

import java.util.List;

/**
 * @author juzi9
 * @date 2020-03-01 12:10
 */
@RestController
@RequestMapping("/api/address")
public class AddressController {
    @Autowired
    private AddressInfoService addressInfoService;

    @UserLoginToken
    @GetMapping("")
    public Result getAddressInfoByUserId(@CurrentUser UserInfo userInfo){
        List<AddressInfo> addressInfoList = addressInfoService.getAddressInfoByUserId(userInfo.getId());
        return addressInfoList!=null? Result.success(addressInfoList):Result.error("您还没有设置收货地址");
    }

    @UserLoginToken
    @GetMapping("/id/{id}")
    public Result getAddressInfoById(@PathVariable("id") Integer id){
        AddressInfo addressInfo = addressInfoService.getAddressInfoById(id);
        return Result.success(addressInfo);
    }
    @UserLoginToken
    @PostMapping("/changeAddress")
    public Result changeAddress(AddressInfo addressInfo){
        int count;
        if (addressInfo.getId()!=null){
            count  = addressInfoService.changeAddressInfoById(addressInfo);
        }else{
            count = addressInfoService.addAddressInfoByUserId(addressInfo);
        }
        return count>0? Result.success(count):Result.error("操作失败");
    }
    @UserLoginToken
    @PostMapping("/deleteAddress/{id}")
    public Result deleteAddressById(@PathVariable("id") Integer id){
        AddressInfo addressInfo = new AddressInfo();
        addressInfo.setId(id);
        addressInfo.setDelFlag(PropertiesUtil.DEL_FLAG);
        int count = addressInfoService.changeAddressInfoById(addressInfo);
        return count>0? Result.success(count):Result.error("删除地址失败");
    }
}
