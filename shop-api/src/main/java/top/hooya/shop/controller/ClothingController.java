package top.hooya.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.hooya.shop.common.pojo.UserLoginToken;
import top.hooya.shop.common.result.Result;
import top.hooya.shop.pojo.extend.ClothingInfoExtend;
import top.hooya.shop.service.ClothingInfoService;

import java.util.List;

/**
 * @author juzi9
 * @date 2020-03-01 20:52
 */
@RestController
@RequestMapping("/api/clothing")
public class ClothingController {
    @Autowired
    private ClothingInfoService clothingInfoService;

    @GetMapping("/category/{name}")
    public Result getClothing(@PathVariable("name") String name){
        List<ClothingInfoExtend> clothingInfoList = clothingInfoService.getClothingByCategory(name);
        return Result.success(clothingInfoList);
    }
    @GetMapping("/{id}")
    public Result getClothingById(@PathVariable("id") Integer id){
        ClothingInfoExtend clothingInfo = clothingInfoService.getClothingById(id);
        return Result.success(clothingInfo);
    }

}
