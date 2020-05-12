package top.hooya.shop.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.hooya.shop.common.result.Result;


/**
 * @author juzi9
 * @date 2020-04-06 21:11
 */
@RestController
public class CustomErrorController implements ErrorController {


    @RequestMapping("/error")
    public Result handleError(){
        return Result.error("请求资源未找到");
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
