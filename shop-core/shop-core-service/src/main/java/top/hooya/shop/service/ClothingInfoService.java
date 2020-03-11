package top.hooya.shop.service;

import top.hooya.shop.pojo.ClothingInfo;
import top.hooya.shop.pojo.extend.ClothingInfoExtend;

import java.util.List;

/**
 * @author juzi9
 * @date 2020-03-01 20:56
 */
public interface ClothingInfoService {
    List<ClothingInfoExtend> getClothingByCategory(String name);

    ClothingInfoExtend getClothingById(Integer id);
}
