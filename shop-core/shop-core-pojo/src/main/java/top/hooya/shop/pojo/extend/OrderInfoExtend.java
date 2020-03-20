package top.hooya.shop.pojo.extend;

import top.hooya.shop.pojo.AddressInfo;
import top.hooya.shop.pojo.OrderInfo;

import java.util.List;

/**
 * @author juzi9
 * @date 2020-03-04 16:33
 */
public class OrderInfoExtend extends OrderInfo {

    private String userName;

    private List<OrderItemExtend>  orderItem;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public List<OrderItemExtend> getOrderItem() {
        return orderItem;
    }

    public void setOrderItem(List<OrderItemExtend> orderItem) {
        this.orderItem = orderItem;
    }
}
