package top.hooya.shop.controller;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.hooya.shop.common.pojo.StatisticalData;
import top.hooya.shop.common.pojo.UserLoginToken;
import top.hooya.shop.common.result.Result;
import top.hooya.shop.common.utils.HttpUtils;
import top.hooya.shop.service.OtherService;
import top.hooya.shop.websocket.WebSocket;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author juzi9
 * @date 2020-03-15 17:34
 */
@RestController
@RequestMapping("/api/other")
public class OtherController {

    @Autowired
    private OtherService otherService;

    @Autowired
    private WebSocket webSocket;

    @UserLoginToken
    @PostMapping("/kdi")
    public Result getKdi(String no){
        String host = "https://wuliu.market.alicloudapi.com";
        String path = "/kdi";
        String method = "GET";
        String appcode = "a8b70121485147f28aa1a79c44ba860a";  // Aliyun AppCode
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Authorization", "APPCODE " + appcode); //格式为:Authorization:APPCODE 83359fd73fe11248385f570e3c139xxx
        Map<String, String> querys = new HashMap<String, String>();
        querys.put("no", no);// 快递单号
        try {
            HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
            return Result.success(JSONObject.parse(EntityUtils.toString(response.getEntity()))); //输出json
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.error("获取物流信息失败");
    }
    @UserLoginToken
    @GetMapping("/today")
    public Result getTodayData(){
        StatisticalData statisticalData =  otherService.getTodayData();
        return Result.success(statisticalData);
    }

    @UserLoginToken
    @GetMapping("/month")
    public Result getMonthData(){
        List<StatisticalData> dataList =  otherService.getMonthData();
        return Result.success(dataList);
    }
}
