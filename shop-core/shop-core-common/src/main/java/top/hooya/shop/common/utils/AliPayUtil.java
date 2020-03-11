package top.hooya.shop.common.utils;


import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePrecreateRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;

import java.util.HashMap;
import java.util.Map;

/**
 * @author juzi9
 * @date 2020-03-06 21:03
 */
public class AliPayUtil {

    public static String getQrCode(String orderId,String totalAmount,String orderTitle) {
        AlipayClient alipayClient = new DefaultAlipayClient(PropertiesUtil.AILPAY_URL,PropertiesUtil.APP_ID,PropertiesUtil.APP_PRIVATE_KEY,"json","UTF-8",PropertiesUtil.ALIPAY_PUBLIC_KEY,"RSA2");
        AlipayTradePrecreateRequest request = new AlipayTradePrecreateRequest();
        request.setBizContent("{" +
                "    \"out_trade_no\":\""+orderId+"\"," +//商户订单号
                "    \"total_amount\":\""+totalAmount+"\"," +
                "    \"subject\":\""+orderTitle+"\"," +
                "    \"store_id\":\"HOOYA商店\"," +
                "    \"timeout_express\":\"120m\"}");//订单允许的最晚付款时间

        try {
            AlipayTradePrecreateResponse response = alipayClient.execute(request);
            return response.getQrCode();
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Map<String,String> getPayState(String orderId){
        Map<String,String> map = new HashMap<>();
        AlipayClient alipayClient = new DefaultAlipayClient(PropertiesUtil.AILPAY_URL,PropertiesUtil.APP_ID,PropertiesUtil.APP_PRIVATE_KEY,"json","UTF-8",PropertiesUtil.ALIPAY_PUBLIC_KEY,"RSA2");
        AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
        request.setBizContent("{\"out_trade_no\":\""+orderId+"\"}"); //设置业务参数
        try {
            AlipayTradeQueryResponse response = alipayClient.execute(request);
            map.put("trade_no",response.getTradeNo());
            map.put("trade_status",response.getTradeStatus());
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return map;
    }
}
