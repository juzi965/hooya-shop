package top.hooya.shop.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import top.hooya.shop.common.pojo.CurrentUser;
import top.hooya.shop.common.pojo.ShoppingCartVo;
import top.hooya.shop.common.pojo.UserLoginToken;
import top.hooya.shop.common.result.Result;
import top.hooya.shop.common.utils.AliPayUtil;
import top.hooya.shop.common.utils.DateUtils;
import top.hooya.shop.common.utils.PropertiesUtil;
import top.hooya.shop.pojo.UserInfo;
import top.hooya.shop.pojo.extend.OrderInfoExtend;
import top.hooya.shop.service.OrderInfoService;
import top.hooya.shop.websocket.WebSocket;

import java.util.List;
import java.util.Map;

/**
 * @author juzi9
 * @date 2020-03-03 21:08
 */
@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderInfoService orderInfoService;

    @Autowired
    private WebSocket webSocket;

    @UserLoginToken
    @PostMapping("/create/{addressId}")
    public Result createOrder(@RequestBody List<ShoppingCartVo> cartVos,@CurrentUser UserInfo userInfo, @PathVariable("addressId") Integer addressId){

        String orderId = orderInfoService.createOrder(cartVos,addressId,userInfo.getId());


        for (UserInfo item:orderInfoService.getUserInfoByRoleId(PropertiesUtil.ROLE_SELLER)){
            webSocket.sendOneMessage(item, DateUtils.getCurrDate("yyyy-MM-dd hh:mm:ss") +"您有新的订单");
        }

        return !StringUtils.isEmpty(orderId)? Result.success(orderId):Result.error("订单创建失败");
    }
    @UserLoginToken
    @GetMapping("/{pageNum}/{pageSize}")
    public Result getOrderByUserId(@CurrentUser UserInfo userInfo, @PathVariable("pageNum") Integer pageNum, @PathVariable("pageSize") Integer pageSize){

        PageHelper.startPage(pageNum,pageSize,"create_time desc");
        List<OrderInfoExtend> orderInfoExtendList = orderInfoService.getOrderByUserId(userInfo.getId());
        PageInfo<OrderInfoExtend> pageInfo = new PageInfo<>(orderInfoExtendList);

        return Result.success(pageInfo);
    }
    @UserLoginToken
    @GetMapping("/{keyWord}/{pageNum}/{pageSize}")
    public Result getOrder(@PathVariable("keyWord") String keyWord,@PathVariable("pageNum") Integer pageNum, @PathVariable("pageSize") Integer pageSize){

        PageHelper.startPage(pageNum,pageSize,"create_time desc");
        List<OrderInfoExtend> orderInfoExtendList = orderInfoService.getOrder(keyWord);
        PageInfo<OrderInfoExtend> pageInfo = new PageInfo<>(orderInfoExtendList);

        return Result.success(pageInfo);
    }
    @UserLoginToken
    @PostMapping("/confirm-receipt")
    public Result confirmReceipt(String orderId){
        int count = orderInfoService.confirmReceipt(orderId);
        return count>0?Result.success(count):Result.error("确认收货失败");
    }
    @UserLoginToken
    @PostMapping("/delete")
    public Result deleteOrderByOrderId(String orderId){
        int count = orderInfoService.deleteOrderByOrderId(orderId);
        return count>0?Result.success(count):Result.error("删除订单失败");
    }
    @UserLoginToken
    @PostMapping("/qrcode")
    public Result qrcode(String orderId) {
        OrderInfoExtend orderInfoExtend = orderInfoService.getOrderByOrderId(orderId);

        String qrCode =  AliPayUtil.getQrCode(orderInfoExtend.getOrderId(),String.valueOf(orderInfoExtend.getTotalPrice()),orderInfoExtend.getOrderId());

        return !StringUtils.isEmpty(qrCode)?Result.success(qrCode):Result.error("获取收款码失败，请点击重新获取");
    }
    @UserLoginToken
    @PostMapping("/pay-state")
    public Result payState (String orderId){
        Map<String,String> map =  AliPayUtil.getPayState(orderId);
        if(!CollectionUtils.isEmpty(map)){
            if ("TRADE_SUCCESS".equals(map.get("trade_status"))){
                orderInfoService.paySuccess(orderId);
                return Result.success(map.get("trade_no"));
            }
        }
        return Result.error("支付失败");
    }
    @UserLoginToken
    @PostMapping("/send")
    public Result send (String orderId,String expressNum){
        int count = orderInfoService.send(orderId,expressNum);
        if (count>0){
            UserInfo userInfo = orderInfoService.getUserInfoByOrderId(orderId);
            webSocket.sendOneMessage(userInfo,DateUtils.getCurrDate("yyyy-MM-dd hh:mm:ss")+"您的订单号为："+orderId+"的订单已发货，快递号为："+expressNum);
            return Result.success(count);
        }
        return Result.error("发货失败");
    }
}
