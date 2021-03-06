package top.hooya.shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.hooya.shop.common.pojo.ShoppingCartVo;
import top.hooya.shop.common.utils.DateUtils;
import top.hooya.shop.common.utils.PropertiesUtil;
import top.hooya.shop.dao.AddressInfoDAO;
import top.hooya.shop.dao.ClothingAttrDAO;
import top.hooya.shop.dao.OrderInfoDAO;
import top.hooya.shop.dao.OrderItemDAO;
import top.hooya.shop.dao.extend.OrderInfoExtendDAO;
import top.hooya.shop.pojo.*;
import top.hooya.shop.pojo.extend.OrderInfoExtend;
import top.hooya.shop.pojo.extend.OrderItemExtend;
import top.hooya.shop.service.OrderInfoService;

import java.util.Date;
import java.util.List;

/**
 * @author juzi9
 * @date 2020-03-04 15:23
 */
@Service
public class OrderInfoServiceImpl implements OrderInfoService {

    @Autowired
    private OrderInfoDAO orderInfoDAO;

    @Autowired
    private ClothingAttrDAO clothingAttrDAO;

    @Autowired
    private OrderItemDAO orderItemDAO;

    @Autowired
    private OrderInfoExtendDAO orderInfoExtendDAO;

    @Autowired
    private AddressInfoDAO addressInfoDAO;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public String createOrder(List<ShoppingCartVo> cartVos, Integer addressId,Integer userId) {
        double totalPrice = 0;
        String uuid= DateUtils.getCurrDate("yyyyMMddHHmmssSSS")+userId.toString();
        for (ShoppingCartVo cartVo : cartVos) {
            ClothingAttr clothingAttr = clothingAttrDAO.selectByPrimaryKey(cartVo.getAttrId());
            int stock = clothingAttr.getStock()-cartVo.getNum();
            if (stock>=0){
                clothingAttr.setStock(stock);
                clothingAttrDAO.updateByPrimaryKeySelective(clothingAttr);
            }else{
                return null;
            }

            totalPrice += cartVo.getPrice() * cartVo.getNum();
            OrderItem orderItem = new OrderItem();
            orderItem.setOrderId(uuid);
            orderItem.setClothingId(cartVo.getClothingId());
            orderItem.setSize(cartVo.getSize());
            orderItem.setNum(cartVo.getNum());
            orderItem.setPrice(cartVo.getPrice());
            orderItem.setImgUrl(cartVo.getImg());
            orderItem.setCreateTime(new Date());
            orderItemDAO.insertSelective(orderItem);
        }
        AddressInfo addressInfo =addressInfoDAO.selectByPrimaryKey(addressId);
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setOrderId(uuid);
        orderInfo.setUserId(userId);
        orderInfo.setRecipient(addressInfo.getRecipient());
        orderInfo.setPhoneNum(addressInfo.getPhoneNum());
        orderInfo.setAddress(addressInfo.getAddress());
        orderInfo.setPayFlag(PropertiesUtil.PAY_UNPAY);
        orderInfo.setTotalPrice(totalPrice);
        orderInfo.setCreateTime(new Date());
        int count = orderInfoDAO.insertSelective(orderInfo);
        return count>0?uuid:null;
    }

    @Override
    public List<OrderInfoExtend> getOrderByUserId(Integer userId) {
        return orderInfoExtendDAO.selectOrderByUserId(userId);
    }
    @Override
    public List<OrderInfoExtend> getOrder(String keyWord) {
        if ("all".equals(keyWord)){
            keyWord=null;
        }
        return orderInfoExtendDAO.selectOrder(keyWord);
    }

    @Override
    public int send(String orderId, String expressNum) {
        OrderInfoExample example = new OrderInfoExample();
        example.createCriteria().andOrderIdEqualTo(orderId);
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setExpressNum(expressNum);
        return orderInfoDAO.updateByExampleSelective(orderInfo,example);
    }

    @Override
    public UserInfo getUserInfoByOrderId(String orderId) {
        return orderInfoExtendDAO.selectUserInfoByOrderId(orderId);
    }

    @Override
    public List<UserInfo> getUserInfoByRoleId(Integer roleId) {

        return orderInfoExtendDAO.selectUserInfoByRoleId(roleId);
    }

    @Override
    public OrderInfoExtend getOrderByOrderId(String orderId) {
        return orderInfoExtendDAO.selectOrderByOrderId(orderId);
    }

    @Override
    public int deleteOrderByOrderId(String orderId) {

        // 如果当前订单为未支付状态，则释放所选商品的数量。
        OrderInfoExtend orderInfoExtend = orderInfoExtendDAO.selectOrderByOrderId(orderId);
        if (orderInfoExtend.getPayFlag().equals(PropertiesUtil.PAY_UNPAY)){
            for (OrderItemExtend orderItemExtend: orderInfoExtend.getOrderItem()){
                ClothingAttrExample clothingAttrExample = new ClothingAttrExample();
                clothingAttrExample.createCriteria().andClothingIdEqualTo(orderItemExtend.getClothingId())
                        .andSizeEqualTo(orderItemExtend.getSize())
                        .andDelFlagNotEqualTo(PropertiesUtil.DEL_FLAG);
                List<ClothingAttr> clothingAttrList = clothingAttrDAO.selectByExample(clothingAttrExample);
                if (clothingAttrList!=null&&!clothingAttrList.isEmpty()){
                    ClothingAttr clothingAttr = clothingAttrList.get(0);
                    clothingAttr.setStock(clothingAttr.getStock()+orderItemExtend.getNum());
                    clothingAttrDAO.updateByPrimaryKeySelective(clothingAttr);
                }
            }
        }

        OrderItem orderItem = new OrderItem();
        orderItem.setDelFlag(PropertiesUtil.DEL_FLAG);
        OrderItemExample orderItemExample = new OrderItemExample();
        orderItemExample.createCriteria().andOrderIdEqualTo(orderId);
        orderItemDAO.updateByExampleSelective(orderItem,orderItemExample);

        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setDelFlag(PropertiesUtil.DEL_FLAG);
        OrderInfoExample orderInfoExample = new OrderInfoExample();
        orderInfoExample.createCriteria().andOrderIdEqualTo(orderId).andDelFlagNotEqualTo(PropertiesUtil.DEL_FLAG);

        return orderInfoDAO.updateByExampleSelective(orderInfo,orderInfoExample);
    }

    @Override
    public int confirmReceipt(String orderId) {
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setOrderFlag(PropertiesUtil.ORDER_CONFIRM);
        OrderInfoExample example = new OrderInfoExample();
        example.createCriteria().andOrderIdEqualTo(orderId).andOrderFlagEqualTo(PropertiesUtil.ORDER_UNCONFIRM).andDelFlagNotEqualTo(PropertiesUtil.DEL_FLAG);
        return orderInfoDAO.updateByExampleSelective(orderInfo,example);
    }

    @Override
    public int paySuccess(String orderId) {
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setPayFlag(PropertiesUtil.PAY_PAY);
        OrderInfoExample example = new OrderInfoExample();
        example.createCriteria().andOrderIdEqualTo(orderId);
        return orderInfoDAO.updateByExampleSelective(orderInfo,example);
    }


}
