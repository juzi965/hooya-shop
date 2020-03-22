package top.hooya.shop.service;

import top.hooya.shop.common.pojo.ShoppingCartVo;
import top.hooya.shop.pojo.UserInfo;
import top.hooya.shop.pojo.extend.OrderInfoExtend;

import java.util.List;

/**
 * @author juzi9
 * @date 2020-03-04 15:22
 */
public interface OrderInfoService {

    String createOrder(List<ShoppingCartVo> cartVos, Integer addressId,Integer userId);

    List<OrderInfoExtend> getOrderByUserId(Integer userId);

    OrderInfoExtend getOrderByOrderId(String orderId);

    int deleteOrderByOrderId(String orderId);

    int confirmReceipt(String orderId);

    int paySuccess(String orderId);

    List<OrderInfoExtend> getOrder(String keyWord);

    int send(String orderId, String expressNum);

    UserInfo getUserInfoByOrderId(String orderId);

    List<UserInfo> getUserInfoByRoleId(Integer roleId);
}
